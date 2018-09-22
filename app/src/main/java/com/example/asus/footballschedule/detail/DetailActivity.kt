package com.example.asus.footballschedule.detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.db.Favorite
import com.example.asus.footballschedule.db.database
import com.example.asus.footballschedule.retrofit.GetDataService
import com.example.asus.footballschedule.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var homeBadge : String
    private lateinit var awayBadge : String
    private var menuItem : Menu? = null
    private lateinit var presenter: DetailPresenter
    private var isFavorite: Boolean = false
    private lateinit var bundle : Bundle
    private lateinit var toast : Toast
    private lateinit var id : String
    private lateinit var Iv_home_badge : ImageView
    private lateinit var Iv_away_badge : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Iv_home_badge = findViewById<ImageView>(R.id.iv_home_badge)
        Iv_away_badge = findViewById<ImageView>(R.id.iv_away_badge)

        bundle = intent.getBundleExtra("bundle")
        id = bundle.getString("eventId")
        favoriteState()

        val retrofit = RetrofitClientInstance.getInstance()
        val service = retrofit.create(GetDataService::class.java)

        presenter = DetailPresenter(this, service, bundle)
        toast = Toast.makeText(this,"Add Success", Toast.LENGTH_SHORT)
        tv_date_event_detail.text = bundle.getString("eventDate")
        tv_strhome_detail.text = bundle.getString("homeTeamName")
        tv_straway_detail.text = bundle.getString("awayTeamName")
        presenter.getData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()){

                isFavorite = true
            }
        }
    }

    fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to id)
            }
            Toast.makeText(this, "Removed to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Log.e("SQLITE", "Error to remove")
        }
    }

    fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to bundle.getString("eventId"),
                        Favorite.HOME_TEAM_NAME to bundle.getString("homeTeamName"),
                        Favorite.AWAY_TEAM_NAME to bundle.getString("awayTeamName"),
                        Favorite.HOME_SCORE to bundle.getString("homeScore"),
                        Favorite.AWAY_SCORE to bundle.getString("awayScore"),
                        Favorite.EVENT_DATE to bundle.getString("eventDate")
                        )
            }
            toast.show()
        } catch (e: SQLiteConstraintException){
            Log.e("SQLITE", "Error insert table")
        }
    }
    fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun showData(urlImage: String, idData: Int) {
        if(idData == 1){
            homeBadge = urlImage
            Glide.with(this).load(urlImage).into(Iv_home_badge)
        }
        else if(idData == 2){
            awayBadge = urlImage
            Glide.with(this).load(urlImage).into(Iv_away_badge)
        }
    }
}
