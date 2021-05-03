package com.example.spotify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spotify.R
import com.example.spotify.fragmentsTab.Albuns
import com.example.spotify.fragmentsTab.Artistas
import com.example.spotify.fragmentsTab.PlayLists
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems


class Biblioteca : Fragment() {

    companion object {
        fun newInstance(): Biblioteca {
            val fragmentBiblioteca = Biblioteca()
            val argumentos = Bundle()
            fragmentBiblioteca.arguments = argumentos
            return fragmentBiblioteca
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
        return inflater.inflate(R.layout.fragment_biblioteca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter =   FragmentPagerItemAdapter(fragmentManager,FragmentPagerItems.with(context)
            .add("PlayLists", PlayLists::class.java)
            .add("Artistas", Artistas::class.java )
            .add("Albuns",Albuns::class.java )
            .create()
        )
    }
}