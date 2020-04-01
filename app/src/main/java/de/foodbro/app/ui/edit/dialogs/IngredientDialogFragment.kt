package de.foodbro.app.ui.edit.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentIngredientDialogBinding
import de.foodbro.app.model.Units
import de.foodbro.app.ui.edit.RecipeEditViewModel
import kotlinx.android.synthetic.main.fragment_ingredient_dialog.*

class IngredientDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(vm: RecipeEditViewModel) = IngredientDialogFragment().apply {
            viewModel = vm
        }
    }

    private lateinit var viewModel: RecipeEditViewModel

    private lateinit var viewDataBinding: FragmentIngredientDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentIngredientDialogBinding.inflate(inflater, container, false).apply {
            viewModel = this@IngredientDialogFragment.viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = UnitsAdapter(requireContext(), R.layout.list_item_edit_ingredient_units, Units.values())
        viewDataBinding.unitDropdown.setAdapter(adapter)
    }
}


