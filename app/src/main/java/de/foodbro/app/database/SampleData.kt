package de.foodbro.app.database

import android.content.ContentValues
import androidx.core.content.contentValuesOf
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.foodbro.app.model.Ingredient
import de.foodbro.app.model.Recipe
import java.util.stream.Collectors

fun chiliConCarneContentValues(): ContentValues {
    return getSampleRecipeData().stream().map {
        contentValuesOf(Pair("name", it.name),
            Pair("description", it.description),
            Pair("portions", it.portions),
            Pair("difficulty", it.difficulty),
            Pair("preparationTime", it.preparationTime),
            Pair("preparationSteps", chiliConCarnePreparation()))
    }.reduce(ContentValues()) { _,b -> b }
}

fun chiliConCarneIngredientsContentValues(recipeId: Int): List<ContentValues> {
    return getSampleIngredientData().stream().map {
        contentValuesOf(Pair("name", it.name),
            Pair("quantity", it.quantity),
            Pair("unit", it.unit?.id),
            Pair("recipeId", recipeId))
    }.collect(Collectors.toList())
}

fun getSampleRecipeData(): List<Recipe> {
    val mapper = jacksonObjectMapper()
    return listOf(mapper.readValue(chiliConCarne(), Recipe::class.java))
}

fun getSampleIngredientData(): List<Ingredient> {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(chiliConCarneIngredients(),
        mapper.typeFactory.constructCollectionType(List::class.java, Ingredient::class.java))
}

fun chiliConCarne(): String {
    return """
        {
            "name": "Chili Con Carne",
            "description": "Das ist eine Beschreibung für chili con carne",
            "portions": 4,
            "difficulty": 1,
            "preparationTime": 30,
            "preparationSteps": []
        }
    """
}

fun chiliConCarnePreparation(): String {
    return """
        [
            "Die Zwiebeln abziehen, in Würfel schneiden und in einem tiefen Topf oder Bräter im Öl goldgelb anbraten. Hackfleisch zufügen, gut anbraten und Farbe nehmen lassen. Dabei ab und zu umrühren und das Hackfleisch zerkleinern.", 
            "Paprika putzen, in Würfel schneiden und zum Hackfleisch geben. Tomatenmark zufügen und etwas anrösten. Die Tomaten, den gepellten und zerkleinerten Knoblauch sowie Gewürze (Zucker, Salz, Pfeffer, Paprika, Tabasco, Chili oder Cayenne) zugeben. Allerdings lieber erst einmal etwas vorsichtiger würzen und gegebenenfalls nach der Kochzeit nachwürzen. Mit Brühe auffüllen und bei mittlerer Hitze einkochen lassen, ist die Flüssigkeit verkocht, immer wieder Brühe angießen.", 
            "Kurz vor Ende der Garzeit Bohnen und Mais aus der Dose befreien, gründlich abspülen und zufügen. Diese nur kurz wenige Minuten mitgaren. Anschließend alles noch einmal abschmecken und gegebenenfalls nachwürzen. Am besten schmeckt ein Chili gut durchgezogen, ist also wunderbar am Tag vorher vorzubereiten!", 
            "Anrichten: Chili con Carne in einem tiefen Teller oder einer Schüssel servieren. Dazu passt ein knuspriges Baguette, Tortillas oder Nachos.", 
            "Tipps: Wahlweise kann das Chili auch mit klein geschnittenem Fleisch und frischen Chilischoten zubereitet werden.\nWer seinem \"normalen\" Chili einmal eine etwas andere Geschmacksrichtung geben möchte, verfeinert das obige Rezept mit einem Teelöffel Kreuzkümmel, einem Esslöffel dunklem Kakaopulver und einem doppelten Espresso! Guten Appetit!"
        ]
    """
}

fun chiliConCarneIngredients(): String {
    return """
        [
            {
              "name": "Rinderhackfleisch",
              "quantity": 800,
              "unit": "GRAM",
              "recipeId": 0
            },
            {
              "name": "Zwiebeln",
              "quantity": 2,
              "unit": "PIECE",
              "recipeId": 0
            },
            {
              "name": "Knoblauch",
              "quantity": 2,
              "unit": "PIECE",
              "recipeId": 0
            },
            {
              "name": "Paprikaschote",
              "quantity": 1,
              "recipeId": 0
            },
            {
              "name": "Tomatenmark",
              "quantity": 2,
              "unit": "TABLESPOON",
              "recipeId": 0
            },
            {
              "name": "Tomaten",
              "quantity": 1,
              "unit": "CAN",
              "recipeId": 0
            },
            {
              "name": "Kidneybohnen",
              "quantity": 1,
              "unit": "CAN",
              "recipeId": 0
            },
            {
              "name": "Mais",
              "quantity": 1,
              "unit": "CAN",
              "recipeId": 0
            },
            {
              "name": "Cayennepfeffer",
              "recipeId": 0
            },
            {
              "name": "Paprikapulver",
              "recipeId": 0
            },
            {
              "name": "Chilipulver",
              "recipeId": 0
            },
            {
              "name": "Salz und Pfeffer",
              "recipeId": 0
            },
            {
              "name": "Zucker",
              "recipeId": 0
            },
            {
              "name": "Tabasco",
              "recipeId": 0
            }
        ] 
    """
}