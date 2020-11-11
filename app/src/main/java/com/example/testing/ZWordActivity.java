package com.example.testing;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ZWordActivity extends AppCompatActivity {

    private EditText wordBox;
    private EditText transBox;
    private Button delButton;
    private Button saveButton;

    private ZDatabaseAdapter adapter;
    private long wordId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_word);

        wordBox = (EditText) findViewById(R.id.word);
        transBox = (EditText) findViewById(R.id.trans);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        adapter = new ZDatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            wordId = extras.getLong("id");
        }
        // если 0, то добавление
        if (wordId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            ItemModel item = adapter.getWord(wordId);
            wordBox.setText(item.getWord());
            transBox.setText(item.getTranslation());
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){


        String word = wordBox.getText().toString();
        String trans = transBox.getText().toString();
        ItemModel item = new ItemModel(wordId, word, trans);

        adapter.open();
        if (wordId > 0) {
            adapter.update(item);
        } else {
            adapter.insert(item);
        }
        adapter.close();
        goHome();
    }
    public void delete(View view){

        adapter.open();
        adapter.delete(wordId);
        adapter.close();
        goHome();
    }
    private void goHome(){
        // переход к главной activity
        Intent intent = new Intent(this, DemoModulActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}