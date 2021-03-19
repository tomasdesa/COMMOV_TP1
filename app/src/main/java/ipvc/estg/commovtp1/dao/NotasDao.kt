package ipvc.estg.commovtp1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.commovtp1.entities.nota

@Dao
interface NotasDao {

        @Query("SELECT * FROM notas_table ORDER BY id ASC")
        fun getAlphabetizedWords(): LiveData<List<nota>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert (nota: nota)

        @Query("DELETE FROM notas_table")
        suspend fun deleteAll()

        @Query("DELETE FROM notas_table where id = :id")
        suspend fun delete(id:Int)

        @Query("UPDATE  notas_table SET titulo = :titulo , descricao = :descricao where id = :id")
        suspend fun update(id:Int, titulo :String, descricao: String)

}