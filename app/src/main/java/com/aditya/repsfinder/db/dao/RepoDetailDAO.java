package com.aditya.repsfinder.db.dao;

import com.aditya.repsfinder.db.entities.RepoDetail;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RepoDetailDAO {

    @Query("SELECT * FROM repodetail")
    List<RepoDetail> getAll();

    @Query("SELECT * FROM repodetail WHERE id IN (:userIds)")
    List<RepoDetail> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM repodetail WHERE title LIKE :first LIMIT 1")
    RepoDetail findByTitle(String first);

    @Insert
    void insertAll(RepoDetail... repoDetails);

    @Delete
    void delete(RepoDetail repoDetail);

}
