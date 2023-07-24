package com.myanuarbf.hatscoffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myanuarbf.hatscoffee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
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
            LoginFirebase(email,password)
        }
    }
    private fun LoginFirebase(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Selamat datang $email",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}