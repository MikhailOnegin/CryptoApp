package com.example.cryptoapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1 , exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context:Context):AppDataBase{
            db?.let{
                return it
            }

            synchronized(LOCK){
                val instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
                db = instance
                return  instance}

        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

}