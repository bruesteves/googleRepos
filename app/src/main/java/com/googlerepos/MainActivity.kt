package com.googlerepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.googlerepos.repos.ui.ReposFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         setupFragment()
    }

    private fun setupFragment() {
        changeFragment(ReposFragment())
    }

    fun changeFragment(fragment: Fragment){
        setTitle(getString(R.string.repos_name))
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    fun addFragment(fragment: Fragment, title: String){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(title)
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            setTitle(getString(R.string.repos_name))
        } else {
            super.onBackPressed()
        }
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             android.R.id.home -> {
                 onBackPressed()
                 return true
             }
         }
         return super.onOptionsItemSelected(item)
     }
     
     fun setTitle(title: String){
        supportActionBar?.setTitle(title)
     }
}