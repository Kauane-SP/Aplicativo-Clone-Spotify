package com.example.spotify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.R
import com.example.spotify.api.SpotfyApi
import com.example.spotify.api.retroft
import com.example.spotify.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {
    private lateinit var categoriaAdapter: CategoriaAdapter
    companion object {
        fun newInstance(): Home{
            val fragmentHome =  Home()
                val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categorias = arrayListOf<Categoria>()

        categoriaAdapter = CategoriaAdapter(categorias)
        recycler_view_categorias.adapter = categoriaAdapter
        recycler_view_categorias.layoutManager = LinearLayoutManager(context)

        retroft()
            .create(SpotfyApi::class.java)
            .ListCategorias()
            .enqueue(object : Callback<Categorias>{
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                    Toast.makeText(context, "Erro do servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                  if(response.isSuccessful){
                      response.body()?.let {
                          categoriaAdapter.categorias.clear()
                          categoriaAdapter.categorias.addAll(it.categorias)
                          categoriaAdapter.notifyDataSetChanged()
                      }
                  }
                }
            })

    }

    private inner class CategoriaAdapter(internal val categorias: MutableList<Categoria>): RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
            return CategoriaHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))

        }

        override fun getItemCount(): Int = categorias.size


        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)

        }

        private inner class CategoriaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(categoria: Categoria) {
                itemView.text_titulo.text = categoria.titulo
                itemView.recycler_albuns.adapter = AlbunsAdapter(categoria.albuns)
                itemView.recycler_albuns.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
        }
    }

    private inner class AlbunsAdapter(private val albuns: List<Album>): RecyclerView.Adapter<AlbunsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolder {
            return AlbunsHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun getItemCount(): Int = albuns.size

        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)

        }
    }

    private inner class AlbunsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (album: Album){
         Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
        }
    }
}