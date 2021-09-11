package com.sagar.emailauthentication

 import android.content.Intent
 import android.os.Bundle
 import android.text.TextUtils
 import android.view.Window
 import android.view.WindowManager
 import android.widget.Button
 import android.widget.TextView
 import android.widget.Toast
 import androidx.appcompat.app.AppCompatActivity
 import com.google.android.material.textfield.TextInputEditText
 import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    var etLoginEmail: TextInputEditText? = null
    var etLoginPassword: TextInputEditText? = null
    var tvRegisterHere: TextView? = null
    var btnLogin: Button? = null
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login)
        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginPassword = findViewById(R.id.etLoginPass)
        tvRegisterHere = findViewById(R.id.tvRegisterHere)
        btnLogin = findViewById(R.id.btnLogin)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btnLogin).setOnClickListener { loginUser() }
        findViewById<TextView>(R.id.tvRegisterHere).setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        }
    }

    private fun loginUser() {
        val email = etLoginEmail!!.text.toString()
        val password = etLoginPassword!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            etLoginEmail!!.error = "Email cannot be empty"
            etLoginEmail!!.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword!!.error = "Password cannot be empty"
            etLoginPassword!!.requestFocus()
        } else {
            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        "User logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Log in Error: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}