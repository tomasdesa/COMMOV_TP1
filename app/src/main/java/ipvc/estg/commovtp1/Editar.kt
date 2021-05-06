package ipvc.estg.commovtp1

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ipvc.estg.commovtp1.viewmodel.NotaViewModel

class Editar : AppCompatActivity() {


    private lateinit var notaViewModel: NotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        val id =getIntent().getIntExtra("id",0)
        val titulo = getIntent().getStringExtra("titulo")
        val descricao = getIntent().getStringExtra("descricao")


       val tituloText = findViewById<TextView>(R.id.tituloedit)
        tituloText.text=titulo

        val descricaoText = findViewById<TextView>(R.id.descricaoedit)
        descricaoText.text=descricao


        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)





        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val titulofinal = tituloText.text.toString()
            val descricaofinal = descricaoText.text.toString()

            if (isNullOrEmpty(titulofinal)|| isNullOrEmpty(descricaofinal) ) {
                Toast.makeText(
                        applicationContext,
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show()

            } else {
                notaViewModel.update(id, titulofinal, descricaofinal)

                finish()

            }
        }

        val Apagar = findViewById<Button>(R.id.ApagarMarker)
        Apagar.setOnClickListener {
            val AlertaApagar = AlertDialog.Builder(this)
            AlertaApagar.setTitle(getString(R.string.apagar_nota))
            AlertaApagar.setMessage(getString(R.string.mensagem_apagar))
            AlertaApagar.setPositiveButton(getString(R.string.sim)){dialog: DialogInterface?, which: Int ->
                notaViewModel.delete(id)
                finish()
            }

            AlertaApagar.setNegativeButton(getString(R.string.nao)){ dialog, id ->
                dialog.dismiss()
            }
            AlertaApagar.show()

        }
        val button_back=findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener{
            finish()
        }
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.trim().isEmpty())
            return false
        return true
    }



}