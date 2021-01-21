package com.androidstrike.javit.interfaces

import android.view.View

interface IRecyclerItemClickListener {
    fun onItemClickListener(view: View, position: Int)
}