package org.sopt.at

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.sopt.at.feature.mypage.MyPageActivity
import org.sopt.at.feature.signin.SignInActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(USER_PREFS, MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

        val intent = if (isLoggedIn) Intent(this, MyPageActivity::class.java)
            else Intent(this, SignInActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    companion object { // TODO: 1주차 과제용, 이후 삭제 예정
        const val USER_PREFS = "user_preferences"
        const val IS_LOGGED_IN = "is_logged_in"
        const val ID = "user_id"
        const val PASSWORD = "user_password"
    }
}
