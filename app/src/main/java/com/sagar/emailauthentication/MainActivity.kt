package com.sagar.emailauthentication

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    var btnLogOut: Button? = null
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        btnLogOut = findViewById(R.id.btnLogout)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btnLogout).setOnClickListener { view ->
            mAuth!!.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth!!.currentUser
        if (user == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }
}