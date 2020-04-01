package de.foodbro.app.ui.edit.dialogs

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import de.foodbro.app.model.Units

class UnitsAdapter(context: Context, layout: Int, var values: Array<Units>) :
    ArrayAdapter<Units>(context, layout, values) {

    // use a 'non-filtering' filter
    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?) = FilterResults().apply {
            values = this@UnitsAdapter.values
            count = this@UnitsAdapter.values.size
        }
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) =
            notifyDataSetChanged()
    }

    override fun getFilter() = filter
}