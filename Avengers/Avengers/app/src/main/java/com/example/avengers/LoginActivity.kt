package com.example.avengers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    lateinit var etMobailNumber: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgotPassword: TextView
    lateinit var txtRegister: TextView
    val validMobailNumber = "0123456789"
    lateinit var sharedpreferences : SharedPreferences
    val validPassword = arrayOf("tony", "steve", "bruce", "thanos")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedpreferences = getSharedPreferences(getString(R.string.preference_file_name),Context.MODE_PRIVATE)
        val isLoggedIn=sharedpreferences.getBoolean("isLoggedIn",false)
        if(isLoggedIn){
            val intent=Intent(this@LoginActivity,AvengerActivity::class.java)
            startActivity(intent)
            finish()
        }


        title = "Log In"
        etMobailNumber = findViewById(R.id.login_number)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.bottonLogin)
        txtForgotPassword = findViewById(R.id.ForgetPassword)
        txtRegister = findViewById(R.id.RegiterYourself)
        btnLogin.setOnClickListener {
            val mobileNumber = etMobailNumber.text.toString()
            val password = etPassword.text.toString()
            var nameOfAvenger = "Avenger"
            val intent = Intent(this@LoginActivity, AvengerActivity::class.java)
            if (mobileNumber == validMobailNumber) {
                when (password) {
                    validPassword[0] -> {

                        nameOfAvenger = "Iron Man"

                        savePrefrences(nameOfAvenger)

                    }
                    validPassword[1] -> {

                        nameOfAvenger = "Captain America"

                        savePrefrences(nameOfAvenger)

                    }
                    validPassword[2] -> {

                        nameOfAvenger = "The Hulk"

                        savePrefrences(nameOfAvenger)

                    }
                    validPassword[3] -> {

                        nameOfAvenger = "The Avengers"
                        savePrefrences(nameOfAvenger)



                    }
                    else -> Toast.makeText(this@LoginActivity, "Incorrect Password", Toast.LENGTH_LONG).show()
                }


            } else {
                Toast.makeText(this@LoginActivity, "Incorrect Details", Toast.LENGTH_LONG)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
    fun savePrefrences(title:String){
        sharedpreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedpreferences.edit().putString("Title",title).apply()
    }
}