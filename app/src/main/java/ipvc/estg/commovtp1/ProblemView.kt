package ipvc.estg.commovtp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp1.API.marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProblemView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_view)

        val idmarker = getIntent().getStringExtra("id")
        val id: Int = idmarker!!.toInt()

        val titV = findViewById<TextView>(R.id.tituloeditview)
        val descV = findViewById<TextView>(R.id.descricaoeditview)
        val tipoProb = findViewById<TextView>(R.id.descricaoedit2view)
        val imgV = findViewById<ImageView>(R.id.imageView2)

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val getM = request.getMarkerById(id)


        getM.enqueue(object : Callback<marker> {
            override fun onResponse(call: Call<marker>, response: Response<marker>) {
                if (response.isSuccessful) {

                    val aux: marker = response.body()!!

                    titV.text = aux.titulo
                    descV.text = aux.descricao
                    tipoProb.text=aux.tipoproblema


                    Picasso.with(this@ProblemView)
                        .load("https://computacaotomas.000webhostapp.com/myslim/API/uploads/" + aux.imagem + ".png").into(imgV)
                }
            }

            override fun onFailure(call: Call<marker>, t: Throwable) {
                Toast.makeText(this@ProblemView, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
        val button_back=findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener{

            finish()
        }



    }
}