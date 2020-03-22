package de.foodbro.app.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.R


class PreparationsAdapter :
    RecyclerView.Adapter<PreparationsAdapter.ViewHolder>() {

    private var preparationSteps = emptyList<String>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val preparationStepItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item_detail_preparation, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = preparationSteps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = preparationSteps[position]
        holder.preparationStepItemView.text = current
    }

    internal fun submitList(preparationSteps: List<String>) {
        this.preparationSteps = preparationSteps
        notifyDataSetChanged()
    }
}