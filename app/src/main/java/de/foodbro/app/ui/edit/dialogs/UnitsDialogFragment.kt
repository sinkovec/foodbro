package de.foodbro.app.ui.edit.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import de.foodbro.app.databinding.FragmentUnitsDialogBinding
import de.foodbro.app.model.Units
import de.foodbro.app.ui.edit.RecipeEditViewModel
import kotlinx.android.synthetic.main.fragment_units_dialog.*

class UnitsDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(vm: RecipeEditViewModel) = UnitsDialogFragment().apply {
            viewModel = vm
        }
    }

    private lateinit var viewModel: RecipeEditViewModel

    private lateinit var viewDataBinding: FragmentUnitsDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentUnitsDialogBinding.inflate(inflater, container, false).apply {
            viewModel = this@UnitsDialogFragment.viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectedIngredient.observe(viewLifecycleOwner, Observer {
            units_dialog_toggle_btn_mg.isChecked = viewModel.isChecked(Units.MILLIGRAM)
            units_dialog_toggle_btn_g.isChecked = viewModel.isChecked(Units.GRAM)
            units_dialog_toggle_btn_kg.isChecked = viewModel.isChecked(Units.KILOGRAM)

            units_dialog_toggle_btn_ml.isChecked = viewModel.isChecked(Units.MILLILITRE)
            units_dialog_toggle_btn_l.isChecked = viewModel.isChecked(Units.LITRE)

            units_dialog_toggle_btn_t.isChecked = viewModel.isChecked(Units.TEASPOON)
            units_dialog_toggle_btn_tbsp.isChecked = viewModel.isChecked(Units.TABLESPOON)
        })
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.closeBottomSheet()
    }
}
