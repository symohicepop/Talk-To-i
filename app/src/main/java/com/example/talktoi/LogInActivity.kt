package com.example.talktoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.security.KeyStore

class LogInActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        btnsignup = findViewById(R.id.btn_signup)


        btnsignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String, Password: String){
        //logic for loging in user
        mAuth.signInWithEmailAndPassword(email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //code for logging in user
                    val intent = Intent(this@LogInActivity, HomeActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    Toast.makeText(this@LogInActivity, "Please check your email or Password and try again",Toast.LENGTH_SHORT).show()
                }
            }


}





    }
