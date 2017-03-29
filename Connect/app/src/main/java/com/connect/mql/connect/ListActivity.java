package com.connect.mql.connect;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.datebase.mql.connect.ConnectDbhelp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    public static final String CONTENT_NAME="ListActivity_name";
    public static final String CONTENT_TEL="ListActivity_tel";
    public static ConnectDbhelp dbHelper;
    private RecyclerView mRecycleView;
    private ConnectAdapter mAdapter;
    private List<String> names;
    private List<String> tels;
    private SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper=new ConnectDbhelp(this,"Connects.db",null,1);
        dbHelper.getWritableDatabase();//打开数据库，如果没有数据库则创建

        mRecycleView=(RecyclerView)findViewById(R.id.ConnectList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
//        mRecycleView.addItemDecoration(new RecycleViewDecoration(this, new RecycleViewDecoration.DecorationCallback() {
//            @Override
//            public long getGroupId(int position) {
//                return Character.toUpperCase();
//            }
//
//            @Override
//            public String getGroupFirstLine(int position) {
//                return null;
//            }
//        }));//添加ItemDecoration

        mAdapter=new ConnectAdapter();
        mRecycleView.setAdapter(mAdapter);

        names=new ArrayList<>();
        tels=new ArrayList<>();

    }
    //此类从数据库获取数据
    private void initdata(){
        if(names!=null){
            names.clear();
            tels.clear();
        }
        SQLiteDatabase db=dbHelper.getWritableDatabase();//打开数据库
        Cursor cursor=db.query("book",null,null,null,null,null,"name",null);//读取数据库中所有的数据
        if(cursor.moveToFirst()){
            do {
                String name=cursor.getString(cursor.getColumnIndex("name"));//通过列名获取数据
                names.add(name);
                String tel=cursor.getString(cursor.getColumnIndex("tel"));
                tels.add(tel);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    class ConnectAdapter extends RecyclerView.Adapter<ConnectAdapter.MyViewHolder>{
        //此类绑定item视图
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder=new MyViewHolder(LayoutInflater.from(ListActivity.this).inflate(
                    R.layout.content_item,parent,false
            ));
            return holder;
        }

        //此类用来给item分配数据
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.name.setText(names.get(position));
            holder.tel.setText(tels.get(position));
        }

        @Override
        public int getItemCount() {
            return names.size();
        }
        //此类用来具体部署item
        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView name;
            private TextView tel;
            public MyViewHolder(View itemView) {
                super(itemView);
                name=(TextView)itemView.findViewById(R.id.name);
                tel=(TextView)itemView.findViewById(R.id.tel);
                itemView.setOnClickListener(this);//实现item的点击效果
            }

            @Override
            public void onClick(View view) {//实现item的点击事件
                Intent intent=new Intent(ListActivity.this,TelActivity.class);//显式Intent
                intent.putExtra(CONTENT_NAME,name.getText());//将name绑定到Intent上
                intent.putExtra(CONTENT_TEL,tel.getText());//将tel绑定到Intent上
                startActivity(intent);//开始意图
            }
        }
    }

    @Override
    protected void onResume() {//每次Activity出现在最顶层会调用onResume方法
        super.onResume();
        initdata();
        mAdapter.notifyDataSetChanged();//更新RecycleView
    }
    //部署菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.topmenu,menu);//绑定工具栏菜单

        final MenuItem item=menu.findItem(R.id.search);
        mSearchView=(SearchView) MenuItemCompat.getActionView(item);//获取SearchView
        mSearchView.setSubmitButtonEnabled(true);
        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo info=searchManager.getSearchableInfo(getComponentName());
        mSearchView.setSearchableInfo(info);

        return super.onCreateOptionsMenu(menu);
    }
    //实现菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addmenu){//实现工具栏菜单项的点击效果
            Intent intent=new Intent(this,AddActivity.class);//定义显式Intent
            startActivity(intent);//开始此意图
        }
        return super.onOptionsItemSelected(item);
    }

    //此类设置item的高级样式ItemDecoration
    public static class RecycleViewDecoration extends RecyclerView.ItemDecoration{

        private int dividerHeight;//item间距大小
        private Paint dividerpaint;//绘制item
        private DecorationCallback mCallback;//回调接口实例
        private Paint paint;//绘制组头
        private TextPaint mTextPaint;//绘制字体
        private int topGap;
        private Paint.FontMetrics mFontMetrics;//字体属性

        public RecycleViewDecoration(Context context,DecorationCallback decorationCallback){
            Resources res=context.getResources();
            this.mCallback=decorationCallback;
            //设置组头属性
            paint=new Paint();
            paint.setColor(res.getColor(R.color.colorAccent));
            //设置组头字体属性
            mTextPaint=new TextPaint();
            mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mTextPaint.setAntiAlias(true);
            mTextPaint.setTextSize(80);
            mTextPaint.setColor(Color.BLACK);
            mTextPaint.setTextAlign(Paint.Align.LEFT);
            mTextPaint.getFontMetrics(mFontMetrics);
            mFontMetrics=new Paint.FontMetrics();
            topGap=res.getDimensionPixelSize(R.dimen.sectioned_top);
            //设置item间距属性
            dividerpaint=new Paint();
            dividerpaint.setColor(res.getColor(R.color.colorAccent));
            dividerHeight=context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        }
        //设置item的上下左右距离（margin）
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.bottom=dividerHeight;

            int pos=parent.getChildAdapterPosition(view);
            long groupId=mCallback.getGroupId(pos);
            if(groupId<0) return;
            if(pos==0||isFirstInGroup(pos)){
                outRect.top=topGap;
            }else{
                outRect.top=0;
            }
        }
        private boolean isFirstInGroup(int pos){
            if(pos==0){
                return true;
            }else{
                long prevGroupId=mCallback.getGroupId(pos-1);
                long groupId=mCallback.getGroupId(pos);
                return prevGroupId!=groupId;
            }
        }
        //绘制上下左右距离
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c,parent,state);

            int childCount=parent.getChildCount();
            int left=parent.getPaddingLeft();
            int right=parent.getWidth()-parent.getPaddingRight();

            for(int i=0;i<childCount-1;i++){
                View view=parent.getChildAt(i);
                float top=view.getBottom();
                float bottom=view.getBottom()+dividerHeight;
                c.drawRect(left,top,right,bottom,dividerpaint);
            }
            for(int j=0;j<childCount;j++){
                View view=parent.getChildAt(j);
                int position=parent.getChildAdapterPosition(view);
                long groupId=mCallback.getGroupId(position);
                if(groupId<0) return;
                String textline=mCallback.getGroupFirstLine(position).toUpperCase();
                if(position==0||isFirstInGroup(position)){
                    float mtop=view.getTop()-topGap;
                    float bottom=view.getTop();
                    c.drawRect(left,mtop,right,bottom,paint);//绘制红色矩形
                    c.drawText(textline,left,bottom,mTextPaint);//绘制文本
                }
            }
        }
        public interface DecorationCallback{
            long getGroupId(int position);
            String getGroupFirstLine(int position);
        }
    }
}
