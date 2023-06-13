package com.example.myapplication

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View

import android.widget.Button
import android.widget.LinearLayout

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

import com.example.myapplication.databinding.ActivityMainBinding
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
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var categories:List<String> = emptyList()
    var ingredients:List<IngredientsType> = emptyList()
    var currentTab = StorageArea.freezer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

//        supportActionBar?.setDisplayShowTitleEnabled(false) //툴바 제목 없애기
        supportActionBar?.setTitle("냉장고를 부탁해")

        renderTabLayout()
        getDataAndRenderLayout()

        binding.recipeBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.carrot))
    }



    private fun getDataAndRenderLayout(){
        val url = "https://mocum-project-gmck.vercel.app/api/ingredients"
        val headers = Headers.Builder()
            .add("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2NTcxNDI1LCJleHAiOjE2ODkxNjM0MjV9.79nry30Eppc4SbeALuRKwrCyCX4KgnodJ7rFl7uhXAY")
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val statusCode = response.code
                Log.e("statusCode",statusCode.toString())
                if(statusCode === 200){
                    val responseBody = response.body?.string()
                    val responseData = Gson().fromJson<ResponseType<StoredIngredientsType>>(responseBody,object:
                        TypeToken<ResponseType<StoredIngredientsType>>() {}.type).result
                    categories = responseData.categories
                    ingredients = responseData.ingredients
                    runOnUiThread{
                        renderMainLayout()
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리
                e.printStackTrace()
                Log.e("error","에러남")
            }
        })
    }



    private fun renderTabLayout(){
        val layout:LinearLayout = run{
            val l = LinearLayout(this)
            var layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL
            l.layoutParams = layoutParams
            l.orientation = LinearLayout.HORIZONTAL

            l
        }
        listOf(StorageArea.freezer,StorageArea.fridge,StorageArea.roomTemp).forEach{ storageArea ->
            val tabItem = Button(this)
            tabItem.text = storageArea.displayName
            tabItem.layoutParams = LinearLayout.LayoutParams(
                0,
                pxToDp(50),
                1f
            )

            if(storageArea == currentTab){
                tabItem.setBackgroundColor(Color.WHITE)
                tabItem.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            } else{
                tabItem.setBackgroundColor(Color.parseColor("#f5f5f5"))
                tabItem.setTextColor(Color.BLACK)
            }
            tabItem.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
            tabItem.setTypeface(null, Typeface.BOLD)
            tabItem.setText(storageArea.displayName)
            tabItem.setOnClickListener{ item ->
                currentTab = storageArea
                binding.tapLayout.removeAllViews()
                renderTabLayout()
                binding.scrollView.removeAllViews()
                renderMainLayout()
            }
            layout.addView(tabItem)
        }

        binding.tapLayout.addView(layout)
    }



    private fun renderMainLayout(){
        val layout:LinearLayout = run{
            val l = LinearLayout(this)
            var layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            l.layoutParams = layoutParams
            l.setPadding(pxToDp(20),0,pxToDp(20),0)
            l.orientation = LinearLayout.VERTICAL
            l
        }

        var hasIngredient = false
        categories.forEach{category ->

            val currIngredients = ingredients.filter { i ->
                i.category == category && i.storageArea == currentTab
            }
            if(currIngredients.isEmpty()){
                return@forEach
            }
            hasIngredient = true
            val section:LinearLayout = run{
                val l = LinearLayout(this)
                var layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.setMargins(0,0,0,pxToDp(80))
                l.layoutParams = layoutParams
                l.orientation = LinearLayout.VERTICAL
                l
            }
            val categoryTitle:TextView = run{
                var textView = TextView(this)
                textView.setText("${category}(${currIngredients.size})")
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
                textView.setTypeface(null, Typeface.BOLD)
                textView.setTextColor(Color.BLACK)
                textView
            }
            section.addView(categoryTitle)

            val splitLine:View = run{
                val view = View(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2
                )
                layoutParams.setMargins(0,pxToDp(5),0,pxToDp(10))
                view.layoutParams = layoutParams
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                view
            }

            section.addView(splitLine)


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

            val itemsLayout = makeItemsLayout()

            fun makeItemSection():LinearLayout{
                val r = LinearLayout(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.setMargins(0,pxToDp(10),pxToDp(10),0)
                r.layoutParams = layoutParams
                r.orientation = LinearLayout.VERTICAL

                return r
            }

            fun makeItemDate(date:String):CardView{
                val wrapper = CardView(this)
                val wrapperLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
//                wrapperLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START,RelativeLayout.TRUE)
//                wrapperLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE)
//                wrapperLayoutParams.setMargins(pxToDp(-10),pxToDp(-10),0,0)
                wrapperLayoutParams.gravity = Gravity.START or Gravity.TOP
                wrapper.layoutParams = wrapperLayoutParams
                wrapper.radius = 999f
                wrapper.cardElevation = 0f

                val textView = TextView(this)
                val textViewLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                textView.layoutParams = textViewLayoutParams
//                textView.minWidth = pxToDp(30)
                textView.setPadding(pxToDp(5),pxToDp(2),pxToDp(5),pxToDp(2))
                textView.text = date
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,6f)
                textView.setTypeface(null,Typeface.NORMAL)
                textView.setTextColor(ContextCompat.getColor(this, R.color.white))
                textView.setBackgroundColor(ContextCompat.getColor(this, R.color.carrot))

                wrapper.addView(textView)
                return wrapper
            }

            fun makeItemButton(text:String): CardView {

                val wrapper = CardView(this)
                val wrapperLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                wrapperLayoutParams.setMargins(pxToDp(5),0,0,0)
                wrapperLayoutParams.gravity = Gravity.BOTTOM or Gravity.END
                wrapper.layoutParams = wrapperLayoutParams
                wrapper.radius = 999f
                wrapper.cardElevation = 0f
                wrapper.isClickable = true
                wrapper.setOnClickListener{
                    // 페이지 이동
                }

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



            currIngredients.sortedBy { i -> -getDayDifference(i.expirationDate) } .forEach{
//                Log.e("date",getDayDifference(it.expirationDate).toString())
                val dayDifference = getDayDifference(it.expirationDate)
                val s = makeItemSection()
                var a = ""
                if(dayDifference > 0){
                    a = "+"
                }else{
                    a = "-"
                }
                s.addView(makeItemDate("D${a}${ abs(dayDifference) }"))
                s.addView(makeItemButton(it.name))
                itemsLayout.addView(s)
            }

            section.addView(itemsLayout)


            layout.addView(section)
        }
        if(hasIngredient == false){

            val l = LinearLayout(this)
            l.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            l.gravity = Gravity.CENTER
            val t = TextView(this)
            t.setText("보관 중인 식재료가 없습니다.")
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
            t.setTypeface(null, Typeface.BOLD)
            t.setTextColor(Color.BLACK)
            l.addView(t)
            layout.addView(l)
        }
        binding.scrollView.addView(layout)
    }



    fun pxToDp(dp: Int): Int {
        val density = this.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    fun getDayDifference(dateString: String): Int {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val givenDate = format.parse(dateString)
        val currentDate = Calendar.getInstance().time

        val dayDifference = ((currentDate.time - givenDate.time) / (24 * 60 * 60 * 1000)).toInt()

        return dayDifference
    }
}

data class StoredIngredientsType(
    val categories:List<String>,
    val ingredients:List<IngredientsType>
)

data class IngredientsType(
    val id:Int,
    val name:String,
    val category:String,
    val storageArea:StorageArea,
    val count:Int,
    val expirationDate:String
)

enum class StorageArea(val displayName:String){
    freezer("냉동실"),
    fridge("냉장고"),
    roomTemp("실온")
}

