package com.sp18.ssu370.baseprojectapp.ui.activities;


import android.database.Cursor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.sp18.ssu370.baseprojectapp.R;


public class TagActivity extends MainActivity {

    private TagAdapter mAdapter;

    public String convertToString(boolean[] bool){
        StringBuffer con = new StringBuffer();
        for (int i = 0; i < bool.length; i++){
            if (bool[i]){
                con.append("1");
            }
            else{
                con.append("0");
            }
        }
        return con.toString();
    }

    public void updateSelectedTags(String con, Cursor res){
        String newcon;
        for (int i = 0; i < con.length(); i++)

            if (con.charAt(i) == '1'){
                res.moveToPosition(i);
                newcon = res.getString(2);
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < res.getCount()- newcon.length(); j++){
                    buffer.append('0');
                }
                buffer.append('1');
                newcon += buffer.toString();
                boolean update = tagDB.updateData(res.getString(0),res.getString(1), newcon);
                if (update){
                    //Toast.makeText(TagActivity.this, "Update WAS GOOD", Toast.LENGTH_SHORT).show();
                }

            }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_view);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        TextView tags = (TextView) findViewById(R.id.tags);

        final EditText tagname = (EditText) findViewById(R.id.edit_tag_text);
        final RecyclerView tagcycle = findViewById(R.id.recycle_tag);
        tagcycle.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new TagAdapter(this, tagDB.getAllData());
        tagcycle.setAdapter(mAdapter);
        //Add button
        Button add = (Button) findViewById(R.id.add_db);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tagName = tagname.getText().toString();
                Cursor res = tagDB.getAllData();
                String con;
                boolean inserted;
                if (res.getCount() == 0){
                    inserted = tagDB.InsertData(tagName,"0");

                    //Toast.makeText(TagActivity.this, "DB is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAdapter.swapCursor(tagDB.getAllData());
                    boolean[] cons = mAdapter.getChecked();
                    con = convertToString(cons);

                    updateSelectedTags(con, res);

                    inserted = tagDB.InsertData(tagName,con);

                }



                mAdapter.swapCursor(tagDB.getAllData());

                if (inserted){
                    //Toast.makeText(TagActivity.this, "INSERT IS A SUCCESS", Toast.LENGTH_SHORT).show();
                    tagname.setText("");
                }
                //else
                    //Toast.makeText(TagActivity.this, "INSERT IS NOT A SUCCESS", Toast.LENGTH_SHORT).show();



            }
        });
        Button search = (Button) findViewById(R.id.search_db);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = tagDB.getAllData();
                /*
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Tag Name: " + res.getString(1) + "\n");
                    buffer.append("Con IDs: " + res.getString(2) + "\n\n");

                }
                */
                if (res.getCount() == 0){
                    Toast.makeText(TagActivity.this, "DB is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    //showMessage("Data", buffer.toString());
                    //int count = res.getCount();
                    int count = tagDB.deleteAll();
                    mAdapter.swapCursor(tagDB.getAllData());
                    Toast.makeText(TagActivity.this, "SUCCESSFULLY DELETED " + count + " TAGS", Toast.LENGTH_SHORT).show();
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

