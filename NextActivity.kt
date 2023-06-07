package com.example.materialregistration

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class NextActivity : AppCompatActivity() {

    private lateinit var storageLocationTextView: TextView
    private lateinit var minusTextView: TextView
    private lateinit var plusTextView: TextView
    private lateinit var quantityValueTextView: TextView
    private lateinit var registrationDateTextView: TextView
    private lateinit var expiryDateTextView: TextView

    private var quantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

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

        val selectedIngredient = intent.getStringExtra("ingredient")

        val subcategoryTextView = findViewById<TextView>(R.id.subcategory_text_view)
        subcategoryTextView.text = selectedIngredient

        registrationDateTextView = findViewById(R.id.registration_date_text_view)
        expiryDateTextView = findViewById(R.id.expiry_date_text_view)

        registrationDateTextView.setOnClickListener {
            showDatePickerDialog(registrationDateTextView)
        }

        expiryDateTextView.setOnClickListener {
            showDatePickerDialog(expiryDateTextView)
        }
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
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

    private fun handleStorageLocationSelection(item: MenuItem) {
        val selectedStorageLocation = when (item.itemId) {
            R.id.menu_freezer -> "냉동실"
            R.id.menu_refrigerator -> "냉장실"
            R.id.menu_room_temperature -> "실온"
            else -> ""
        }
        storageLocationTextView.text = selectedStorageLocation
    }
}