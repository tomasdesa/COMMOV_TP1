package ipvc.estg.commovtp1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp1.API.Outputmarker
import ipvc.estg.commovtp1.API.marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditMarker : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_marker)

        val idM = getIntent().getIntExtra("id", 0)
        val id_user = getIntent().getStringExtra("id_user")

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val getM = request.getMarkerById(idM)

        val titE = findViewById<TextView>(R.id.tituloedit)
        val descE = findViewById<TextView>(R.id.descricaoedit)


        getM.enqueue(object : Callback<marker> {
            override fun onResponse(call: Call<marker>, response: Response<marker>) {
                if (response.isSuccessful) {

                    val aux: marker = response.body()!!

                    titE.text = aux.titulo
                    descE.text = aux.descricao
                }
            }

            override fun onFailure(call: Call<marker>, t: Throwable) {
                Toast.makeText(this@EditMarker, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })



        val guardar = findViewById<Button>(R.id.guardar1edit)

        guardar.setOnClickListener() {


            var ola = "ola"

            val spinner: Spinner = findViewById(R.id.spinner2edit)
            // Create an ArrayAdapter using the string array and a default spinner layout


            ola = spinner.getSelectedItem().toString()


            val titulo = findViewById<TextView>(R.id.tituloedit)
            val tituloText = titulo.text.toString()

            val descricao = findViewById<TextView>(R.id.descricaoedit)
            val descricaoText = descricao.text.toString()
            if(tituloText == "") {
                Toast.makeText(
                        applicationContext,
                        getString(R.string.addtit),
                        Toast.LENGTH_SHORT).show()

            }
            else if(descricaoText == "") {
                Toast.makeText(
                        applicationContext,
                        getString(R.string.addDes),
                        Toast.LENGTH_SHORT).show()
            }
            else {

                val call = request.updateMarker(idM, tituloText, descricaoText, ola)

                call.enqueue(object : Callback<Outputmarker> {
                    override fun onResponse(call: Call<Outputmarker>, response: Response<Outputmarker>) {
                        if (response.isSuccessful) {

                            val c: Outputmarker = response.body()!!
                            Toast.makeText(this@EditMarker, c.MSG, Toast.LENGTH_SHORT).show()
                            markerInicio(id_user)
                        }
                    }

                    override fun onFailure(call: Call<Outputmarker>, t: Throwable) {
                        Toast.makeText(this@EditMarker, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
            }


        }
        val button_back=findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener{

            markerInicio(id_user)
        }
        val Apagar = findViewById<Button>(R.id.ApagarMarker)
        Apagar.setOnClickListener {
            val AlertaApagar = AlertDialog.Builder(this)
            AlertaApagar.setTitle(getString(R.string.apagar_marker))
            AlertaApagar.setMessage(getString(R.string.mensagem_apagarProblema))
            AlertaApagar.setPositiveButton(getString(R.string.sim)){dialog: DialogInterface?, which: Int ->
                val call = request.DeleteMarker(idM)

                call.enqueue(object : Callback<Outputmarker> {
                    override fun onResponse(call: Call<Outputmarker>, response: Response<Outputmarker>) {
                        if (response.isSuccessful) {

                            val c: Outputmarker = response.body()!!
                            Toast.makeText(this@EditMarker,c.MSG, Toast.LENGTH_SHORT).show()
                            markerInicio(id_user)
                        }
                    }

                    override fun onFailure(call: Call<Outputmarker>, t: Throwable) {
                        Toast.makeText(this@EditMarker, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })

                finish()
            }

            AlertaApagar.setNegativeButton(getString(R.string.nao)){ dialog, id ->
                dialog.dismiss()
            }
            AlertaApagar.show()

        }

    }

    fun markerInicio(user: String?) {
        val intent = Intent(this, Marker::class.java)
        intent.putExtra("id_user", user)
        startActivity(intent)
        finish()
    }
}