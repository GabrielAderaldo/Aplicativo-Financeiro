package com.example.finaceiroapk.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finaceiroapk.Model.Transacoes
import com.example.finaceiroapk.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_listar.*
import kotlinx.android.synthetic.main.holder_listar.*

class ListarActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    lateinit var juntar:List<Transacoes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)
        adapter = Adapter()
        rView.adapter = adapter
        rView.layoutManager = LinearLayoutManager(baseContext)


        bntFlutuante.setOnClickListener {
            startActivity(Intent(this,ReceitaActivity::class.java))
        }

        bntFlutuanteMenos.setOnClickListener {
            startActivity(Intent(this,DespesaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Firebase.database.reference.child("usuarios").child(Firebase.auth.currentUser!!.uid).child("despesas").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val despesas = snapshot.children.map { it.getValue(Transacoes::class.java)!! }
                juntar = despesas.takeLast(5)
                //adapter.atualizar(despesas.takeLast(5))

            }
            override fun onCancelled(error: DatabaseError) {

            }

        })

        Firebase.database.reference.child("usuarios").child(Firebase.auth.currentUser!!.uid).child("receitas").addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val receitas = snapshot.children.map { it.getValue(Transacoes::class.java)!! }
                var aux = receitas.takeLast(5)
                juntar = juntar + aux
                //adapter.atualizar(receitas.take(5))
                adapter.atualizar(juntar.take(10))
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })



    }


}