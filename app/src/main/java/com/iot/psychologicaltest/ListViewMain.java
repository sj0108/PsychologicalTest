package com.iot.psychologicaltest;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.iot.psychologicaltest.QuestionActivity.REQUEST_CODE_MENU;

public class ListViewMain extends AppCompatActivity {

    private ListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        adapter = new ListAdapter();
        listView = (ListView) findViewById(R.id.listView);

        setData();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                Content content = new Content(position, 0);
                intent.putExtra("content", content);
                startActivityForResult(intent, REQUEST_CODE_MENU);
                //startActivity(intent);
            }
        });
    }

    private void setData() {
        TypedArray arrResId = getResources().obtainTypedArray(R.array.resId);
        String[] titles = getResources().getStringArray(R.array.subtitle);

        for (int i = 0; i < arrResId.length(); i++) {
            ListItem dto = new ListItem();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);

            adapter.addItem(dto);
        }
    }


}
