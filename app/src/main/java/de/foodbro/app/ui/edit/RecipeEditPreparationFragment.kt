package de.foodbro.app.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import de.foodbro.app.databinding.FragmentRecipeEditPreparationBinding
import javax.inject.Inject

class RecipeEditPreparationFragment : DaggerFragment() {

    lateinit var viewModel: RecipeEditViewModel

    private lateinit var viewDataBinding: FragmentRecipeEditPreparationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditPreparationBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditPreparationFragment.viewModel
            lifecycleOwner = this@RecipeEditPreparationFragment.viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listAdapter = PreparationsAdapter()
        viewDataBinding.preparationsList.adapter = listAdapter
        viewModel.preparationSteps.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}
