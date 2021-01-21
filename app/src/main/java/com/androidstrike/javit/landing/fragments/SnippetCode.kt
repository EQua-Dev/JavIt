package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstrike.javit.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_snippet_code.*

class SnippetCode : Fragment() {
    var snippetName: String? = null
    var snippetInfo: String? = null
    var snippetInput: String? = null
    var snippetOutput: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snippet_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (arguments?.getString("snipName") != null){
            snippetName = arguments?.getString("snipName")!!
            snippetInfo = arguments?.getString("snipText")!!
            snippetInput = arguments?.getString("snipIn")!!
            snippetOutput = arguments?.getString("snipOut")!!
        }

        loadSnippet()
    }

    private fun loadSnippet() {

        tv_snip_name.text = "$snippetName"
        tv_snip_info.text = "$snippetInfo"

        Picasso.with(context)
            .load(snippetInput)
            .into(iv_input)

        Picasso.with(context)
            .load(snippetOutput)
            .into(iv_output)
    }
}