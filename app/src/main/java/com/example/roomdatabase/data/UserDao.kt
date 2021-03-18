package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabase.Model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // to igone the repeatition of the data

    // suspend function can be paused and resumed at a later time
    suspend fun  addUser(user: User)

    @Update
    suspend fun updateUser(user : User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun  readAllData():LiveData<List<User>> // observable data

}