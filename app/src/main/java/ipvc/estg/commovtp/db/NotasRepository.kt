package ipvc.estg.commovtp.db

import androidx.lifecycle.LiveData
import ipvc.estg.commovtp.dao.NotasDao
import ipvc.estg.commovtp.entities.nota

class NotasRepository(private val NotasDao: NotasDao) {

    val allNotas: LiveData<List<nota>> = NotasDao.getAlphabetizedWords()

    suspend fun insert(nota: nota){
        NotasDao.insert(nota)
    }

    suspend fun delete(id:Int){
        NotasDao.delete(id)
    }
    suspend fun update(id:Int, titulo :String, descricao: String){
        NotasDao.update(id, titulo, descricao)
    }
}