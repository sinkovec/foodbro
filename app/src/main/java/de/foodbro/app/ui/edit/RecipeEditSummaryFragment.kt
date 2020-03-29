package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditSummaryBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.edit.dialogs.DurationPickerDialogFragment
import javax.inject.Inject

class RecipeEditSummaryFragment : Fragment() {

    lateinit var viewModel: RecipeEditViewModel

    private lateinit var viewDataBinding: FragmentRecipeEditSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditSummaryBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditSummaryFragment.viewModel
            lifecycleOwner = this@RecipeEditSummaryFragment.viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openTimePickerDialogEvent.observe(viewLifecycleOwner, EventObserver {
            DurationPickerDialogFragment.newInstance(viewModel).show(parentFragmentManager, "dialog")
        })
    }
}
