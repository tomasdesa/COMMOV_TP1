package ipvc.estg.commovtp1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import ipvc.estg.commovtp1.entities.nota

class Editar : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        val id =getIntent().getStringExtra("id")
        val titulo = getIntent().getStringExtra("titulo")
        val descricao = getIntent().getStringExtra("descricao")

       val tituloText = findViewById<TextView>(R.id.titulo)
        tituloText.text=titulo

        val descricaoText = findViewById<TextView>(R.id.descricao)
        descricaoText.text=descricao






        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(tituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_titulo, tituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_descricao, descricaoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_titulo = "com.example.android.titulo"
        const val EXTRA_REPLY_descricao = "com.example.android.descricao"
    }



}