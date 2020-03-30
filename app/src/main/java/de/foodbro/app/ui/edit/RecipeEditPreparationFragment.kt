package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import de.foodbro.app.databinding.FragmentRecipeEditPreparationBinding
import java.util.*
import javax.inject.Inject

class RecipeEditPreparationFragment : DaggerFragment() {

    companion object {
        fun newInstance() = RecipeEditPreparationFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[RecipeEditViewModel::class.java]
    }

    private lateinit var viewDataBinding: FragmentRecipeEditPreparationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            FragmentRecipeEditPreparationBinding.inflate(inflater, container, false).apply {
                viewModel = this@RecipeEditPreparationFragment.viewModel
                lifecycleOwner = this@RecipeEditPreparationFragment.viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listAdapter = PreparationsAdapter()

        val touchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                listAdapter.swap(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }

        })
        touchHelper.attachToRecyclerView(viewDataBinding.preparationsList)

        viewDataBinding.preparationsList.adapter = listAdapter
        viewModel.preparationSteps.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}
