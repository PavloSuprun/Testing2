package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class CardflipActivity extends AppCompatActivity implements View.OnClickListener {

    AnimatorSet front_anim;
    AnimatorSet back_anim;
    TextView card_front;
    TextView card_back;
    ConstraintLayout flip_btn;
    boolean isFront = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardflip);

        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        card_front = (TextView)findViewById(R.id.activity_cardflip__card_front);
        card_back = (TextView)findViewById(R.id.activity_cardflip__card_back);

        card_front.setCameraDistance(8000 * scale);
        card_back.setCameraDistance(8000 * scale);

        front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);

        flip_btn = (ConstraintLayout) findViewById(R.id.activity_cardflip__card_view);
        flip_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(isFront)
        {
            front_anim.setTarget(card_front);
            back_anim.setTarget(card_back);
            front_anim.start();
            back_anim.start();
            isFront = false;
        }
        else
        {
            front_anim.setTarget(card_back);
            back_anim.setTarget(card_front);
            front_anim.start();
            back_anim.start();
            isFront = true;
        }
    }
}