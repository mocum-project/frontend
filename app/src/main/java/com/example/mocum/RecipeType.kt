package com.example.mocum

data class RecipeResponseType(
    val recipes:List<RecipeType>
)

data class RecipeType (
    val name: String,
    val category: String,
    val availableIngredients: List<String>,
    val missingIngredients: List<String>,
    val youtube: Youtube
)

data class Youtube(
    val videoUrl: String,
    val title: String,
    val thumbnailUrl: String
)

val recipeJson = """
[
    {
        "name": "삼겹살야채볶음",
        "category": "메인반찬",
        "availableIngredients": [
            "설탕",
            "간장",
            "후추",
            "소금",
            "참기름",
            "굴소스",
            "마늘",
            "삼겹살",
            "양파"
        ],
        "missingIngredients": [
            "청경채",
            "건고추",
            "맛술",
            "미니양송이버섯"
        ],
        "youtube": {
            "videoUrl": "https://youtube.com/video/Oao9t8xmy3g",
            "title": "깔끔한 맛의 삼겹살 야채볶음 만들기",
            "thumbnailUrl": "https://i.ytimg.com/vi/Oao9t8xmy3g/mqdefault.jpg"
        }
    },
    {
        "name": "김치야채비빔국수",
        "category": "면/만두",
        "availableIngredients": [
            "설탕",
            "간장",
            "고추장",
            "고춧가루",
            "참기름",
            "김치",
            "양파"
        ],
        "missingIngredients": [
            "애호박",
            "소면"
        ],
        "youtube": {
            "videoUrl": "https://youtube.com/video/ULO430mqklI",
            "title": "[김치비빔국수] 눈이 번쩍 떠지는 새콤, 달콤, 매콤한 간단 비빔국수! / 국수 만들기, 김치요리 Spicy Noodles",
            "thumbnailUrl": "https://i.ytimg.com/vi/ULO430mqklI/mqdefault.jpg"
        }
    }
]
"""