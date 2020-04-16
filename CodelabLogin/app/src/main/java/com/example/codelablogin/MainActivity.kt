package com.example.codelablogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val callbackManager = CallbackManager.Factory.create()
    private val email = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        facebook.setPermissions(arrayListOf("email"))
        facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(this@MainActivity, "Success login ", Toast.LENGTH_LONG).show()
            }

            override fun onCancel() {
                Toast.makeText(this@MainActivity, "Cancel login ", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@MainActivity, "Error login ", Toast.LENGTH_LONG).show()
            }

        });

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLogin = accessToken!= null && !accessToken.isExpired

        LoginManager.getInstance().logInWithReadPermissions(this, arrayListOf("public_profile"))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
