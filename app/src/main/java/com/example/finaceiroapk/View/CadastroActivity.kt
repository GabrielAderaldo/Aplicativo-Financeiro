package com.example.finaceiroapk.View

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finaceiroapk.Model.Usuario
import com.example.finaceiroapk.R
import com.google.firebase.auth.ActionCodeResult.ERROR
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro_tela.*

class CadastroActivity:AppCompatActivity(){

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_tela)
        auth = Firebase.auth

        bntCadastrar.setOnClickListener{
            createAccount(inpultEmail.text.toString(),inpultSenha.text.toString())
        }

    }


    fun createAccount(email:String,senha:String){

        if(inpultSenha.text.toString() != inpultConfirmarSenha.text.toString()){
            Log.w("ERROR","email ou senha invalida")
            Toast.makeText(baseContext,"Autorização deu  falha", Toast.LENGTH_SHORT).show()
        }else{
            auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this){
                    task ->
                if (task.isSuccessful){
                    //TODO:FAZER GERAR UM ID ALTOMATICAMENTE...
                    var usuario = Usuario(inputNome.text.toString(),inpultSobrenome.text.toString(),inpultPhone.text.toString(),inpultEmail.text.toString(),inpultSenha.text.toString())
                    val user = auth.currentUser!!
                    var dataBase = Firebase.database.reference
                    dataBase.child("usuarios").child(user.uid).setValue(usuario)
                    Toast.makeText(baseContext,"Usuario criado com sucesso!",Toast.LENGTH_SHORT).show()

                }else{
                    Log.w("Error", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Falha ao criar um usuario...",
                        Toast.LENGTH_SHORT).show()
                    //TODO:Criar a função updateUser...
                }
            }
        }
    }



}