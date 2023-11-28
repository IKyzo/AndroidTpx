package com.example.androidtpx.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtpx.SigninActivity
import com.example.androidtpx.databinding.FragmentDashbordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DashbordFragment : Fragment(){

    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding =
            FragmentDashbordBinding.inflate(layoutInflater, container, false)

        setData(binding)
        return binding.root}

    private fun setData(binding: FragmentDashbordBinding) {

        binding.textView.text=user.email
        binding.button.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, SigninActivity::class.java)
            startActivity(intent)
        }

    }

}