package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.sp18.ssu370.baseprojectapp.R;


public class TagActivity extends MainActivity {

    private TagAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_view);

        TextView tags = (TextView) findViewById(R.id.tags);

        final EditText tagname = (EditText) findViewById(R.id.edit_tag_text);
        final EditText conlist = findViewById(R.id.edit_con_text);
        final RecyclerView tagcycle = findViewById(R.id.recycle_tag);
        tagcycle.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TagAdapter(this, tagDB.getAllData());
        tagcycle.setAdapter(mAdapter);
        Button add = (Button) findViewById(R.id.add_db);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagName = tagname.getText().toString();
                String con = conlist.getText().toString();

                boolean inserted = tagDB.InsertData(tagName,con);
                mAdapter.swapCursor(tagDB.getAllData());

                if (inserted){
                    Toast.makeText(TagActivity.this, "INSERT IS A SUCCESS", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(TagActivity.this, "INSERT IS NOT A SUCCESS", Toast.LENGTH_SHORT).show();



            }
        });
        Button search = (Button) findViewById(R.id.search_db);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = tagDB.getAllData();
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Tag Name: " + res.getString(1) + "\n");
                    buffer.append("Con IDs: " + res.getString(2) + "\n\n");

                }
                if (res.getCount() == 0){
                    Toast.makeText(TagActivity.this, "READ IS NOT A SUCCESS", Toast.LENGTH_SHORT).show();
                }
                else {
                    showMessage("Data", buffer.toString());
                    int count = res.getCount();
                    Toast.makeText(TagActivity.this, "READ IS A SUCCESS " + count, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

