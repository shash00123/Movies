package android.example.firstapp.Room

import android.content.Context
import android.example.firstapp.Model.Domain.Model.Movies
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Movies::class), version = 1, exportSchema = false)
public abstract class AppDatabse : RoomDatabase() {
    abstract fun moviedao(): MovieDao

    companion object {
        @Volatile
        private var sInstance: AppDatabse? = null
        private const val DATABASE_NAME = "D1"

        fun getdatabase(context: Context): AppDatabse {
            return sInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabse::class.java,
                    "D1"
                ).build()
                sInstance = instance
                instance
            }
        }
    }
}