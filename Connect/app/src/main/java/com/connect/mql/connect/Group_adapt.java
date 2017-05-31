package com.connect.mql.connect;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by MQL on 2017/5/22.
 */

public class Group_adapt extends RecyclerView.Adapter<Group_adapt.myViewHolder> implements Del_group {
    public static String name="suoyou";
    private ListActivity mContext;
    private List<String> tables;
    private String del;
    public Group_adapt(ListActivity context, List<String> table) {
        mContext = context;
        tables=table;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder=new myViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.group_list_item,parent,false
        ));
        return holder;
    }
    @Override
    public int getItemCount() {
        return tables.size();
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.group_item_text.setText(tables.get(position));
        holder.group_item_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=PinYin.getPinYin(holder.group_item_text.getText().toString());
                mContext.initdata(PinYin.getPinYin(name));
                mContext.mAdapter.notifyDataSetChanged();
            }
        });
        holder.group_item_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                del=tables.get(position);
                FragmentManager manager=mContext.getSupportFragmentManager();
                delete_group dialog=new delete_group(Group_adapt.this);
                dialog.show(manager,"TelActivity");
                return true;
            }
        });
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        private TextView group_item_text;
        public myViewHolder(View itemView) {
            super(itemView);
            group_item_text=(TextView)itemView.findViewById(R.id.group_item_text);
        }
    }

    @Override
    public void delgroup() {
        mContext.deletegroup(del);
    }
}
