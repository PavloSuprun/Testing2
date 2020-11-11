package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class CardstackActivity extends AppCompatActivity {

    private static final String TAG = "CardstackActivity";
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardstack);

        CardStackView cardStackView = findViewById(R.id.activity_cardstack__cardstack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(CardstackActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                }
                /*if (direction == Direction.Top){
                    Toast.makeText(CardstackActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }*/
                if (direction == Direction.Left){
                    Toast.makeText(CardstackActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                }
                /*if (direction == Direction.Bottom){
                    Toast.makeText(CardstackActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }*/

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_word);
                Log.d(TAG, "onCardAppeared: " + position + ", word: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_word);
                Log.d(TAG, "onCardAppeared: " + position + ", word: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        //manager.setDirections(Direction.FREEDOM);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList(), getApplicationContext());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> current = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, current);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(current);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        ZDatabaseAdapter adapter = new ZDatabaseAdapter(this);
        adapter.open();
        List<ItemModel> items = adapter.getWords();
        /*items.add(new ItemModel(*//*R.drawable.sample1,*//* "Rabbit", "[rabit]", "Кролик"));
        items.add(new ItemModel(*//*R.drawable.sample2,*//* "Horse", "[horse]", "Лошадь"));
        items.add(new ItemModel(*//*R.drawable.sample3,*//* "Home", "[home]", "Дом"));
        items.add(new ItemModel(*//*R.drawable.sample4,*//* "Car", "[car]", "Машина"));
        items.add(new ItemModel(*//*R.drawable.sample5,*//* "Iron", "[iron]", "Железо"));*/
        return items;
    }
}
