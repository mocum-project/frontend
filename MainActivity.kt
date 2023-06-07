package com.example.materialregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                val intent = Intent(this, NextActivity::class.java)
                val selectedIngredient = categories[index]
                intent.putExtra("ingredient", selectedIngredient.text.toString())
                startActivity(intent)
            }
        }
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
}