package org.sopt.at.feature.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.at.MainActivity.Companion.ID
import org.sopt.at.MainActivity.Companion.IS_LOGGED_IN
import org.sopt.at.MainActivity.Companion.PASSWORD
import org.sopt.at.MainActivity.Companion.USER_PREFS
import org.sopt.at.R.string.sign_in_error
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.feature.mypage.MyPageActivity
import org.sopt.at.feature.signup.SignUpActivity

class SignInActivity : ComponentActivity() {
    private var userId by mutableStateOf("")
    private var userPassword by mutableStateOf("")
    private val signUpLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                userId = it.data?.getStringExtra(ID).orEmpty()
                userPassword = it.data?.getStringExtra(PASSWORD).orEmpty()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                var id by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { innerPadding ->
                    SignInScreen(
                        id = id,
                        onIdChange = { id = it },
                        passwordText = password,
                        onPasswordChange = { password = it },
                        onSignInClick = { signIn(id, password, snackbarHostState, coroutineScope) },
                        navigateToSignUp = { navigateToSignUp() },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun navigateToSignUp() =
        Intent(this, SignUpActivity::class.java).apply { signUpLauncher.launch(this) }

    private fun signIn(
        id: String,
        password: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope,
    ) {
        if (id == userId && password == userPassword) {
            val sharedPreferences = getSharedPreferences(USER_PREFS, MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString(ID, id)
                putString(PASSWORD, password)
                putBoolean(IS_LOGGED_IN, true)
                apply()
            }
            val intent = Intent(this, MyPageActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        } else coroutineScope.launch { snackbarHostState.showSnackbar(getString(sign_in_error)) }
    }
}
