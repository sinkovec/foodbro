package de.foodbro.app.ui.edit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.Units
import de.foodbro.app.repository.RecipeDetailRepository
import de.foodbro.app.util.LongArg
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeEditViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: RecipeDetailRepository

    @InjectMocks
    private lateinit var viewModel: RecipeEditViewModel

    private val testRecipe = Recipe(id=0, name="TestRecipe")

    private val testIngredient = Ingredient(id=0, name="TestIngredient", unit=Units.KILOGRAM, quantity=2)

//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }

    @Test
    fun `test setup of ViewModel that name is set`() = runBlockingTest {
        // given
        `when`(repository.getRecipeById(ArgumentMatchers.anyLong()))
            .thenReturn(testRecipe)

        // when
        viewModel.start(LongArg(0))

        // then
        // assertThat(viewModel.name, `is`(equalTo(recipe.name)))
        assertNotNull(viewModel.recipeDetail.value)
        assertEquals(viewModel.recipeDetail.value?.name, testRecipe.name)
    }

    @Test
    fun `test setup of ViewModel that ingredients are set`() = runBlockingTest {
        // given
        `when`(repository.getRecipeById(ArgumentMatchers.anyLong()))
            .thenReturn(testRecipe)
        `when`(repository.getIngredientsByRecipeId(ArgumentMatchers.anyLong()))
            .thenReturn(listOf(testIngredient))

        // when
        viewModel.start(LongArg(0))

        // then
        assertNotNull(viewModel.ingredients.value)
        assertEquals(viewModel.ingredients.value?.size, 1)
        val ingredient = viewModel.ingredients.value?.get(0)
        assertEquals(ingredient, testIngredient)
    }


}