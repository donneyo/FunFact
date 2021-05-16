package com.neyo.funfact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.neyo.funfact.databinding.ActivityLoginBinding
import com.neyo.funfact.databinding.ActivityRegisterBinding

/*import kotlinx.android.synthetic.main.activity_login.**/


class LoginActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener {
            if( binding.editTextEmail.text.isNotEmpty() || binding.editTextPassword.text.isNotEmpty()){
                signInUser( binding.editTextEmail.text.trim().toString(),
                    binding.editTextPassword.text.trim().toString())

            }else{
                Toast.makeText(this,"Input required", Toast.LENGTH_LONG).show()
            }
        }


         binding.tvRegister.setOnClickListener{
             val intent = Intent(this, RegisterActivity::class.java)
             startActivity(intent);
         }
    }



    fun signInUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->

                if(task.isSuccessful){
                    Log.e( "Task Message", "Successful.....");

                    var intent = Intent(this,MainActivity::class.java);
                    startActivity(intent)

                }else{
                    Log.e( "Task Message", "Failed....."+task.exception);
                }

            }
    }

}