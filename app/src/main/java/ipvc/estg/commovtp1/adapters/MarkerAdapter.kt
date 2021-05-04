package ipvc.estg.commovtp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.commovtp1.API.marker
import ipvc.estg.commovtp1.R


class MarkerAdapter(val marker: List<marker>, var clickListener: OnMarkerClickListener) : RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MarkerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclermarker, parent, false)
        return MarkerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marker.size
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        return holder.bind(marker[position],clickListener)
    }

    class MarkerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val titulo: TextView = itemView.findViewById(R.id.tituloedit)
        private val descricao : TextView = itemView.findViewById(R.id.descricaoedit)
        private val tipoProb: TextView = itemView.findViewById(R.id.tipoProb)

        fun bind(marker: marker, action: OnMarkerClickListener) {
            titulo.text = marker.titulo
            descricao.text = marker.descricao
            tipoProb.text = marker.tipoproblema

            itemView.setOnClickListener{
                action.onMarkerClick(marker, adapterPosition)
            }
        }


    }

    interface OnMarkerClickListener{
        fun onMarkerClick(marker: marker, position: Int)
    }

}


