package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import de.foodbro.app.EditGraphArgs

import de.foodbro.app.R
import de.foodbro.app.databinding.FragmentRecipeEditBinding
import de.foodbro.app.ui.EventObserver
import kotlinx.android.synthetic.main.fragment_recipe_edit.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RecipeEditFragment : DaggerFragment() {

    companion object {
        private val TAB_TITLES = listOf("Summary", "Ingredients", "Preparation")
    }

    private val args: EditGraphArgs by navArgs()

    private lateinit var viewDataBinding: FragmentRecipeEditBinding

    @Inject
    lateinit var viewModel: RecipeEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditFragment.viewModel
            lifecycleOwner = this@RecipeEditFragment.viewLifecycleOwner
        }
        viewModel.setup(args.recipeId)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        setupViewPager()
    }

    private fun setupNavigation() {
        viewModel.recipeUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action = RecipeEditFragmentDirections.actionRecipeEditFragmentDestToRecipesFragmentDest()
            findNavController().navigate(action)
        })
    }

    private fun setupViewPager() {
        val viewPagerAdapter = createViewPagerAdapter()
        edit_view_pager.adapter = viewPagerAdapter

        TabLayoutMediator(edit_tab_layout, edit_view_pager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }

    private fun createViewPagerAdapter() = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when(position) {
            0 -> RecipeEditSummaryFragment()
            1 -> RecipeEditIngredientFragment()
            2 -> RecipeEditPreparationFragment()
            else -> throw IllegalArgumentException()
        }
    }

}