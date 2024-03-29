package com.example.spotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.R
import com.example.spotify.model.Secao
import com.example.spotify.model.dados
import kotlinx.android.synthetic.main.fragment_buscar.*
import kotlinx.android.synthetic.main.secao_item.*
import kotlinx.android.synthetic.main.secao_item.view.*

class Buscar : Fragment() {
    private lateinit var secaoAdapter: SecaoAdapter

    companion object {
        fun newInstance(): Buscar{
            val fragmentBuscar =  Buscar()
            val argumentos = Bundle()
            fragmentBuscar.arguments = argumentos
            return fragmentBuscar
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        secaoAdapter = SecaoAdapter(dados())
        recycler_view_secao.adapter = secaoAdapter
        recycler_view_secao.layoutManager = GridLayoutManager(context, 2)

    }

    private inner class SecaoAdapter(private val secoes: MutableList<Secao>): RecyclerView.Adapter<SecaoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecaoHolder {
            return SecaoHolder(layoutInflater.inflate(R.layout.secao_item, parent, false))

        }

        override fun getItemCount(): Int = secoes.size

        override fun onBindViewHolder(holder: SecaoHolder, position: Int) {
            val secao = secoes[position]
            holder.bind(secao)
        }
    }

    private inner class SecaoHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(secao: Secao){
            itemView.imagem_secao.setImageResource(secao.secao)
            itemView.nome_secao.text = secao.nomeSecao
        }
    }
}