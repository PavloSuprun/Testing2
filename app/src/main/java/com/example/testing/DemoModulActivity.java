package com.example.testing;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class DemoModulActivity extends AppCompatActivity {

    private ListView wordList;
    ArrayAdapter<ItemModel> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_modul);

        wordList = (ListView)findViewById(R.id.list);

        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemModel item = arrayAdapter.getItem(position);
                if(item!=null) {
                    Intent intent = new Intent(getApplicationContext(), ZWordActivity.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("click", 25);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ZDatabaseAdapter adapter = new ZDatabaseAdapter(this);
        adapter.open();

        List<ItemModel> items = adapter.getWords();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        wordList.setAdapter(arrayAdapter);
        adapter.close();
    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, ZWordActivity.class);
        startActivity(intent);
    }
}