package com.example.nyumbakumi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var edtEmail:EditText
    lateinit var edtPassword:EditText
    lateinit var btnregister:Button
    lateinit var tvlogin:TextView
    lateinit var progress:ProgressDialog
    lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        btnregister = findViewById(R.id.mBtnReg)
        tvlogin = findViewById(R.id.mTvLogin)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog( this)
        progress.setTitle("LOading")
        progress.setMessage("PLease Wait")
        btnregister.setOnClickListener {
//Start receiving data from user
            var email = edtEmail.toString().trim()
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
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        progress.dismiss()
                        if (it.isSuccessful){
                            Toast.makeText(this, "Registration Successful",
                                Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            startActivity(Intent(this,LoginActivity::class.java))
                            finish()
                        }else{
                            displayMessage("Error", it.exception!!.message.toString())
                        }
                    }
            }
        }
        tvlogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

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