package com.misterfinn.mywords.screens;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.misterfinn.mywords.R;
import com.misterfinn.mywords.pojo.Word;
import com.misterfinn.mywords.viewmodel.MainViewModel;

public class AddWordDialog extends DialogFragment {
    private Button buttonAddWord;
    private EditText editTextWord, editTextTranslation, editTextTag;
    private Spinner spinner;
    private MainViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);
        buttonAddWord = view.findViewById(R.id.buttonAddToDatabase);
        editTextTag = view.findViewById(R.id.editTextTag);
        editTextTranslation = view.findViewById(R.id.editTextTranslation);
        editTextWord = view.findViewById(R.id.editTextEnglishWord);
        spinner = view.findViewById(R.id.spinner);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MainViewModel.class);
        buttonAddWord.setText(R.string.add_to_database);
        buttonAddWord.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String englishWord = editTextWord.getText().toString();
                String translation = editTextTranslation.getText().toString();
                String tag = editTextTag.getText().toString();
                String partOfSpeech = spinner.getSelectedItem().toString();
                if (!englishWord.equals("") && !translation.equals("")) {
                    viewModel.insertWord(new Word(englishWord, translation, partOfSpeech, tag));
                    dismiss();

                }
                else {
                    Toast.makeText(getContext(), "Заполни все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;

    }
}
