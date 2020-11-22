package de.foodbro.app.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import de.foodbro.app.data.AppDatabase
import de.foodbro.app.data.Recipe
import de.foodbro.app.util.RECIPE_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(RECIPE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val recipeType = object : TypeToken<List<Recipe>>() {}.type
                    val recipeList: List<Recipe> = Gson().fromJson(jsonReader, recipeType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.recipeDao().insertAll(recipeList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}