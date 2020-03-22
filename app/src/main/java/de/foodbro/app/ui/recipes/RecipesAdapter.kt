package de.foodbro.app.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.R
import de.foodbro.app.model.Recipe

class RecipesAdapter(val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    var recipes = emptyList<Recipe>()

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item_recipes, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = recipes[position]
        holder.textView.text = current.name
    }

    internal fun submitList(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }
}