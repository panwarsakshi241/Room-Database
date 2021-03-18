package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.Model.User

@Database(entities = [User::class],version = 1 , exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract  fun  userDao(): UserDao

    companion object{

        //Volatile : writes to this field are immediately made visible to other threads

        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {

            val tempInstance =
                INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            // protected by the concurrent execution of multple threads

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}