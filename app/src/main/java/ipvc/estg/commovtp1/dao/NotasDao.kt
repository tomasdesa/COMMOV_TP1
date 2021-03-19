package ipvc.estg.commovtp1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.commovtp1.entities.nota

@Dao
interface NotasDao {

        @Query("SELECT * FROM notas_table ORDER BY id ASC")
        fun getAlphabetizedWords(): LiveData<List<nota>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert (nota: nota)

        @Query("DELETE FROM notas_table")
        suspend fun deleteAll()

}