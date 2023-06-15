package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.CrudIngredient
import com.example.myapplication.databinding.ActivityCategoriesBinding
import com.example.myapplication.databinding.ActivityRecipeRecommendBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class CategoriesActivity : AppCompatActivity() {

    private lateinit var request: Request

    enum class StorageArea(val displayName: String) {
        FREEZER("냉동실"),
        FRIDGE("냉장실"),
        ROOM_TEMP("실온")
    }

    data class Ingredients(
        val name: String,
        val category: String,
        val storageArea: StorageArea,
        val count: Int,
        val expirationDate: String
    )

    class MyApiClient {
        private val client = OkHttpClient()

        fun sendRequest(url: String, callback: Callback) {
            val request = Request.Builder()
                .addHeader("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2MjIyODM1LCJleHAiOjE2ODg4MTQ4MzV9.C3gcVa22eGBigQkGQZidRFL_isRlp8IhZKKmynH67XU")
                .url(url)
                .build()

            client.newCall(request).enqueue(callback)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityCategoriesBinding
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.carrot))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val underlineView = findViewById<View>(R.id.underline_view)

        val categoryVegetable = findViewById<TextView>(R.id.category_vegetable)
        val subcategoryLayoutVegetableGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_vegetable_group)

        val categoryFruit = findViewById<TextView>(R.id.category_fruit)
        val subcategoryLayoutFruitGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_fruit_group)

        val categoryMeat = findViewById<TextView>(R.id.category_meat)
        val subcategoryLayoutMeatGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_meat_group)

        val categorySeafood = findViewById<TextView>(R.id.category_seafood)
        val subcategoryLayoutSeaFoodGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_seafood_group)

        val categoryBeverage = findViewById<TextView>(R.id.category_Beverage)
        val subcategoryLayoutBeverageGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_beverage_group)

        val categoryAlcohol = findViewById<TextView>(R.id.category_Alcohol)
        val subcategoryLayoutAlcoholGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_alcohol_group)

        val categorySeasoning = findViewById<TextView>(R.id.category_Seasoning)
        val subcategoryLayoutSeasoningGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_seasoning_group)

        val categorySpice = findViewById<TextView>(R.id.category_Spice)
        val subcategoryLayoutSpiceGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_spice_group)

        val categoryDish = findViewById<TextView>(R.id.category_Dish)
        val subcategoryLayoutDishGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_dish_group)

        val categoryMilkProduct = findViewById<TextView>(R.id.category_MilkProduct)
        val subcategoryLayoutMilkProductGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_milkproduct_group)

        val categoryBakery = findViewById<TextView>(R.id.category_Bakery)
        val subcategoryLayoutBakeryGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_bakery_group)

        val categoryDessert = findViewById<TextView>(R.id.category_Dessert)
        val subcategoryLayoutDessertGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_dessert_group)

        val categoryNuts = findViewById<TextView>(R.id.category_Nuts)
        val subcategoryLayoutNutsGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_nuts_group)

        val categoryGrains = findViewById<TextView>(R.id.category_Grains)
        val subcategoryLayoutGrainsGroup =
            findViewById<LinearLayout>(R.id.subcategory_layout_grains_group)

        val categoryOther2 = findViewById<TextView>(R.id.category_Other2)
        val subcategoryLayoutOther2Group =
            findViewById<LinearLayout>(R.id.subcategory_layout_other2_group)



        underlineView.visibility = View.INVISIBLE
        subcategoryLayoutVegetableGroup.visibility = View.INVISIBLE
        subcategoryLayoutFruitGroup.visibility = View.INVISIBLE
        subcategoryLayoutMeatGroup.visibility = View.INVISIBLE
        subcategoryLayoutSeaFoodGroup.visibility = View.INVISIBLE
        subcategoryLayoutBeverageGroup.visibility = View.INVISIBLE
        subcategoryLayoutAlcoholGroup.visibility = View.INVISIBLE
        subcategoryLayoutSeasoningGroup.visibility = View.INVISIBLE
        subcategoryLayoutSpiceGroup.visibility = View.INVISIBLE
        subcategoryLayoutDishGroup.visibility = View.INVISIBLE
        subcategoryLayoutMilkProductGroup.visibility = View.INVISIBLE
        subcategoryLayoutBakeryGroup.visibility = View.INVISIBLE
        subcategoryLayoutDessertGroup.visibility = View.INVISIBLE
        subcategoryLayoutNutsGroup.visibility = View.INVISIBLE
        subcategoryLayoutGrainsGroup.visibility = View.INVISIBLE
        subcategoryLayoutOther2Group.visibility = View.INVISIBLE

        val categories = listOf(
            categoryVegetable, categoryFruit, categoryMeat, categorySeafood,
            categoryBeverage, categoryAlcohol, categorySeasoning, categorySpice,
            categoryDish, categoryMilkProduct, categoryBakery, categoryDessert,
            categoryNuts, categoryGrains, categoryOther2
        )

        val subcategoryLayouts = listOf(
            subcategoryLayoutVegetableGroup,
            subcategoryLayoutFruitGroup,
            subcategoryLayoutMeatGroup,
            subcategoryLayoutSeaFoodGroup,
            subcategoryLayoutBeverageGroup,
            subcategoryLayoutAlcoholGroup,
            subcategoryLayoutSeasoningGroup,
            subcategoryLayoutSpiceGroup,
            subcategoryLayoutDishGroup,
            subcategoryLayoutMilkProductGroup,
            subcategoryLayoutBakeryGroup,
            subcategoryLayoutDessertGroup,
            subcategoryLayoutNutsGroup,
            subcategoryLayoutGrainsGroup,
            subcategoryLayoutOther2Group
        )

        categories.forEachIndexed { index, category ->
            category.setOnClickListener {
                underlineView.visibility = View.VISIBLE
                subcategoryLayouts.forEachIndexed { layoutIndex, layout ->
                    layout.visibility = if (layoutIndex == index) View.VISIBLE else View.GONE
                }
            }
        }

        subcategoryLayouts.forEachIndexed { index, layout ->
            layout.setOnClickListener {

                val selectedIngredientTextView = categories[index]
                val selectedIngredient = selectedIngredientTextView.text?.toString() ?: ""
                val category = selectedIngredientTextView.text?.toString() ?: ""

                selectedIngredientTextView.contentDescription = category // 카테고리 정보를 contentDescription에 설정

                val intent = Intent(this, CrudIngredient::class.java)
                intent.putExtra("name", "귤")
                intent.putExtra("category", category)
                intent.putExtra("mode", "new")
                intent.putExtra("index", index) // Add index as an extra parameter
                startActivity(intent)
            }
        }

        val myApiClient = MyApiClient()

        val url = "https://mocum-project-gmck.vercel.app/api/recipe"

        val jsonString = readJsonFromFile("addMaterial.json")

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody: RequestBody = jsonString?.toRequestBody(mediaType) ?: "".toRequestBody(mediaType)

        request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()


        myApiClient.sendRequest(url, object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // 상태 코드 확인
                val statusCode = response.code
                Log.d("API Response", "Status code: $statusCode")

                // 응답 처리
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    Log.d("API Response", responseBody)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리

                e.printStackTrace()
            }
        })
    }

    private fun makeToast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (isTaskRoot) {
            // 현재 Activity가 앱의 첫 화면이 아닌 경우에만 이전 화면으로 돌아감
            onBackPressed()
        } else {
            // 앱의 첫 화면인 경우에는 현재 Activity를 종료하여 이전 화면으로 돌아감
            finish()
        }
        return true
    }
    private fun readJsonFromFile(fileName: String): String? {
        return try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}