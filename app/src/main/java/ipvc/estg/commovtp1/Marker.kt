package ipvc.estg.commovtp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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


class Marker : AppCompatActivity() {

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
                        adapter = MarkerAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<List<marker>>, t: Throwable) {
                Toast.makeText(this@Marker, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })



    }

}