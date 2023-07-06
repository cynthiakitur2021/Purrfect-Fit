package com.example.nyumbakumi

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class Registration : AppCompatActivity() {
    lateinit var edtName: EditText
    lateinit var edtPass: EditText
    lateinit var edtEmail: EditText
    lateinit var edtAge: EditText
    lateinit var ImageView: ImageView
    lateinit var btnupload: Button
    lateinit var progress: ProgressDialog
    val PICK_IMAGE_REQUEST = 100
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignments)
        edtName = findViewById(R.id.housenumber)
        edtPass = findViewById(R.id.houseprice)
        edtEmail = findViewById(R.id.housesize)
        edtAge = findViewById(R.id.age)
        ImageView = findViewById(R.id.her)
        btnupload = findViewById(R.id.btnupload)
        progress = ProgressDialog(this)
        progress.setTitle("Uploading")
        progress.setMessage("Please wait...")

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = firebaseStore.getReference()
        mAuth = FirebaseAuth.getInstance()
        ImageView.setOnClickListener {
            //Opening a gallery to select an image
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Customer                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              Picture "),
                PICK_IMAGE_REQUEST
            )
        }
        btnupload.setOnClickListener {
            var Subject = edtName.text.toString().trim()
            var Admissionno = edtPass.text.toString()
            var imageId = System.currentTimeMillis().toString()
            var userId = mAuth.currentUser?.uid
            //Check if the submitting field is empty
            if (Subject.isEmpty()) {
                edtName.setError("Enter the subject")
                edtName.requestFocus()

            }  else if (Admissionno.isEmpty()) {
                edtPass.setError("")
                edtPass.requestFocus()
            }  else if (Admissionno.isEmpty()) {
                edtEmail.setError("")
                edtEmail.requestFocus()
            }  else if (Admissionno.isEmpty()) {
                edtAge.setError("")
                edtAge.requestFocus()
            }
            else {
                //Proceed to upload data
                if (filePath == null) {
                    Toast.makeText(applicationContext, "Choose Image", Toast.LENGTH_SHORT).show()
                } else {
                    var ref = storageReference.child("House/$imageId")
                    progress.show()
                    ref.putFile(filePath).addOnCompleteListener {
                        progress.dismiss()
                        if (it.isSuccessful) {
                            //Proceed to save the data in the database
                            ref.downloadUrl.addOnSuccessListener {
                                var imageUrl = it.toString()
                                var Assignmentsdata = Customer(
                                    Subject,
                                    Admissionno,
                                    userId!!,
                                    imageId,
                                    imageUrl
                                )
                                var dbref = FirebaseDatabase.getInstance()
                                    .getReference().child("Houses/$imageId")
                                dbref.setValue(Assignmentsdata)
                                Toast.makeText(
                                    applicationContext,
                                    "Upload Success",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                it.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            filePath = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                ImageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }
}


