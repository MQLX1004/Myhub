package com.connect.mql.connect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datebase.mql.connect.ConnectDbhelp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements LetterIndexView.UpdateListView {
    private static final String TAG = "ListActivity";
    public static final String CONTENT_NAME="ListActivity_name";
    public static final String CONTENT_TEL="ListActivity_tel";
    public static ConnectDbhelp dbHelper;
    private RecyclerView mRecycleView;
    public ConnectAdapter mAdapter;
    private List<String> names;
    private List<String> Fletters;
    private List<String> tels;
    private EditText etInput;
    private ItemTouchHelper.Callback callback;
    private ItemTouchHelper mItemTouchHelper;
    private Toolbar mtoolbar;
    private FloatingActionButton fab;
    private ImageView search_del;
    private TextView show_letter;
    private LinearLayoutManager linearLayoutManager;
    private LetterIndexView letterbar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView grouplist;
    private List<String> tablenames;
    private Group_adapt groupAdapter;
    private Button Create_group;
    private TextView morenqunzu;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭手势滑动

        show_letter=(TextView)findViewById(R.id.show_letter_in_center);//实例化隐藏TextView
        letterbar=(LetterIndexView)findViewById(R.id.letterbar);//实例化自定义字母侧边栏
        letterbar.setTextViewDialog(show_letter);//绑定TextView
        letterbar.setUpdateListView(this);//绑定UpdateListView实例

        search_del=(ImageView)findViewById(R.id.search_delete);//实例化查找删除按钮
        search_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initdata("book");//初始化数据
                etInput.setText("");
                mAdapter.notifyDataSetChanged();//更新RecycleView
            }
        });

        mtoolbar=(Toolbar)findViewById(R.id.listToolbar);//实例化Toolbar
        setSupportActionBar(mtoolbar);//将toolbar设置为ActionBar
        getSupportActionBar().setTitle("联系人");//为toolbar设置标题

        fab=(FloatingActionButton)findViewById(R.id.fab);//实例化FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListActivity.this,AddActivity.class);//定义显式Intent
                startActivity(intent);//开始此意图
            }
        });

        dbHelper=new ConnectDbhelp(this,"Connects.db",null,1);
        dbHelper.setTablename("suoyou");
        sqLiteDatabase=dbHelper.getWritableDatabase();//打开数据库，如果没有数据库则创建
        dbHelper.setTablename("alltable");
        dbHelper.onCreate(sqLiteDatabase);//创建数据库表

        mRecycleView=(RecyclerView)findViewById(R.id.ConnectList);
        linearLayoutManager=new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);//设置布局管理器
