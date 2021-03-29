package com.example.finaceiroapk.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.finaceiroapk.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    //declarando o firebase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        //local para criar bnts
        bntLogar.setOnClickListener {
            signIn(inpultLoginNome.text.toString(),inpultLoginSenha.text.toString())
        }

        txtRegistro.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,CadastroActivity::class.java))
        })

        updateUI(currentUser)
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        //TODO: Implementar depois, quando sentir necessidade
    }


    fun signIn(email:String,senha:String){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                val user = auth.currentUser
                Toast.makeText(baseContext,"Você pode logar",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,ListarActivity::class.java))
                finish()
                updateUI(user)
            }else{
                Log.w("ERROR","email ou senha invalida",task.exception)
                Toast.makeText(baseContext,"Autorização deu  falha", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
}