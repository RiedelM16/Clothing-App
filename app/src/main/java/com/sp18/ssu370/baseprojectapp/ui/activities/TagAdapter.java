package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sp18.ssu370.baseprojectapp.R;


public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private Context mContext;
    private  Cursor mcursor;
    public TagAdapter(Context context, Cursor cursor) {
    mContext = context;
    mcursor = cursor;
    }
    public class TagViewHolder extends RecyclerView.ViewHolder {
        public TextView tagname;
        public TextView conlist;


        public TagViewHolder(View itemView) {
            super(itemView);

            tagname = itemView.findViewById(R.id.tag_name);
            conlist = itemView.findViewById(R.id.tag_con);

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
        if(!mcursor.moveToPosition(position)){
            return;
        }
        String name = mcursor.getString(1);
        String con = mcursor.getString(2);
        holder.tagname.setText(name);
        holder.conlist.setText(con);

    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public void swapCursor(Cursor newcursor) {
        if (mcursor != null) {
            mcursor.close();
        }
        mcursor = newcursor;
        if (newcursor != null) {
            notifyDataSetChanged();
        }
    }
}
