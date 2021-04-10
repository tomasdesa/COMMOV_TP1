package ipvc.estg.commovtp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.commovtp.R
import ipvc.estg.commovtp.entities.nota

class NotaAdapter  internal constructor(
        context: Context, var clickListener: OnNotaClickListener
    ) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var notas = emptyList<nota>() // Cached copy of cities

        class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val notaItemView: TextView = itemView.findViewById(R.id.titulo)
            val descricaoItemView: TextView = itemView.findViewById(R.id.Descricao)

            fun initialize(nota: nota, action:OnNotaClickListener){
                notaItemView.text=nota.titulo
                descricaoItemView.text=nota.descricao

                itemView.setOnClickListener{
                    action.onItemClick(nota, adapterPosition)
                }
            }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
            val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
            return NotaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
            val current = notas[position]
            holder.notaItemView.text =  current.titulo
            holder.descricaoItemView.text= current.descricao

            holder.initialize(notas.get(position),clickListener)

        }

        internal fun setNotas( notas : List<nota>) {
            this.notas = notas
            notifyDataSetChanged()
        }

        override fun getItemCount() =notas.size

    interface OnNotaClickListener{
        fun onItemClick(nota: nota, position: Int)
    }

}
