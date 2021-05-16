package com.neyo.funfact

import android.content.Intent
import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.neyo.funfact.databinding.ActivityLoginBinding
import com.neyo.funfact.databinding.ActivityRegisterBinding
/*import kotlinx.android.synthetic.main.activity_register.**/

class RegisterActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)*/

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        auth = FirebaseAuth.getInstance()


        binding.btnRegister.setOnClickListener {
            if( binding.editEmail.text.isNotEmpty() || binding.editPassword.text.isNotEmpty()){
                createUser( binding.editEmail.text.trim().toString(),
                binding.editPassword.text.trim().toString())

            }else{
                Toast.makeText(this,"Input required", Toast.LENGTH_LONG).show()
            }
        }
        binding.tvLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent);
        }
    }
    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->

                if(task.isSuccessful){
                    Log.e( "Task Message", "Successful.....");

                    var intent = Intent(this,LoginActivity::class.java);
                    startActivity(intent)

                }else{
                    Log.e( "Task Message", "Failed....."+task.exception);
                }

            }
    }
}