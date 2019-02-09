package com.aditya.repsfinder.db.dao;

import com.aditya.repsfinder.db.entities.PullRequest;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PullRequestDAO {
    @Query("SELECT * FROM pullrequest")
    List<PullRequest> getAll();

    @Insert
    void insertAll(PullRequest... pullRequests);

    @Delete
    void delete(PullRequest pullRequest);
}
