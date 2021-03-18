package com.example.rec

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AuthActivity : AppCompatActivity() {

    private val frgLogin: LoginFragment = LoginFragment()
    private val frgregister: Fragment = RegisterFragment()
    private val DURACION_SPLASH = 900

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        setContentView(R.layout.act_auth)
        Handler().postDelayed({
            val intent = Intent(this@AuthActivity, LoginFragment::class.java)
            startActivity(intent)
            finish()
        }, DURACION_SPLASH.toLong())
    }
}