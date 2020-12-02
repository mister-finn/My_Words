package com.misterfinn.mywords.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.misterfinn.mywords.database.WordDatabase;
import com.misterfinn.mywords.pojo.Word;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static WordDatabase database;
    private LiveData<List<Word>> words;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = WordDatabase.getInstance(application.getApplicationContext());
        words = database.wordDao().getAllWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        new InsertWordTask().execute(word);
    }

    public void deleteWord(Word word) {
        new DeleteWordTask().execute(word);
    }

    class DeleteWordTask extends AsyncTask<Word, Void, Void> {
        @Override
        protected Void doInBackground(Word... words) {
            if (words != null && words.length > 0) {
                database.wordDao().deleteWord(words[0]);
            }
            return null;
        }
    }

    class InsertWordTask extends AsyncTask<Word, Void, Void> {
        @Override
        protected Void doInBackground(Word... words) {
            if (words != null && words.length > 0) {
                database.wordDao().insertWord(words[0]);
            }
            return null;
        }
    }

}
