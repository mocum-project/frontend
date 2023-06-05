package com.example.materialregistration

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.orange))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val underlineView = findViewById<View>(R.id.underline_view)

        val categoryVegetable = findViewById<TextView>(R.id.category_vegetable)
        val subcategoryLayoutVegetableGroup = findViewById<LinearLayout>(R.id.subcategory_layout_vegetable_group)

        val categoryFruit = findViewById<TextView>(R.id.category_fruit)
        val subcategoryLayoutFruitGroup = findViewById<LinearLayout>(R.id.subcategory_layout_fruit_group)

        val categoryMeat = findViewById<TextView>(R.id.category_meat)
        val subcategoryLayoutMeatGroup = findViewById<LinearLayout>(R.id.subcategory_layout_meat_group)

        val categorySeafood = findViewById<TextView>(R.id.category_seafood)
        val subcategoryLayoutSeaFoodGroup = findViewById<LinearLayout>(R.id.subcategory_layout_seafood_group)

        val categoryBeverage = findViewById<TextView>(R.id.category_Beverage)
        val subcategoryLayoutBeverageGroup = findViewById<LinearLayout>(R.id.subcategory_layout_beverage_group)

        val categoryAlcohol = findViewById<TextView>(R.id.category_Alcohol)
        val subcategoryLayoutAlcoholGroup = findViewById<LinearLayout>(R.id.subcategory_layout_alcohol_group)

        val categorySeasoning = findViewById<TextView>(R.id.category_Seasoning)
        val subcategoryLayoutSeasoningGroup = findViewById<LinearLayout>(R.id.subcategory_layout_seasoning_group)

        val categorySpice = findViewById<TextView>(R.id.category_Spice)
        val subcategoryLayoutSpiceGroup = findViewById<LinearLayout>(R.id.subcategory_layout_spice_group)

        val categoryDish = findViewById<TextView>(R.id.category_Dish)
        val subcategoryLayoutDishGroup = findViewById<LinearLayout>(R.id.subcategory_layout_dish_group)

        val categoryMilkProduct = findViewById<TextView>(R.id.category_MilkProduct)
        val subcategoryLayoutMilkProductGroup = findViewById<LinearLayout>(R.id.subcategory_layout_milkproduct_group)

        val categoryBakery = findViewById<TextView>(R.id.category_Bakery)
        val subcategoryLayoutBakeryGroup = findViewById<LinearLayout>(R.id.subcategory_layout_bakery_group)

        val categoryDessert = findViewById<TextView>(R.id.category_Dessert)
        val subcategoryLayoutDessertGroup = findViewById<LinearLayout>(R.id.subcategory_layout_dessert_group)

        val categoryNuts = findViewById<TextView>(R.id.category_Nuts)
        val subcategoryLayoutNutsGroup = findViewById<LinearLayout>(R.id.subcategory_layout_nuts_group)

        val categoryGrains = findViewById<TextView>(R.id.category_Grains)
        val subcategoryLayoutGrainsGroup = findViewById<LinearLayout>(R.id.subcategory_layout_grains_group)

        val categoryOther2 = findViewById<TextView>(R.id.category_Other2)
        val subcategoryLayoutOther2Group = findViewById<LinearLayout>(R.id.subcategory_layout_other2_group)

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

        categoryVegetable.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.VISIBLE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryFruit.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutFruitGroup.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryMeat.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.VISIBLE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categorySeafood.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.VISIBLE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryBeverage.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.VISIBLE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryAlcohol.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.VISIBLE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categorySeasoning.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.VISIBLE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categorySpice.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.VISIBLE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryDish.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.VISIBLE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryMilkProduct.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.VISIBLE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryBakery.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.VISIBLE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryDessert.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.VISIBLE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryNuts.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.VISIBLE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryGrains.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.VISIBLE
            subcategoryLayoutOther2Group.visibility = View.GONE
        }
        categoryOther2.setOnClickListener {
            underlineView.visibility = View.VISIBLE
            subcategoryLayoutVegetableGroup.visibility = View.GONE
            subcategoryLayoutFruitGroup.visibility = View.GONE
            subcategoryLayoutMeatGroup.visibility = View.GONE
            subcategoryLayoutSeaFoodGroup.visibility = View.GONE
            subcategoryLayoutBeverageGroup.visibility = View.GONE
            subcategoryLayoutAlcoholGroup.visibility = View.GONE
            subcategoryLayoutSeasoningGroup.visibility = View.GONE
            subcategoryLayoutSpiceGroup.visibility = View.GONE
            subcategoryLayoutDishGroup.visibility = View.GONE
            subcategoryLayoutMilkProductGroup.visibility = View.GONE
            subcategoryLayoutBakeryGroup.visibility = View.GONE
            subcategoryLayoutDessertGroup.visibility = View.GONE
            subcategoryLayoutNutsGroup.visibility = View.GONE
            subcategoryLayoutGrainsGroup.visibility = View.GONE
            subcategoryLayoutOther2Group.visibility = View.VISIBLE
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "menu1")
        menu?.add(0, 1, 0, "menu2")
        menu?.add(0, 2, 0, "menu3")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Log.d(TAG, "item1 clicked")
            }
            R.id.item2 -> {
                Log.d(TAG, "item1 clicked")
            }
            R.id.item3 -> {
                Log.d(TAG, "item1 clicked")
            }

        }
        return super.onOptionsItemSelected(item)
    }
}