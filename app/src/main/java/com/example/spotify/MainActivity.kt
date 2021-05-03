package com.example.spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.spotify.fragments.Biblioteca
import com.example.spotify.fragments.Buscar
import com.example.spotify.fragments.Home
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var Content: FrameLayout? = null
    private var mOnNavigationItemSelectListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.nave_inicio -> {
                val fragment = Home.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nave_search -> {
                val fragment = Buscar.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nave_biblioteca ->{
                val fragment = Biblioteca.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Content = content
        bottom_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectListener)
        val fragment = Home.newInstance()
        addFragment(fragment)
    }
    private fun addFragment(fragmente: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmente, fragmente.javaClass.simpleName)
            .commit()
    }

}