package com.example.testing;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<ItemModel> items;

    private Context context;
    private boolean isFront = true;

    public CardStackAdapter(List<ItemModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView word, transcription, translation;
        CardView cardView;
        AnimatorSet front_anim1;
        AnimatorSet front_anim2;
        AnimatorSet back_anim1;
        AnimatorSet back_anim2;
        AnimatorSet background_anim;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            word = itemView.findViewById(R.id.item_word); //front
            transcription = itemView.findViewById(R.id.item_transcription); // front
            translation = itemView.findViewById(R.id.item_translation); // back

            float scale = context.getResources().getDisplayMetrics().density;

            word.setCameraDistance(8000 * scale);
            transcription.setCameraDistance(8000 * scale);
            translation.setCameraDistance(8000 * scale);
            image.setCameraDistance(8000 * scale);

            front_anim1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.front_animator);
            front_anim2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.front_animator);
            back_anim1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.back_animator);
            back_anim2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.back_animator);
            background_anim = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.background_animator);


            cardView = itemView.findViewById(R.id.item_card__cardview);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!front_anim1.isRunning() || !back_anim1.isRunning()) {

                        if (isFront) {
                            front_anim1.setTarget(word);
                            front_anim2.setTarget(transcription);
                            back_anim1.setTarget(translation);
                            background_anim.setTarget(image);
                            front_anim1.start();
                            front_anim2.start();
                            back_anim1.start();
                            background_anim.start();
                            isFront = false;
                        } else {
                            front_anim1.setTarget(translation);
                            back_anim1.setTarget(word);
                            back_anim2.setTarget(transcription);
                            background_anim.setTarget(image);
                            front_anim1.start();
                            back_anim1.start();
                            back_anim2.start();
                            background_anim.start();
                            isFront = true;
                        }
                    }
                }

            };
            cardView.setOnClickListener(listener);
        }

        void setData(ItemModel data) {
            //Picasso.get()
            //        .load(data.getImage())
            //        .fit()
            //        .centerCrop()
            //        .into(image);
            word.setText(data.getWord());
            transcription.setText(data.getTranscription());
            translation.setText(data.getTranslation());
        }
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}

