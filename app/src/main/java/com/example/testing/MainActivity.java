package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardFlip;
    private CardView cardStack;
    private CardView cardModul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardFlip = (CardView)findViewById(R.id.activity_main__cardflip);
        cardStack = (CardView)findViewById(R.id.activity_main__cardstack);
        cardModul = (CardView)findViewById(R.id.activity_main__cardmodul);
        cardFlip.setOnClickListener(this);
        cardStack.setOnClickListener(this);
        cardModul.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.activity_main__cardflip :
                i = new Intent("com.example.mainproject.CardflipActivity");
                startActivity(i);
                break;
            case R.id.activity_main__cardstack :
                i = new Intent("com.example.mainproject.CardstackActivity");
                startActivity(i);
                break;
            case R.id.activity_main__cardmodul :
                i = new Intent("com.example.mainproject.DemoModulActivity");
                startActivity(i);
                break;
        }
    }
}