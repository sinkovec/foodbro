package de.foodbro.app.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import de.foodbro.app.model.Unit
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class IngredientReadWriteTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<List<Ingredient>>

    private lateinit var recipeDao: RecipeDao
    private lateinit var ingredientDao: IngredientDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        // setup db
        val context = InstrumentationRegistry.getInstrumentation().context
        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                 .allowMainThreadQueries().build()
        recipeDao = db.recipeDao()
        ingredientDao = db.ingredientDao()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertIngredients() {
        // given
        val recipe = createRecipe()
        recipeDao.insert(recipe)
        val ingredients = createIngredients()

        // when
        ingredientDao.insertAllForRecipe(recipe, ingredients)
        val storedIngredients = ingredientDao.getAll()
        storedIngredients.observeForever(observer)
        val values = storedIngredients.value!!

        // then
        assertThat(values.size, equalTo(2))
        assertThat(values[0].recipeId, equalTo(recipe.id))
        assertThat(values[1].recipeId, equalTo(recipe.id))
    }

    private fun createRecipe(): Recipe {
        return Recipe("Test Name", "Test Description", 2, listOf("Test step 1", "test step 2"))
    }

    private fun createIngredients(): List<Ingredient> {
        return listOf(
            Ingredient("ingredient1", 2, Unit.MILLIGRAM),
            Ingredient("ingredient2"))
    }

}