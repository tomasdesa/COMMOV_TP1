package ipvc.estg.commovtp1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.commovtp1.Notas
import ipvc.estg.commovtp1.dao.NotasDao
import ipvc.estg.commovtp1.db.NotaDB
import ipvc.estg.commovtp1.db.NotasRepository
import ipvc.estg.commovtp1.entities.nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel (application: Application) : AndroidViewModel(application) {

        private val repository: NotasRepository
        // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
        // - We can put an observer on the data (instead of polling for changes) and only update the
        //   the UI when the data actually changes.
        // - Repository is completely separated from the UI through the ViewModel.
        val allNotas: LiveData<List<nota>>

        init {
            val notasDao= NotaDB.getDatabase(application, viewModelScope).NotasDao()
            repository = NotasRepository(notasDao)
            allNotas = repository.allNotas
        }

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(nota: nota) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(nota)
        }
}