package org.tensorflow.lite.examples.posenet


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_logout.*
import kotlinx.android.synthetic.main.activity_logout.btnSignOut
import kotlinx.android.synthetic.main.activity_mainlogin.*
import org.tensorflow.lite.examples.posenet.Extensions.toast

class MainloginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainlogin)



        val btn_click_1 = findViewById<Button>(R.id.btn_click_1)
        val btn_click_2 = findViewById<Button>(R.id.btn_click_2)

        btn_click_1.setOnClickListener {
            val intent = Intent(this, AbdominalActivity::class.java)
            startActivity(intent)

            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                    Log.d("TAG1", "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG2", "Failed to read value.", error.toException())
                }
            })

        }

        btn_click_2.setOnClickListener {
            val intent = Intent(this, LowerbodyActivity::class.java)
            startActivity(intent)
        }

        btnSignOut.setOnClickListener {
            FirebaseUtils.firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            toast("로그아웃")
            finish()
        }



    }
}



