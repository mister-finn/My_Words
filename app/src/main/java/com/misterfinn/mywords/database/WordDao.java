package com.misterfinn.mywords.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.misterfinn.mywords.pojo.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM words")
    LiveData<List<Word>> getAllWords();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWord(Word word);
    @Delete
    void deleteWord(Word word);

}
