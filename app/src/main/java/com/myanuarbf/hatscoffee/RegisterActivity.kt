package com.myanuarbf.hatscoffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.myanuarbf.hatscoffee.databinding.ActivityRegisterBinding
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val email = binding.inpEmail.text.toString()
            val password = binding.inpPassword.text.toString()

            if(email.isEmpty()){
                binding.inpEmail.error = "Email Harus diisi!"
                binding.inpEmail.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.inpEmail.error = "Email tidak Valid!"
                binding.inpEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.inpPassword.error = "password  Harus diisi!"
                binding.inpPassword.requestFocus()
                return@setOnClickListener
            }

            if(password.length<6){
                binding.inpPassword.error = "Password Minimal 6 Karakter "
                binding.inpPassword.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,password)
        }

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun RegisterFirebase(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}