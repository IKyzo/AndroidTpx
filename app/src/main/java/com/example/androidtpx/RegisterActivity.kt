package com.example.androidtpx

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidtpx.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    lateinit var email : EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        auth = FirebaseAuth.getInstance();



        binding.LoginNow.setOnClickListener() {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signUpButton.setOnClickListener() {


            var email = binding.username.text.toString()
            var password = binding.password.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Email not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            /*if (password.isEmpty()){
                Toast.makeText(this, "Password not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/
            //with regular expression
            val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d).{6,}\$")
            if (!password.matches(passwordRegex)) {
                Toast.makeText(
                    this,
                    "Password must be at least 6 characters long, contain at least one uppercase letter, and one digit.",
                            Toast.LENGTH_SHORT
                ).show()
                //binding.progress.visibility = View.GONE
                return@setOnClickListener
            }


            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            baseContext,
                            "Authentication Success.",
                            Toast.LENGTH_SHORT,
                        ).show()


                    } else {


                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }





        }
    }
}