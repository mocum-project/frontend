package com.example.myapplication


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.user.UserApiClient
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = this@LoginActivity.getSharedPreferences("shared", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("jwt", null)

        if (token != null) {
            // 저장된 토큰이 있을 경우, 자동으로 로그인 처리
            alertToast("성공")
            performAutoLogin(token)
        } else {
            // 저장된 토큰이 없을 경우, 로그인 화면을 보여줌
            showLoginScreen()
        }

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
        /*        // 해쉬키 찾기
                val keyHash = Utility.getKeyHash(this)
                Log.d("Hash", keyHash)*/

        val kakao_login_button = findViewById<ImageButton>(R.id.kakao_login_button) // 로그인 버튼

        kakao_login_button.setOnClickListener {
            if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        // 로그인 실패 처리
                    } else if (token != null) {
/*
                        Toast.makeText(this, token.accessToken, Toast.LENGTH_SHORT).show()
*/
                        Log.i("AccessToken", token.accessToken)
                        sendTokenToServer(token.accessToken) // 토큰을 서버로 전송
                    }
                }
            } else {
                Toast.makeText(this, "카카오톡 실행 불가", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performAutoLogin(token: String) {
        if (isValidToken(token)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            showLoginScreen()
        }
    }
    private fun isValidToken(token: String): Boolean {
        // 토큰 유효성 검사 로직 구현
        // 유효한 토큰인 경우 true 반환, 그렇지 않은 경우 false 반환
        // 서버로 토큰 유효성 검사를 요청하는 API 호출

        // OkHttpClient를 사용하여 HTTP 요청을 보낼 수 있습니다.
        val client = OkHttpClient()

        // 토큰 유효성 검사를 위한 API 엔드포인트 URL
        val url = "https://mocum-project-gmck.vercel.app/api/login"

        // 요청을 생성합니다.
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()

        // 요청을 실행하고 응답을 받습니다.
        val response = client.newCall(request).execute()

        // 응답을 처리합니다.
        return response.isSuccessful
    }

    private fun showLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun alertToast(s:String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    // 토큰 정보를 담고 있는 Token 클래스 예시

    data class KakaoLogin(
        val accessToken : String)

    private fun sendTokenToServer(accessToken: String) {
        val url = "https://mocum-project-gmck.vercel.app/api/login"
        val client = OkHttpClient()

        val body = FormBody.Builder()
            .add("token", accessToken)
            .build()

        val KakaoLogin = KakaoLogin(accessToken) // KakaoLogin 인스턴스 생성

        val request = Request.Builder()
            .url(url)
            .header("access_token", KakaoLogin.accessToken) // 헤더에 토큰 추가
            .post(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // 요청 실패 처리
                // 예외 발생 또는 네트워크 연결 문제 등
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                // 요청 성공 처리
                // 응답 상태코드 확인
                val statusCode = response.code
                runOnUiThread {
                    alertToast(statusCode.toString())
                }
                if (statusCode == 200) {
                    // 토큰 전송 성공
                    val responseBody = response.body?.string()
                    val jwt = Gson().fromJson<ResponseType<JwtResponseType>>(responseBody, object:TypeToken<ResponseType<JwtResponseType>>() {}.type).result.jwt

                    val sharedPreferences = this@LoginActivity.getSharedPreferences("shared",Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("jwt",jwt).apply()
                    runOnUiThread {
                        alertToast(sharedPreferences.getString("jwt","안불러와짐").toString())
                    }

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()

                } else {
                    // 토큰 전송 실패
                    runOnUiThread {
                        alertToast("실패")
                    }
                }
            }
        })
    }
}

data class JwtResponseType(
    val jwt:String
)
