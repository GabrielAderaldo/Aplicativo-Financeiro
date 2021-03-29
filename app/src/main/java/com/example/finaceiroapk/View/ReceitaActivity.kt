package com.example.finaceiroapk.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finaceiroapk.Model.Transacoes
import com.example.finaceiroapk.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_receita.*


class ReceitaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita)
        auth = Firebase.auth

        this.bntSalvarReceita.setOnClickListener {
        salvarDados()
        }



    }

    fun salvarDados(){
        if(inpultReceitaNome.text.toString().isNotEmpty() && inpultReceita.text.toString().isNotEmpty() && inpultReceita.text.toString().toDouble() > 0.0 ){

            var transacoes = Transacoes(inpultReceitaNome.text.toString(),inpultReceita.text.toString().toDouble())
            var dataBase = Firebase.database.reference
            dataBase.child("usuarios").child(auth.currentUser!!.uid).child("receitas").push().setValue(transacoes)
            Toast.makeText(baseContext,"O valor: "+inpultReceita.text.toString().toDouble()+" Foi salvo com sucesso!", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(baseContext,"Verifique se você digitou corretamente todos os campos...",Toast.LENGTH_SHORT).show()
        }

    }
}