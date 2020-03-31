package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import de.foodbro.app.databinding.FragmentRecipeEditPreparationBinding
import de.foodbro.app.ui.EventObserver
import javax.inject.Inject

class RecipeEditPreparationFragment : DaggerFragment() {

    companion object {
        fun newInstance() = RecipeEditPreparationFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireParentFragment(), viewModelFactory)[RecipeEditViewModel::class.java]
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
        viewDataBinding.preparationsList.adapter = listAdapter
        viewModel.preparationSteps.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

        viewModel.addedPreparationStepEvent.observe(viewLifecycleOwner, EventObserver {
            viewDataBinding.preparationsList.smoothScrollToPosition(listAdapter.itemCount)
        })

//        val touchHelper = ItemTouchHelper(object :
//            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
//            override fun onMove(
//                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                val fromPosition = viewHolder.adapterPosition
//                val toPosition = target.adapterPosition
//                listAdapter.notifyItemMoved(fromPosition, toPosition)
//                viewModel.onPreparationStepMoved(fromPosition, toPosition)
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            }
//        })
//        touchHelper.attachToRecyclerView(viewDataBinding.preparationsList)
    }
}
