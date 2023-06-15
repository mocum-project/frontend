package com.example.materialregistration

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.time.LocalDate
import java.time.LocalDateTime
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

        val ingredientTextView = findViewById<TextView>(R.id.category_text_view)
        val categoryTextView = findViewById<TextView>(R.id.subcategory_text_view)

// Retrieve the extras from the intent
        val ingredient = intent.getStringExtra("ingredient")
        val category = intent.getStringExtra("category")
        val index = intent.getIntExtra("index", -1)

// Check if index is valid
        if (index >= 0) {
            // Create a map to store the categories and their corresponding lists of subcategories
            val categoryToSubcategoryMap = mapOf(
                "채소" to listOf(
                    "가지", "감자", "고구마", "고추", "깻잎", "느타리버섯", "단호박", "당근", "대파", "무",
                    "배추", "버섯", "부추", "브로콜리", "상추", "새송이버섯", "새싹채소", "샐러리", "시금치",
                    "애호박", "양배추", "양상추", "양파", "연근", "오이", "옥수수", "우엉", "청경채",
                    "청양고추", "케일", "콩나물", "팽이버섯", "표고버섯", "피망", "호박"
                ),
                "과일" to listOf(
                    "감", "귤", "대추", "딸기", "라임", "레몬", "망고", "무화과", "바나나", "방울토마토",
                    "베", "복숭아", "블루베리", "사과", "수박", "아보카도", "오렌지", "참외", "체리", "키위",
                    "토마토", "파인애플", "포도"
                ),
                "고기" to listOf(
                    "갈비", "달걀", "닭가슴살", "닭고기", "닭날개", "닭다리", "돼지고기", "삼겹살", "메추리알",
                    "베이컨", "소고기", "소시지", "앞다리살", "양고기", "오리고기", "햄"
                ),
                "수산물" to listOf(
                    "가리비", "갈치", "갑오징어", "고둥", "고등어", "굴", "꼬막", "꽃게", "대게",
                    "대구", "대하", "랍스터", "말린생선", "멸치", "문어", "바지락", "새우", "생선",
                    "성게","아귀","연어","오징어","장어","전복","조개","참치","해삼","해조류","홍합"
                ),
                "음료" to listOf(
                    "과일 주스", "레모네이드", "생수", "에너지음료", "이온음료", "차", "캔 음료", "커피", "탄산수",
                    "탄산음료", "토닉", "티백", "핫초코"
                ),
                "주류" to listOf(
                    "럼", "막걸리", "맥주", "무알콜맥주", "보드카", "브랜디", "사케", "상그리아", "샴페인",
                    "소주", "와인", "위스키", "진", "칵테일", "테킬라"
                ),
                "조미료" to listOf(
                    "간장", "고추냉이", "고추장", "굴소스", "꿀", "땅콩버터", "마요네즈", "머스터드", "메이플시럽",
                    "물엿", "바베큐소스", "발사믹", "사과 소스", "사과 식초", "살사소스", "샐러드소스", "설탕", "소금",
                    "소스팩", "식용유", "올리브유", "잼", "케첩", "페스토", "핫소스"
                ),
                "향신료" to listOf(
                    "고수", "고춧가루", "다진마늘", "딜", "로즈마리", "마늘", "민트", "바질", "생강",
                    "시나몬", "오레가노", "월계수잎", "카레가루", "케이퍼", "쿠민", "타임", "트러플", "파슬리",
                    "펜넬", "후추"
                ),
                "요리" to listOf(
                    "게맛살", "국", "국수", "김", "김밥", "김치", "낫또", "뇨키", "도시락",
                    "돈까스", "두부", "떡볶이", "똠양꿍", "라사냐", "만두", "볶음밥", "부리또", "불고기",
                    "새우튀김", "샌드위치"
                ),
                "유제품" to listOf(
                    "고다치즈", "두유", "라씨", "리코타", "모짜렐라", "버터", "버터밀크", "사워크림", "요거트",
                    "우유", "유산균", "체다치즈", "크림치즈", "휘핑크림"
                ),
                "빵류" to listOf(
                    "도넛", "도라야키", "롤빵", "마들렌", "머핀", "바게트", "번", "베이글", "브리오슈",
                    "스콘", "시나몬롤", "식빵", "와플", "치아바타", "크레이프", "크루아상","파이", "팬케이크","프레첼",
                    "플랫브레드"
                ),
                "디저트" to listOf(
                    "감자칩", "감자튀김", "나초", "떡", "마시멜로", "마카롱", "벌집", "브라우니", "비스킷",
                    "사탕", "아이스크림", "약과", "에너지바", "에클레르", "월병", "젤리빈", "초콜릿", "츄러스",
                    "캐러멜", "케이크", "쿠키", "크래커", "티라미수", "팝콘", "푸딩"
                ),
                "견과류" to listOf(
                    "깨", "도토리", "땅콩", "마카다미아", "밤", "브라질너트", "아몬드", "잣", "카카오",
                    "캐슈넛", "피칸", "해바라기씨", "헤이즐넛", "호두", "호박씨"
                ),
                "곡물" to listOf(
                    "강낭콩", "검정콩", "녹두", "렌틸콩", "메밀", "밀가루", "병아리콩", "보리", "쌀",
                    "오트밀", "옥수수전분", "완두콩", "팥", "호밀", "효모"
                ),
                "기타" to listOf(
                    "사료", "비타민", "알약", "이유식", "화장품"
                ),
            )

            val categoryList = categoryToSubcategoryMap[category]

            // Check if the category list is not null and the index is within the range
            if (categoryList != null && index < categoryList.size) {
                // Get the subcategory for the corresponding index
                val subcategory = categoryList[index]

                // Set the ingredientTextView text to the subcategory value
                ingredientTextView.text = subcategory
            } else {
                // Handle invalid index or category
                ingredientTextView.text = "Invalid index or category"
            }
        } else {
            // Handle invalid index
            ingredientTextView.text = "Invalid index"
        }

// Set the categoryTextView text to the category value
        categoryTextView.text = category

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
        put(R.id.menu_refrigerator, "냉장실")
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
