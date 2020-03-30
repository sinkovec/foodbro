package de.foodbro.app.ui.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import de.foodbro.app.R

import de.foodbro.app.databinding.FragmentRecipeEditBinding
import de.foodbro.app.ui.EventObserver
import kotlinx.android.synthetic.main.fragment_recipe_edit.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RecipeEditFragment : DaggerFragment() {

    companion object {
        private val TAB_TITLES = listOf(
            R.string.recipe_edit_tab_summary,
            R.string.recipe_edit_tab_ingredients,
            R.string.recipe_edit_tab_preparation)
    }

    private val args: RecipeEditFragmentArgs by navArgs()

    private lateinit var viewDataBinding: FragmentRecipeEditBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[RecipeEditViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRecipeEditBinding.inflate(inflater, container, false).apply {
            viewModel = this@RecipeEditFragment.viewModel
            lifecycleOwner = this@RecipeEditFragment.viewLifecycleOwner
        }
        viewModel.setup(args.recipeId)

        setHasOptionsMenu(true)

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        edit_view_pager.offscreenPageLimit = TAB_TITLES.size
        edit_view_pager.adapter = getViewPagerAdapter()

        TabLayoutMediator(tab_layout, edit_view_pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
    }

    private fun getViewPagerAdapter() = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when(position) {
            0 -> RecipeEditSummaryFragment.newInstance()
            1 -> RecipeEditIngredientFragment.newInstance()
            2 -> RecipeEditPreparationFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save_recipe -> {
                viewModel.saveRecipe()
                true
            }
            else -> false
        }
    }

}