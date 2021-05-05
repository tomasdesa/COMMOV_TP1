package ipvc.estg.commovtp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp1.API.marker
import ipvc.estg.commovtp1.adapters.MarkerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Marker : AppCompatActivity(), MarkerAdapter.OnMarkerClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marker)

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val idUser = getIntent().getStringExtra("id_user")
        val id_user: Int = idUser!!.toInt()

        val call = request.getMarkerByIdUser(id_user)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclermarker)

        call.enqueue(object : Callback<List<marker>> {
            override fun onResponse(call: Call<List<marker>>, response: Response<List<marker>>) {
                if(response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager (this@Marker)
                        adapter = MarkerAdapter(response.body()!!,this@Marker)
                    }
                }
            }

            override fun onFailure(call: Call<List<marker>>, t: Throwable) {
                Toast.makeText(this@Marker, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        val add = findViewById<FloatingActionButton>(R.id.fab)

        add.setOnClickListener() {
            markerInicio(idUser)
        }



    }

    fun markerInicio(marker: String) {
        val intent = Intent(this, AddProblema::class.java)
        intent.putExtra("id_user", marker)
        startActivity(intent)
    }
    override fun onMarkerClick(marker: marker, position: Int) {
        //Toast.makeText(this, nota.titulo, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, EditMarker::class.java)
        intent.putExtra("id", marker.id)
        startActivity(intent)
    }

}