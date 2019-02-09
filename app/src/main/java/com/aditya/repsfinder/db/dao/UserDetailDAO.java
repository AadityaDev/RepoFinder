package com.aditya.repsfinder.db.dao;

import com.aditya.repsfinder.db.entities.UserDetail;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDetailDAO {

    @Query("SELECT * FROM userdetail")
    List<UserDetail> getAll();

    @Query("SELECT * FROM userdetail WHERE id IN (:userIds)")
    List<UserDetail> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM userdetail WHERE login LIKE :first LIMIT 1")
    UserDetail findByName(String first);

    @Insert
    void insertAll(UserDetail... users);

    @Delete
    void delete(UserDetail user);
}
