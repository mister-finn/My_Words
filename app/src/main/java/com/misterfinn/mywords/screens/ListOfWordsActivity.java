package com.misterfinn.mywords.screens;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.misterfinn.mywords.R;
import com.misterfinn.mywords.adapters.WordAdapter;
import com.misterfinn.mywords.pojo.Word;
import com.misterfinn.mywords.viewmodel.MainViewModel;

import java.util.List;

public class ListOfWordsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WordAdapter adapter;
    private MainViewModel viewModel;
    private DialogFragment dialogFragmentAddWord;
    private DialogFragment dialogFragmentChangeWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_words);
        recyclerView = findViewById(R.id.recyclerViewAllWords);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WordAdapter();
        LiveData<List<Word>> words = viewModel.getWords();
        words.observe(this, words1 -> adapter.setWords(words1));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickDeleteWord(position -> {
            Word word = adapter.getWords().get(position);
            viewModel.deleteWord(word);
        });
        adapter.setOnClickChangeWord(position -> {
            Word word = adapter.getWords().get(position);
            dialogFragmentChangeWord = new ChangeWordDialog(word);
            dialogFragmentChangeWord.show(getSupportFragmentManager(), "dialogFragmentChangeWord");
        });
    }

    public void onClickAddWordInList(View view) {
        dialogFragmentAddWord = new AddWordDialog();
        dialogFragmentAddWord.show(getSupportFragmentManager(), "dialogFragmentAddWord");
    }
}