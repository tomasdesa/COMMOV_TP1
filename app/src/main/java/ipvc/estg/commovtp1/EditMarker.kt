package ipvc.estg.commovtp1

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Output
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp1.API.OutputPost
import ipvc.estg.commovtp1.API.Outputmarker
import ipvc.estg.commovtp1.API.marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditMarker : AppCompatActivity() {

    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_marker)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val id_marker = getIntent().getIntExtra("id",0)

        val titulo = findViewById<TextView>(R.id.tituloedit)
        val descricao = findViewById<TextView>(R.id.descricaoedit)
        val latitude = findViewById<TextView>(R.id.latitudeedit)
        val longitude = findViewById<TextView>(R.id.longitudeedit)
        val imagem = findViewById<TextView>(R.id.imagemedit)


        Toast.makeText(this@EditMarker, id_marker.toString(), Toast.LENGTH_SHORT).show()


        val request = ServiceBuilder.buildService(Endpoints::class.java)


        val call = request.getMarkerById(id_marker)

        call.enqueue(object : Callback<marker> {
            override fun onResponse(call: Call<marker>, response: Response<marker>) {
                if (response.isSuccessful) {

                    val c: marker = response.body()!!

                    Toast.makeText(this@EditMarker, id_marker.toString(), Toast.LENGTH_SHORT).show()

                    titulo.text = c.titulo
                    descricao.text = c.descricao
                    latitude.text = c.latitude
                    longitude.text = c.longitude
                    imagem.text = c.imagem


                }
            }
            override fun onFailure(call: Call<marker>, t: Throwable) {
                Toast.makeText(this@EditMarker, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        val butCorde = findViewById<Button>(R.id.coordenadasedit)

        butCorde.setOnClickListener() {
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            } else {

                fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                    if(location != null) {
                        lastLocation = location

                        val lat = findViewById<TextView>(R.id.latitudeedit)
                        val long = findViewById<TextView>(R.id.longitudeedit)

                        lat.text = location.latitude.toString()
                        long.text = location.longitude.toString()
                    }
                }
            }
        }

        val imageButton = findViewById<Button>(R.id.imagemedit)
        imageButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, EditMarker.PERMISSION_CODE);
                }
                else {
                    pickImageFromGallery();
                }
            }else {
                pickImageFromGallery()
            }
        }

        val guardar = findViewById<Button>(R.id.guardar1edit)

        guardar.setOnClickListener() {

            var ola = "ola"

            val spinner: Spinner = findViewById(R.id.spinner2edit)
            // Create an ArrayAdapter using the string array and a default spinner layout


            ola = spinner.getSelectedItem().toString()

            val request = ServiceBuilder.buildService(Endpoints::class.java)


            val tituloText = titulo.text.toString()
            val descricaoText = descricao.text.toString()

            val longitudeText = longitude.text.toString()
            val longDou = longitudeText.toDouble()

            val latitudeText = latitude.text.toString()
            val latDou = latitudeText.toDouble()

            val imagem = "teste"

            val call = request.updateMarker(tituloText, descricaoText, longDou, latDou, imagem, ola)

            call.enqueue(object : Callback<marker> {
                override fun onResponse(call: Call<marker>, response: Response<marker>) {
                    if (response.isSuccessful) {

                        Toast.makeText(this@EditMarker,"Está a dar",Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<marker>, t: Throwable) {
                    Toast.makeText(this@EditMarker, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }

        val Apagar = findViewById<Button>(R.id.Apagar)
        Apagar.setOnClickListener {
            val AlertaApagar = AlertDialog.Builder(this)
            AlertaApagar.setTitle(getString(R.string.apagar_nota))
            AlertaApagar.setMessage(getString(R.string.mensagem_apagar))
            AlertaApagar.setPositiveButton(getString(R.string.sim)){ dialog: DialogInterface?, which: Int ->

                val call = request.DeleteMarker(id_marker)

                call.enqueue(object : Callback<Outputmarker> {
                    override fun onResponse(call: Call<Outputmarker>, response: Response<Outputmarker>) {
                        if (response.isSuccessful) {

                            Toast.makeText(this@EditMarker,"Está a dar",Toast.LENGTH_SHORT).show()

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
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val image = findViewById<ImageView>(R.id.imageView)

            image.setImageURI(data?.data)
        }
    }


}