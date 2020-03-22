package de.foodbro.app.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.foodbro.app.R
import de.foodbro.app.model.Ingredient


class IngredientsAdapter :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private var ingredients = emptyList<Ingredient>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ingredientTextView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item_detail_ingredient, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = ingredients[position]
        holder.ingredientTextView.text =
            listOfNotNull(current.quantity, current.unit, current.name).joinToString(" ")
        holder.ingredientTextView.setBackgroundResource(getBackgroundResource(position))
    }

    private fun getBackgroundResource(index: Int): Int {
        return if (isEven(index)) R.drawable.rounded_corner_gray else R.drawable.rounded_corner_white
    }

    private fun isEven(num: Int) = num % 2 == 0

    internal fun submitList(ingredients: List<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }
}