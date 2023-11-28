package com.example.androidtpx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.androidtpx.databinding.ActivityHomeBinding
import com.example.androidtpx.databinding.ActivityRegisterBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser

    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var actionToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)

        initMenu(binding)

        navigateMenu(navView)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        /*
        binding.textView.text=user.email
        binding.button.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }*/

        //binding= DataBindingUtil.setContentView(this,R.layout.activity_home);


        //Bundle extra = getIntent().getExtras()
        //val ss:String = intent.getStringExtra("userDetails").toString()
        //binding.emailDisplay.text = ss;

        /*
        if (user == null) {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            binding.emailDisplay.text = user!!.email.toString()
        }
        binding.LogoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
        */

    }

    private fun initMenu(binding: ActivityHomeBinding) {
        drawerLayout = binding.drawerLayout
        navView = binding.designNavigationView

        actionToggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, 0, 0)
        drawerLayout.addDrawerListener(actionToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//make icon menu visible
        actionToggle.syncState()


    }

    private fun navigateMenu(navigationView: NavigationView) {
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.user -> {

                    true
                }

                R.id.profile -> {
                    Toast.makeText(
                        baseContext,
                        "profile",
                        Toast.LENGTH_SHORT,
                    ).show()
                    true
                }

                R.id.logout -> {


                    auth.signOut()
                    val intent = Intent(this, SigninActivity::class.java)
                    startActivity(intent)


                    true
                }
                else-> {false}
            }


        }

    }

}