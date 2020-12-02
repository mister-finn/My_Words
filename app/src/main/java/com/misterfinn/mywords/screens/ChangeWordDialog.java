package com.misterfinn.mywords.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.misterfinn.mywords.R;
import com.misterfinn.mywords.pojo.Word;
import com.misterfinn.mywords.viewmodel.MainViewModel;

public class ChangeWordDialog extends DialogFragment {
    private Word word;

    public ChangeWordDialog(Word word) {
        this.word = word;
    }

    private Button buttonChangeWord;
    private EditText editTextWord, editTextTranslation, editTextTag;
    private Spinner spinner;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);
        buttonChangeWord = view.findViewById(R.id.buttonAddToDatabase);
        editTextTag = view.findViewById(R.id.editTextTag);
        editTextTag.setText(word.getTag());
        editTextTranslation = view.findViewById(R.id.editTextTranslation);
        editTextTranslation.setText(word.getTranslation());
        editTextWord = view.findViewById(R.id.editTextEnglishWord);
        editTextWord.setText(word.getEnglishWord());
        spinner = view.findViewById(R.id.spinner);
        spinner.setSelection(getIdOfSelectedItem(word.getPartOfSpeech()));
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MainViewModel.class);
        buttonChangeWord.setText(R.string.ok);
        buttonChangeWord.setOnClickListener(v -> {
            viewModel.deleteWord(word);
            String englishWord = editTextWord.getText().toString();
            String translation = editTextTranslation.getText().toString();
            String tag = editTextTag.getText().toString();
            String partOfSpeech = spinner.getSelectedItem().toString();
            if (!englishWord.equals("") && !translation.equals("")) {
                viewModel.insertWord(new Word(englishWord, translation, partOfSpeech, tag));
                dismiss();

            } else {
                Toast.makeText(getContext(), "Заполни все поля", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private int getIdOfSelectedItem(String partOfSpeech) {
        int id;
        switch (partOfSpeech) {
            case "Прилагательные":
                id = 1;
                break;
            case "Местоимения":
                id = 2;
                break;
            case "Глаголы":
                id = 3;
                break;
            case "Наречия":
                id = 4;
                break;
            case "Предлоги":
                id = 5;
                break;
            case "Фразы":
                id = 6;
                break;
            default:
                id = 0;
                break;

        }
        return id;
    }
}
