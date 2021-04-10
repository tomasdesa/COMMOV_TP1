package ipvc.estg.commovtp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.commovtp.dao.NotasDao
import ipvc.estg.commovtp.entities.nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(nota::class), version = 8, exportSchema = false)
public abstract class NotaDB : RoomDatabase() {

    abstract fun NotasDao(): NotasDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var NotasDao = database.NotasDao()

                    // Delete all content here.
                    /*cityDao.deleteAll()

                    // Add sample cities.
                    var city = City(1, "Viana do Castelo", "Portugal")
                    cityDao.insert(city)
                    city = City(2, "Porto", "Portugal")
                    cityDao.insert(city)
                    city = City(3, "Aveiro", "Portugal")
                    cityDao.insert(city)*/

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotaDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotaDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDB::class.java,
                    "nota_database"
                )
                    //estratégia de destruição
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}