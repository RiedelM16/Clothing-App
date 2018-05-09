package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sp18.ssu370.baseprojectapp.R;


public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private Context mContext;
    private  Cursor mCursor;
    private boolean[] checked;

    private SparseBooleanArray boxen= new SparseBooleanArray();
    public TagAdapter(Context context, Cursor cursor) {
    mContext = context;
    mCursor = cursor;
    checked = new boolean[cursor.getCount()];
    }
    public class TagViewHolder extends RecyclerView.ViewHolder {
        public TextView tagname;
        public TextView conlist;
        public CheckBox checkBox;


        public TagViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            tagname = itemView.findViewById(R.id.tag_name);
            conlist = itemView.findViewById(R.id.tag_con);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.tag_item, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        final int pos = position;
        if(!mCursor.moveToPosition(position)){
            return;
        }

            if(pos != 0) {
                boolean[] temp = checked;
                checked = new boolean[mCursor.getCount() + 1];
                for (int i = 0; i < temp.length; i++) {
                    checked[i] = temp[i];
                }
            }
            else{
                if (checked[0]){
                    checked = new boolean[mCursor.getCount() + 1];
                    checked[0] = true;
                }
                else
                    checked = new boolean[mCursor.getCount() + 1];

            }
        //checked = new boolean[mCursor.getCount()+1];
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
                    checked[pos] = true;
                }
                else{
                    checked[pos] = false;
                }
            }
        });
        String name = mCursor.getString(1);
        String con = mCursor.getString(2);
        holder.tagname.setText(name);
        holder.conlist.setText(con);
        holder.checkBox.setChecked(checked[pos]);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newcursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newcursor;
        notifyDataSetChanged();
    }

    public boolean[] getChecked() {
        return checked;
    }

    public void setChecked(boolean[] check){
        checked = check;
    }
}
