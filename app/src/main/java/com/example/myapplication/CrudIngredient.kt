package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.databinding.ActivityCrudIngredientBinding

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CrudIngredient : AppCompatActivity() {

    lateinit var binding: ActivityCrudIngredientBinding
    private lateinit var storageLocationTextView: TextView
    private lateinit var minusTextView: TextView
    private lateinit var plusTextView: TextView
    private lateinit var quantityValueTextView: TextView
    private lateinit var expiryDateTextView: TextView
    private var mode = "new"

    private var quantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCrudIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        storageLocationTextView = findViewById(R.id.storage_location_popup_menu)
        storageLocationTextView.setOnClickListener {
            showPopupMenu()
        }

        minusTextView = findViewById(R.id.minus_button)
        plusTextView = findViewById(R.id.plus_button)
        quantityValueTextView = findViewById(R.id.quantity_value_text_view)


        minusTextView.setOnClickListener {
            if (quantity > 0) {
                quantity--
                quantityValueTextView.text = quantity.toString()
            }
        }

        plusTextView.setOnClickListener {
            quantity++
            quantityValueTextView.text = quantity.toString()
        }

        val ingredientTextView = findViewById<TextView>(R.id.name)
        val categoryTextView = findViewById<TextView>(R.id.subcategory_text_view)

// Retrieve the extras from the intent
        val category = intent.getStringExtra("category")
        val name = intent.getStringExtra("name")
        val mode = intent.getStringExtra("mode")

// Check if index is valid
        if (mode == "new") {
            binding.name.text = name
            categoryTextView.text = category
            binding.addOrEditBtn.text = "추가"

        } else {
            val count = intent.getStringExtra("count")
            val storageArea = intent.getStringExtra("storageArea")
            val expirationDate = intent.getStringExtra("expirationDate")
            binding.name.text = name
            categoryTextView.text = category
//            runOnUiThread{
//                makeToast(listOf(count,storageArea,expirationDate).toString())
//            }
            binding.storageLocationPopupMenu.text = getStorageArea2(storageArea as String)
            binding.quantityValueTextView.text = count as String
            binding.expiryDateTextView.text = formatDateReverse(expirationDate as String)
            binding.addOrEditBtn.text = "수정"

        }

// Set the categoryTextView text to the category value


        expiryDateTextView = findViewById(R.id.expiry_date_text_view)



        expiryDateTextView.setOnClickListener {
            showDatePickerDialog(expiryDateTextView)
        }

        binding.addOrEditBtn.setOnClickListener{


            if(mode == "new") {
//                Toast.makeText(this, "ㅇㅇㅇㅇㅇ", Toast.LENGTH_SHORT).show()
                fetchNewIngredient()
            }else{
                fetchPutIngredient()
            }
        }

        binding.deleteBtn.setOnClickListener{
            fetchDeleteIngredient()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mode = data?.getStringExtra("mode") as String
    }

    private fun fetchNewIngredient(){
        val name = binding.name.text.toString()
        val category = binding.subcategoryTextView.text.toString()
        val storageArea = binding.storageLocationPopupMenu.text.toString()
        val count = binding.quantityValueTextView.text.toString()
        val expirationDate = formatDate(binding.expiryDateTextView.text.toString())


        val url = "https://mocum-project-gmck.vercel.app/api/ingredients"

        // RequestBody 생성
        val requestBody = FormBody.Builder()
            .add("name", name)
            .add("category",category)
            .add("storageArea",getStorageArea(storageArea))
            .add("count",count)
            .add("expirationDate",expirationDate)
            .build()

        val headers = Headers.Builder()
            .add("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2ODE5MjY0LCJleHAiOjE2ODk0MTEyNjR9.wWuDtV4iXBhhx0N8DOW6Rp1SF6P50dQ6T9GxPLN0KuU")
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .headers(headers)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val statusCode = response.code
                if(statusCode === 200){
                    runOnUiThread{
                        makeToast("식재료 생성 성공")
//                        val intent = Intent(this@CrudIngredient,CategoriesActivity::class.java)
//                        startActivity(intent)
                        onBackPressed()
                    }
                }else{
                    runOnUiThread{
                        makeToast(statusCode.toString())
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리
                e.printStackTrace()
                runOnUiThread{
                    makeToast("오류")
                }
            }
        })
    }

    private fun fetchPutIngredient(){
        val id = intent.getStringExtra("id") as String
        val name = binding.name.text.toString()
        val category = binding.subcategoryTextView.text.toString()
        val storageArea = binding.storageLocationPopupMenu.text.toString()
        val count = binding.quantityValueTextView.text.toString()
        val expirationDate = formatDate(binding.expiryDateTextView.text.toString())

//        makeToast(listOf(name,category,storageArea,count,expirationDate).toString())

        val url = "https://mocum-project-gmck.vercel.app/api/ingredients"

        // RequestBody 생성
        val requestBody = FormBody.Builder()
            .add("id",id)
            .add("name", name)
            .add("category",category)
            .add("storageArea",getStorageArea(storageArea))
            .add("count",count)
            .add("expirationDate",expirationDate)
            .build()

        val headers = Headers.Builder()
            .add("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2ODE5MjY0LCJleHAiOjE2ODk0MTEyNjR9.wWuDtV4iXBhhx0N8DOW6Rp1SF6P50dQ6T9GxPLN0KuU")
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .headers(headers)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val statusCode = response.code
                if(statusCode === 200){
                    runOnUiThread{
                        makeToast("식재료 수정 성공")
//                        val intent = Intent(this@CrudIngredient,CategoriesActivity::class.java)
//                        startActivity(intent)
                        onBackPressed()
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리
                e.printStackTrace()
                runOnUiThread{
                    makeToast("오류")
                }
            }
        })
    }
    private fun fetchDeleteIngredient(){
        val id = intent.getStringExtra("id") as String

        val url = "https://mocum-project-gmck.vercel.app/api/ingredients"



        val headers = Headers.Builder()
            .add("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIzIiwibmlja25hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg2ODE5MjY0LCJleHAiOjE2ODk0MTEyNjR9.wWuDtV4iXBhhx0N8DOW6Rp1SF6P50dQ6T9GxPLN0KuU")
            .add("id",id)
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .delete()
            .headers(headers)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val statusCode = response.code
                if(statusCode === 200){
                    runOnUiThread{
                        makeToast("식재료 삭제 성공")
//                        val intent = Intent(this@CrudIngredient,CategoriesActivity::class.java)
//                        startActivity(intent)
                        onBackPressed()
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                // 요청 실패 처리
                e.printStackTrace()
                runOnUiThread{
                    makeToast("오류")
                }
            }
        })
    }

    fun getStorageArea2(storageArea: String):String{
        if(storageArea == "freezer"){
            return "냉동실"
        } else if(storageArea == "fridge"){
            return "냉장고"
        }else{
            return "실온"
        }
    }

    fun getStorageArea(storageArea:String):String{
        if(storageArea == "냉동실"){
            return "freezer"
        } else if(storageArea == "냉장고"){
            return "fridge"
        }else{
            return "roomTemp"
        }
    }

    private fun makeToast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        val date = inputFormat.parse(dateString)
        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return outputFormat.format(calendar.time)
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = formatDate(selectedYear, selectedMonth, selectedDay)
                textView.text = selectedDate
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val formattedMonth = String.format("%02d", month + 1)
        val formattedDay = String.format("%02d", day)
        return "$year/$formattedMonth/$formattedDay"
    }

    private fun formatDateReverse(inputDate: String): String {
        val dateTimeParts = inputDate.split("T", "Z")
        val datePart = dateTimeParts[0]
        val dateParts = datePart.split("-")
        val year = dateParts[0]
        val month = dateParts[1]
        val day = dateParts[2]

        return "$year/$month/$day"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, storageLocationTextView)
        popupMenu.menuInflater.inflate(R.menu.storage_location_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            handleStorageLocationSelection(item)
            true
        }

        popupMenu.show()
    }

    private val storageAreaHashMap: HashMap<Int, String> = HashMap<Int, String>().apply {
        put(R.id.menu_freezer, "냉동실")
        put(R.id.menu_refrigerator, "냉장고")
        put(R.id.menu_room_temperature, "실온")
    }

    private fun handleStorageLocationSelection(item: MenuItem) {
        val selectedStorageLocation = storageAreaHashMap[item.itemId] ?: ""
        storageLocationTextView.text = selectedStorageLocation
    }
    enum class StorageArea(val displayName: String) {
        freezer("냉동실"),
        fridge("냉장실"),
        roomTemp("실온")
    }
    data class Ingredients(
        val name: String,
        val category: String,
        val storageArea: StorageArea,
        val count: Int,
        val expirationDate: LocalDateTime
    )
}

