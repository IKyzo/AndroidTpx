package com.example.androidtpx

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidtpx.databinding.ActivitySigninBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SigninActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivitySigninBinding

    lateinit var email : EditText
    lateinit var password: EditText
    lateinit var signinButton: Button

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
        binding= DataBindingUtil.setContentView(this,R.layout.activity_signin);
        auth = FirebaseAuth.getInstance();



        binding.signInButton.setOnClickListener() {

            val changePage = Intent(this, RegisterActivity::class.java)

            var email = binding.username.text.toString()
            var password = binding.password.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "Email not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this, "Password not valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(
                                this@SigninActivity, "Authentication Success.",
                                Toast.LENGTH_SHORT
                            ).show()

                            val user: FirebaseUser? = auth.getCurrentUser()
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.putExtra("userDetails", email)
                            startActivity(intent)
                            finish()


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(
                                this@SigninActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    })

        }
    }
}