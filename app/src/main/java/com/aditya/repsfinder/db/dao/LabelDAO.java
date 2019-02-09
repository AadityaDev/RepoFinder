package com.aditya.repsfinder.db.dao;

import com.aditya.repsfinder.db.entities.Label;
import com.aditya.repsfinder.db.entities.PullRequest;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LabelDAO {

    @Query("SELECT * FROM label")
    List<Label> getAll();

    @Insert
    void insertAll(Label... labels);

    @Delete
    void delete(Label label);

}
