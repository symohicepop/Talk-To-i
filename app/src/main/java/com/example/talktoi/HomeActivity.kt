package com.example.talktoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    private  lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAuth= FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(this,userList)


        userRecyclerView = findViewById(R.id.userRecyclerView)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

     mDbRef.child("User").addValueEventListener(object : ValueEventListener{
         override fun onDataChange(snapshot: DataSnapshot) {

             userList.clear()
             for (postSnapshot in snapshot.children) {

                 val currentUser = postSnapshot.getValue(User::class.java)

                 if (mAuth.currentUser?.uid != currentUser?.uid) {

                     userList.add(currentUser!!)

                 }
             }
             adapter.notifyDataSetChanged()
         }

         override fun onCancelled(error: DatabaseError) {

         }

     })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

        if(item.itemId == R.id.logout) {
            //writing the logic for logout

            mAuth.signOut()
            val intent = Intent(this@HomeActivity,LogInActivity::class.java)
            finish()
            startActivity(intent)

            return true

        }
        return true
    }

}