package ipvc.estg.commovtp1.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.commovtp.API.Endpoints
import ipvc.estg.commovtp.API.ServiceBuilder
import ipvc.estg.commovtp1.API.marker
import ipvc.estg.commovtp1.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarkerAdapter(val marker: List<marker>) : RecyclerView.Adapter<MarkerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MarkerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclermarker, parent, false)
        return MarkerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marker.size
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        return holder.bind(marker[position])
    }
}

class MarkerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    private val titulo: TextView = itemView.findViewById(R.id.titulo)
    private val descricao : TextView = itemView.findViewById(R.id.descricao)
    private val tipoProb: TextView = itemView.findViewById(R.id.tipoProb)

    fun bind(marker: marker) {
        titulo.text = marker.titulo
        descricao.text = marker.descricao
        tipoProb.text = marker.tipoproblema
    }

}