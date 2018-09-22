package com.example.asus.footballschedule.home

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.db.Favorite
import com.example.asus.footballschedule.db.database
import com.example.asus.footballschedule.favorite.FavoriteFragment
import com.example.asus.footballschedule.next.NextFragment
import com.example.asus.footballschedule.past.PastFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.db.delete

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//
//        try {
//            database.use {
//                delete(Favorite.TABLE_FAVORITE)
//            }
//            Toast.makeText(this, "Removed to favorite", Toast.LENGTH_SHORT).show()
//        } catch (e: SQLiteConstraintException){
//            Log.e("SQLITE", "Error to remove")
//        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.past -> {
                    loadPastFragment(savedInstanceState)
                }
                R.id.next -> {
                    loadNextFragment(savedInstanceState)
                }
                R.id.favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.past
    }

    private fun loadPastFragment(savedInstanceState : Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PastFragment.newInstance(), PastFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadNextFragment(savedInstanceState : Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextFragment.newInstance(), NextFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?){
        Log.i("TEst favorite", "joss")
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment.newInstance(), FavoriteFragment::class.simpleName)
                    .commit()
        }
        Log.i("LEwat"," ")
    }
}
