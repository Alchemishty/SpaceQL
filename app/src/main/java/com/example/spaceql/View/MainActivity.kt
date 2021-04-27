package com.example.spaceql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.nav_mission -> {
                println("mission pressed")
                replaceFragment(MissionFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_home -> {
                println("home pressed")
                replaceFragment(CompanyFragment())
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
