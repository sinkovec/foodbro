package de.foodbro.app.ui.edit

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import de.foodbro.app.model.Units

class UnitsAdapter(context: Context, layout: Int, var values: Array<Units>) :
    ArrayAdapter<Units>(context, layout, values) {

    private val filter = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            results.values = values
            results.count = values.size
            return results
        }
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}