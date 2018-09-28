package com.example.externkoutsodimakis.kotlinfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()

        mAuth = FirebaseAuth.getInstance()
        var employee = Employee("janes bond", "secret agent", "london", 52)
        databaseRef.setValue(employee)

        databaseRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                var value = p0!!.value as HashMap<String, Any>

            }

            override fun onCancelled(p0: DatabaseError) {}
        })
    }

    override fun onStart() {
        super.onStart()
        currentUser = mAuth!!.currentUser
        if (currentUser != null){
            Toast.makeText(this,"used is logged in",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"user is not logged in",Toast.LENGTH_LONG).show()
        }
    }
}


