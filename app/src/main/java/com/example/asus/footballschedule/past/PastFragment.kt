package com.example.asus.footballschedule.past

import android.support.v4.app.Fragment
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.asus.footballschedule.R
import com.example.asus.footballschedule.model.Schedule
import com.example.asus.footballschedule.adapter.RecyclerViewAdapter
import com.example.asus.footballschedule.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PastFragment : Fragment(), AnkoComponent<Context>, PastView {

    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: PastPresenter
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar

    companion object {

        fun newInstance(): PastFragment {
            return PastFragment()
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RecyclerViewAdapter(schedules) {
            val bundle =
                    bundleOf("eventDate" to it.eventDate.toString(),
                            "homeTeamName" to it.homeTeamName.toString(),
                            "awayTeamName" to it.awayTeamName.toString(),
                            "homeScore" to it.homeScore.toString(),
                            "awayScore" to it.awayScore.toString(),
                            "homeGoalDetail" to it.homeGoalDetails.toString(),
                            "awayGoalDetail" to it.awayGoalDetails.toString(),
                            "homeGoalKeeper" to it.homeGoalKeeper.toString(),
                            "awayGoalKeeper" to it.AwayGoalKeeper.toString(),
                            "homeDefense" to it.homeDefense.toString(),
                            "awayDefense" to it.awayDefense.toString(),
                            "homeMidfield" to it.homeMidfield.toString(),
                            "awayMidfield" to it.awayMidfield.toString(),
                            "homeForward" to it.homeForward.toString(),
                            "awayForward" to it.awayForward.toString(),
                            "eventId" to it.eventId.toString()
                    )
            startActivity<DetailActivity>("bundle" to bundle)
        }
        listEvent.adapter = adapter

        presenter = PastPresenter(this)
        presenter.getPastSchedule()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>) : View {
        return with(ui){
            verticalLayout{
                backgroundColor = Color.GRAY
                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.list_schedule
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }

            }
        }
    }

    override fun showSchedule(data : List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }
}
