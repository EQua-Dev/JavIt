package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.androidstrike.javit.R
import com.androidstrike.javit.adapters.OverViewAdapter
import com.androidstrike.javit.interfaces.OverViewAPI
import com.androidstrike.javit.models.OverView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.fragment_overview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewFragment : Fragment(), CardStackListener {

    private val adapter = OverViewAdapter()
    private lateinit var layoutManager:  CardStackLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager = CardStackLayoutManager(context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        //this below lines of code make the information on the landing page to be in swipeable cards
        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }

        //the code below gets the info from the REST API to be displayed on the swipeable cards
        OverViewAPI().getOverview().enqueue(object : Callback<List<OverView>>{
            override fun onResponse(
                call: Call<List<OverView>>,
                response: Response<List<OverView>>
            ) {
                response.body()?.let {
                    adapter.setOverviews(it)
                    Log.d("EQua", "onResponse: $it")
                }
            }

            override fun onFailure(call: Call<List<OverView>>, t: Throwable) {
                Log.d("EQua", "onFailure: ${t.message}")
            }
        })
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }
}