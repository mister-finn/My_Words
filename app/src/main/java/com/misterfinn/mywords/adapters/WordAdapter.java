package com.misterfinn.mywords.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.misterfinn.mywords.R;
import com.misterfinn.mywords.pojo.Word;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> words;
    private onClickDeleteWord onClickDeleteWord;
    private onClickChangeWord onClickChangeWord;

    public interface onClickDeleteWord {
        void onClick(int position);
    }

    public interface onClickChangeWord {
        void onClick(int position);
    }


    public WordAdapter() {
        this.words = new ArrayList<>();
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    public void setOnClickDeleteWord(onClickDeleteWord onClickDeleteWord) {
        this.onClickDeleteWord = onClickDeleteWord;
    }

    public void setOnClickChangeWord(WordAdapter.onClickChangeWord onClickChangeWord) {
        this.onClickChangeWord = onClickChangeWord;
    }

    public List<Word> getWords() {
        return words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(holder.getAdapterPosition());
        holder.textViewWord.setText(word.getEnglishWord());
        holder.textViewTranslation.setText(word.getTranslation());
        holder.textViewPartOfSpeech.setText(word.getPartOfSpeech());
        holder.imageViewDelete.setOnClickListener(v -> {
            if (onClickDeleteWord != null) {
                onClickDeleteWord.onClick(holder.getAdapterPosition());
            }
        });
        holder.imageViewChangeWord.setOnClickListener(v -> {
            if (onClickChangeWord != null) {
                onClickChangeWord.onClick(holder.getAdapterPosition());
            }
        });
        int colorId;
        switch (word.getPartOfSpeech()) {
            case "Существительные":
                colorId = holder.itemView.getResources().getColor(R.color.light_green);
                break;
            case "Прилагательные":
                colorId = holder.itemView.getResources().getColor(R.color.orange);
                break;
            case "Местоимения":
                colorId = holder.itemView.getResources().getColor(R.color.light_red);
                break;
            case "Глаголы":
                colorId = holder.itemView.getResources().getColor(R.color.light_blue);
                break;
            case "Наречия":
                colorId = holder.itemView.getResources().getColor(R.color.light_pink);
                break;
            case "Предлоги":
                colorId = holder.itemView.getResources().getColor(R.color.turquoise);
                break;
            case "Фразы":
                colorId = holder.itemView.getResources().getColor(R.color.yellow);
                break;
            default:
                colorId = holder.itemView.getResources().getColor(R.color.white);
                break;
        }
        holder.cardView.setCardBackgroundColor(colorId);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }


    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWord, textViewTranslation, textViewPartOfSpeech;
        private ImageView imageViewDelete, imageViewChangeWord;
        private CardView cardView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.textViewWord);
            textViewTranslation = itemView.findViewById(R.id.textViewTranslation);
            textViewPartOfSpeech = itemView.findViewById(R.id.textViewPartOfSpeech);
            imageViewDelete = itemView.findViewById(R.id.imageViewDeleteFromDatabase);
            imageViewChangeWord = itemView.findViewById(R.id.imageViewChangeWord);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
