package com.example.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModulActivity extends AppCompatActivity {

    ListView listView;
    ImageButton create_modul_btn;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul);

        listView = (ListView)findViewById(R.id.activity_modul__listview);
        create_modul_btn = (ImageButton)findViewById(R.id.activity_modul__create_btn);

        adapter = new ListAdapter(this, addList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ModulActivity.this, "Test", Toast.LENGTH_SHORT).show();

            }
        });

        create_modul_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ModulActivity.this, "Create new modul", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }
        );

    }
    List<String> items = new ArrayList<>();
    private List<String> addList() {
        items.add("Animals");
        items.add("Fruits");
        items.add("Verbs");
        items.add("Travel");
        items.add("Places");
        return items;
    }

    private class ListAdapter extends ArrayAdapter<String>
    {
        Context context;
        List<String> rTitle;

        public ListAdapter (Context context, List<String> rTitle)
        {
            super(context, R.layout.activity_modul__list_row, rTitle);
            this.context = context;
            this. rTitle = rTitle;
        }

        public int getItemCount() {
            return rTitle.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_modul__list_row, parent, false);
            TextView title = row.findViewById(R.id.activity_modul__list_row__title);
            ImageView row_menu_btn = row.findViewById(R.id.activity_modul__list_row__ic_menu);
            title.setText(rTitle.get(position));

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ModulActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                }
            };

            row_menu_btn.setOnClickListener(listener);

            return row;
        }

        public List<String> getItems() {
            return rTitle;
        }

        public void setItems(List<String> rTitle) {
            this.rTitle = rTitle;
        }
    }
}