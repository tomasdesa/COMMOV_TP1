package ipvc.estg.commovtp1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ipvc.estg.commovtp1.adapters.NotaAdapter
import ipvc.estg.commovtp1.entities.nota
import ipvc.estg.commovtp1.viewmodel.NotaViewModel

class Editar : AppCompatActivity() {


    private lateinit var notaViewModel: NotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        val id =getIntent().getIntExtra("id",0)
        val titulo = getIntent().getStringExtra("titulo")
        val descricao = getIntent().getStringExtra("descricao")

       val tituloText = findViewById<TextView>(R.id.titulo)
        tituloText.text=titulo

        val descricaoText = findViewById<TextView>(R.id.descricao)
        descricaoText.text=descricao


        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)





        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val titulofinal = tituloText.text.toString()
            val descricaofinal = descricaoText.text.toString()

            notaViewModel.update(id, titulofinal, descricaofinal)

            finish()
        }

        val Apagar = findViewById<Button>(R.id.Apagar)
        Apagar.setOnClickListener {
            notaViewModel.delete(id)
            finish()
        }
    }





}