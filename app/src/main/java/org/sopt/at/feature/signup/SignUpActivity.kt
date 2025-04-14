package org.sopt.at.feature.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.sopt.at.MainActivity.Companion.ID
import org.sopt.at.MainActivity.Companion.PASSWORD
import org.sopt.at.R.string.sign_up_id_error
import org.sopt.at.R.string.sign_up_password_error
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                var id by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var page by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                ) { innerPadding ->
                    SignUpScreen(
                        id = id,
                        onIdChange = { id = it },
                        password = password,
                        onPasswordChange = { password = it },
                        page = page,
                        navigateUp = { if (page == 0) finish() else page-- },
                        onSignUpClick = {
                            when (page) {
                                0 -> if (id.isNotEmpty() && id.isValidEmail()) page++
                                    else Toast.makeText(this, getString(sign_up_id_error), Toast.LENGTH_SHORT).show()

                                else -> if (password.isNotEmpty() && password.isValidPassword()) {
                                    val resultIntent = Intent().apply {
                                        putExtra(ID, id)
                                        putExtra(PASSWORD, password)
                                    }
                                    setResult(RESULT_OK, resultIntent)
                                    finish()
                                } else Toast.makeText(this, getString(sign_up_password_error), Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier
                            .padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun String.isValidEmail(): Boolean = EMAIL_REGEX.matches(this)

    private fun String.isValidPassword(): Boolean = PASSWORD_REGEX.matches(this)

    companion object { // TODO: 1주차 과제용, 삭제 예정
        val EMAIL_REGEX = "^[a-z0-9]{6,12}$".toRegex()
        val PASSWORD_REGEX =
            "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*])[a-zA-Z0-9~!@#$%^&*]{8,15}$".toRegex()
    }
}
