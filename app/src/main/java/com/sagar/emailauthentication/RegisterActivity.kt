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


class RegisterActivity : AppCompatActivity() {
    var etRegEmail: TextInputEditText? = null
    var etRegPassword: TextInputEditText? = null
    var tvLoginHere: TextView? = null
    var btnRegister: Button? = null
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register)
        etRegEmail = findViewById(R.id.etRegEmail)
        etRegPassword = findViewById(R.id.etRegPass)
        tvLoginHere = findViewById(R.id.tvLoginHere)
        btnRegister = findViewById(R.id.btnRegister)
        mAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btnRegister).setOnClickListener { createUser() }
        findViewById<TextView>(R.id.tvLoginHere).setOnClickListener {
            startActivity(
                Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                )
            )
        }
    }

    private fun createUser() {
        val email = etRegEmail!!.text.toString()
        val password = etRegPassword!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            etRegEmail!!.error = "Email cannot be empty"
            etRegEmail!!.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword!!.error = "Password cannot be empty"
            etRegPassword!!.requestFocus()
        } else {
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "User registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration Error: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}