//        mRecycleView.addItemDecoration(new SectionDecoration(this, new DecorationCallback() {
//            @Override
//            public String getGroupFirstLine(int position) {
//                if (Fletters.size()!=0){
//                    return Fletters.get(position);
//                }
//                return "";
//            }
//        }));

        mAdapter=new ConnectAdapter();
        mRecycleView.setAdapter(mAdapter);

        callback=new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper=new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecycleView);

        names=new ArrayList<>();
        Fletters=new ArrayList<>();
        tels=new ArrayList<>();
        tablenames=new ArrayList<>();

        etInput=(EditText)findViewById(R.id.searchview);
        //给EditText添加监听器
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            //当EditText内容发生变化时会调用此方法
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initdata(PinYin.getPinYin(Group_adapt.name));//初始化数据
                if (charSequence.toString().equals("")){
                    search_del.setVisibility(View.GONE);
                }else{
                    search_del.setVisibility(View.VISIBLE);
                }
                searchtext(charSequence);//匹配查找
                mAdapter.notifyDataSetChanged();//更新RecycleView
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        right();
    }
    private void searchtext(CharSequence charSequence){
        for (int x = 0; x<names.size(); x++){
            String item=names.get(x);
            if(!(item.contains(charSequence.toString()))){
                names.remove(item);
                tels.remove(tels.get(x));
                Fletters.remove(Fletters.get(x));
                x--;
            }
        }
    }
    //此类从数据库获取数据
    public void initdata(String table){
        if(names!=null){
            names.clear();
            Fletters.clear();
            tels.clear();
        }
            SQLiteDatabase db = dbHelper.getWritableDatabase();//打开数据库
            Cursor cursor = db.query(table, null, null, null, null, null, "letter", null);//读取数据库中所有的数据
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));//通过列名获取数据
                    names.add(name);
                    String letter=cursor.getString(cursor.getColumnIndex("letter"));
                    Fletters.add(letter);
                    String tel = cursor.getString(cursor.getColumnIndex("tel"));
                    tels.add(tel);
                } while (cursor.moveToNext());
            }
            cursor.close();
    }
    public void initgroup(){
        if(tablenames!=null){
            tablenames.clear();
        }
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        Cursor cursor=database.query("alltable",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String name=cursor.getString(cursor.getColumnIndex("tablename"));
                tablenames.add(name);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    class ConnectAdapter extends RecyclerView.Adapter<ConnectAdapter.MyViewHolder> implements onMoveAndSwipedListener,DialogDelete{
        private int position;
        @Override
        public void delete() {
            SQLiteDatabase db=ListActivity.dbHelper.getWritableDatabase();
            db.execSQL("DELETE FROM BOOK WHERE name = '"+names.get(position)+"' AND" +
                    " tel = '"+tels.get(position)+"';");
            mAdapter.notifyDataSetChanged();
            Toast.makeText(ListActivity.this,"已删除："+names.get(position),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void quxiaodelete() {
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition,toPosition);//交换列表中item的位置
            return true;
        }
        //当Item被右滑结束的时候调用
        @Override
        public void onItemRight(int position) {
//            this.position=position;
//            FragmentManager manager=getSupportFragmentManager();
//            Dialog_del dialog=new Dialog_del(this);
//            dialog.show(manager,"ListActivity");
            Toast.makeText(ListActivity.this,"正在拨号："+names.get(position),Toast.LENGTH_SHORT).show();
        }
        //当Item被左滑结束的时候调用
        @Override
        public void onItemLift(int position) {
            Toast.makeText(ListActivity.this,"正在发送短信："+names.get(position),Toast.LENGTH_SHORT).show();
        }

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
            char a=names.get(position).charAt(0);
            holder.xing.setText(String.valueOf(a));
        }

        @Override
        public int getItemCount() {
            return names.size();
        }
        //此类用来具体部署item
        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, onStateChangedListener{
            private View lay;
            private TextView name;
            private TextView tel;
            private TextView xing;
            private ImageView xing_img;
            private Random mRandom=new Random();
            public MyViewHolder(View itemView) {
                super(itemView);
                xing=(TextView)itemView.findViewById(R.id.item_img_tx);
                xing_img=(ImageView)itemView.findViewById(R.id.item_image);
                lay=itemView.findViewById(R.id.item_lay);
                setimgbg();
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
            //当Item被滑动或拖拽时调用此方法改变Item颜色
            @Override
            public void onItemSelected() {
                itemView.setBackgroundColor(Color.GRAY);
            }
            //当Item滑动完毕时回调此方法
            @Override
            public void onItemClear() {
                itemView.setBackgroundColor(Color.WHITE);
            }
            //此方法随机给img添加颜色
            private void setimgbg(){
                switch (mRandom.nextInt(4)){
                    case 0:
                        break;
                    case 1:
                        xing_img.setBackgroundResource(R.drawable.item_img_bg_green);
                        break;
                    case 2:
                        xing_img.setBackgroundResource(R.drawable.item_img_bg_red);
                        break;
                    case 3:
                        xing_img.setBackgroundResource(R.drawable.item_img_blue);
                        break;
                }
            }
        }
    }

    @Override
    protected void onResume() {//每次Activity出现在最顶层会调用onResume方法
        super.onResume();
        initdata(Group_adapt.name);
        initgroup();
        mAdapter.notifyDataSetChanged();//更新RecycleView
        groupAdapter.notifyDataSetChanged();//更新群组RecycleView
    }

    //部署菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.topmenu,menu);//绑定工具栏菜单
        return true;
    }
    //实现菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.group){
            mDrawerLayout.openDrawer(GravityCompat.END);
        }
        return super.onOptionsItemSelected(item);
    }
    //实现触摸字母跳转到相应位置的接口
    @Override
    public void updateListView(String currentChar) {
        int x = 0;
        for(int i=0;i<Fletters.size();i++){
            if(Fletters.get(i).equals(currentChar)){
                x=i;
                break;
            }
        }
        linearLayoutManager.scrollToPositionWithOffset(x,0);
        linearLayoutManager.setStackFromEnd(true);
    }
    //设置右边隐藏布局
    private void right(){
        grouplist=(RecyclerView)findViewById(R.id.group_list);
        grouplist.setLayoutManager(new LinearLayoutManager(this));
        initgroup();
        groupAdapter=new Group_adapt(this,tablenames);
        grouplist.setAdapter(groupAdapter);

        Create_group=(Button)findViewById(R.id.creategroup);
        Create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListActivity.this,Create_group.class);
                startActivity(intent);
            }
        });
        morenqunzu=(TextView) findViewById(R.id.morenqunzu);
        morenqunzu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Group_adapt.name="suoyou";
                initdata(Group_adapt.name);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
    public void deletegroup(String name){
        sqLiteDatabase.execSQL("DELETE FROM "+"alltable"+" WHERE tablename = '"+name+"';");
        sqLiteDatabase.execSQL("drop table if exists "+PinYin.getPinYin(name)+";");
        initgroup();
        groupAdapter.notifyDataSetChanged();
        initdata("suoyou");
        mAdapter.notifyDataSetChanged();
        Toast.makeText(ListActivity.this,"删除"+name+"成功",Toast.LENGTH_SHORT).show();
    }
}
