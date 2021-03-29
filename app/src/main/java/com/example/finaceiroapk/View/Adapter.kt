package com.example.finaceiroapk.View

import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finaceiroapk.Model.Transacoes
import com.example.finaceiroapk.R
import kotlinx.android.synthetic.main.holder_listar.view.*

class Adapter:RecyclerView.Adapter<Adapter.TransitionViewHolder>(){

    var list = mutableListOf<Transacoes>()

    fun atualizar(lista: List<Transacoes>){
        this.list.clear()
        this.list.addAll(lista)
        notifyDataSetChanged()
    }

    class TransitionViewHolder(view: View):RecyclerView.ViewHolder(view){


        fun bind(transicoes: Transacoes){
            itemView.lbNomeProduto.text = transicoes.nome_transicao
            itemView.lbValor.text = "RS " + transicoes.valor.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransitionViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.holder_listar,parent,false)
        return TransitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransitionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}