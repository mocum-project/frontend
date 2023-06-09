package com.example.myapplication

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityRecipeRecommendBinding

import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException



class RecipeRecommend : AppCompatActivity() {
    lateinit var binding: ActivityRecipeRecommendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        removeLoading()
        getRecipes(0) { recipes ->
            addRecipesLayout(recipes)
        }

        binding.moreBtn.setOnClickListener{
            getRecipes(0) { recipes ->
                addRecipesLayout(recipes)
            }
        }
    }



    private fun addRecipesLayout(recipes:List<RecipeType>){
        recipes.forEach{ recipe ->
            val sectionLayout:LinearLayout = run{
                val layout = LinearLayout(this)
                layout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layout.orientation = LinearLayout.VERTICAL
                layout.setPadding(pxToDp(20),0,pxToDp(20),0)
                layout
        }
            val videoImage:ImageView = run{
                val view = ImageView(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.setMargins(0,pxToDp(30),0,0)
                view.layoutParams = layoutParams
                view.adjustViewBounds = true
                view.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this)
                    .load(recipe.youtube.thumbnailUrl)
                    .into(view)
                view
            }
            val videoTitle:TextView = run{
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.setMargins(0,pxToDp(15),0,0)
                view.layoutParams = layoutParams
                view.text = recipe.youtube.title
                view.textSize = 16F
                view.setTypeface(null,Typeface.BOLD)
                view.setTextColor(ContextCompat.getColor(this, R.color.black))
                view
            }
            fun makeIngredientTag(text:String):CardView{
                val wrapper = CardView(this)
                val wrapperLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                wrapperLayoutParams.setMargins(0,pxToDp(20),0,0)
                wrapper.layoutParams = wrapperLayoutParams
                wrapper.radius = 999f
                wrapper.cardElevation = 0f
                val textView = TextView(this)
                val textViewLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                textView.layoutParams = textViewLayoutParams
                textView.setPadding(pxToDp(10),pxToDp(5),pxToDp(10),pxToDp(5))
                textView.text = text
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)
                textView.setTypeface(null,Typeface.BOLD)
                textView.setTextColor(ContextCompat.getColor(this, R.color.white))
                textView.setBackgroundColor(ContextCompat.getColor(this, R.color.carrot))

                wrapper.addView(textView)
                return wrapper
            }

            val availableTag = makeIngredientTag("보유")
            val missingTag = makeIngredientTag("미보유")

            fun makeItemsLayout():CustomFlexboxLayout{
                val view = CustomFlexboxLayout(this)
                val layoutParams = LinearLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.MATCH_PARENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                )
                view.layoutParams = layoutParams
                view.flexWrap = FlexWrap.WRAP
                return view
            }

            fun makeItemTextView(text:String):CardView{
                val wrapper = CardView(this)
                val wrapperLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                wrapperLayoutParams.setMargins(0,pxToDp(10),pxToDp(10),0)
                wrapper.layoutParams = wrapperLayoutParams
                wrapper.radius = 999f
                wrapper.cardElevation = 0f

                val textView = TextView(this)
                val textViewLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                textView.layoutParams = textViewLayoutParams
                textView.setPadding(pxToDp(10),pxToDp(5),pxToDp(10),pxToDp(5))
                textView.text = text
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)
                textView.setTypeface(null,Typeface.BOLD)
                textView.setTextColor(ContextCompat.getColor(this, R.color.black))
                textView.setBackgroundColor(Color.parseColor("#f5f5f5"))

                wrapper.addView(textView)
                return wrapper
            }

            val availableItemsLayout = makeItemsLayout()
            val missingItemsLayout = makeItemsLayout()
            recipe.availableIngredients.forEach{ item ->
                availableItemsLayout.addView(makeItemTextView(item))
            }
            recipe.missingIngredients.forEach{item ->
                missingItemsLayout.addView(makeItemTextView(item))
            }

            val splitLine:View = run{
                val view = View(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2
                )
                layoutParams.setMargins(0,pxToDp(20),0,0)
                view.layoutParams = layoutParams
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                view
            }



            listOf(videoImage,videoTitle,availableTag, availableItemsLayout,missingTag,missingItemsLayout,splitLine).forEach { view ->
                sectionLayout.addView(view)
            }


            binding.main.addView(sectionLayout)

        }
    }

    // px -> dp
    fun pxToDp(dp: Int): Int {
        val density = this.resources.displayMetrics.density
        return (dp * density).toInt()
    }


    // Toast 알림 띄우는 함수
    private fun alertToast(s:String){
        Toast.makeText(this.applicationContext, s, Toast.LENGTH_SHORT).show()
    }



    private fun removeLoading(){
        runOnUiThread{
            binding.main.removeView(binding.loading)
        }
    }

    private fun getRecipes(startIndex:Int, renderRecipes: (List<RecipeType>) -> Unit){
        runOnUiThread{
            binding.main.removeView(binding.moreBtnWrapper)
            binding.main.addView(binding.loading)
        }
//        val url = "https://mocum-project-gmck.vercel.app/api/recipe?startIndex=$startIndex"
        val url = "https://mocum-project-gmck.vercel.app/api/dev/recipeData" // 더미데이터
        val headers = Headers.Builder()
            .add("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2MTQ0Mzg5LCJleHAiOjE2ODg3MzYzODl9.SPmt9QV5oNcV9n6QR3JUv1YmlDOowbXTT3jW9FH25OI")
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val statusCode = response.code
                if(statusCode === 200){
                    val responseBody = response.body?.string()
                    val recipes = Gson().fromJson<ResponseType<RecipeResponseType>>(responseBody,object:TypeToken<ResponseType<RecipeResponseType>>() {}.type).result.recipes
                    Log.e("recipes",recipes.toString())
                    runOnUiThread{
                        removeLoading()
                        renderRecipes(recipes)
                        runOnUiThread{
                            binding.main.addView(binding.moreBtnWrapper)
                        }
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리
                e.printStackTrace()
            }
        })
    }
}