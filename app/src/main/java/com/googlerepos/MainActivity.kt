package com.googlerepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.googlerepos.repos.ui.ReposFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, ReposFragment()).commit()
    }
}