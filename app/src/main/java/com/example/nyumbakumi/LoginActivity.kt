package com.example.nyumbakumi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvRegister: TextView

    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail = findViewById(R.id.medtEmail)
        edtPassword = findViewById(R.id.medtPassword)
        btnLogin = findViewById(R.id.mbtnLogin)
        tvRegister = findViewById(R.id.mtvRegister)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        btnLogin.setOnClickListener {
//Start receiving data from user
            var email = edtEmail.text.toString().trim()
            var password = edtPassword.text.toString().trim()

            //Check if user has submitted empty fields
            if (email.isEmpty()){
                edtEmail.setError("Please fill in this input")
                edtEmail.requestFocus()

            }else if (password.isEmpty()){
                edtPassword.setError("Please fill in this input")
                edtPassword.requestFocus()
            }else{
                //Proceed to register the user
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        progress.dismiss()
                        if (it.isSuccessful){
                            Toast.makeText(this, "Login Successful",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,NewHomeActivity::class.java))
                            finish()
                        }else{
                            displayMessage("Error", it.exception!!.message.toString())
                        }
                    }
            }
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(applicationContext,Registration::class.java))
        }


    }

    fun displayMessage(title:String, message:String){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("ok", null)
        alertDialog.create().show()
    }
}
