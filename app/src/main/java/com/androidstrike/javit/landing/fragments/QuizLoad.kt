package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.androidstrike.javit.models.Question
import com.androidstrike.javit.utils.Common
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_quiz_load.*

class QuizLoad : Fragment() {

    //Firebase
    lateinit var database: FirebaseDatabase
    lateinit var questions: DatabaseReference

    lateinit var questionsModel: Question


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_load, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        questions = database.reference.child("Questions")


        loadQuestion()

        btn_start_quiz.setOnClickListener {
            if (!it.isClickable)
                activity?.toast("Please wait while questions are loaded")
            else {
                val frag_quiz = Quiz()


                val manager = fragmentManager

                val frag_transaction = manager?.beginTransaction()

                frag_transaction?.replace(R.id.fragment_container, frag_quiz)
                frag_transaction?.commit()
            }
        }

    }

    private fun loadQuestion() {
        Log.d("EQUA", "loadQuestion: ")
        pb_quiz_load.visibility = View.VISIBLE
        btn_start_quiz.isClickable = false
        if (Common.questionList.size > 0)
            Common.questionList.clear()
        Log.d("EQUa", "loadQuestion: ${Common.questionList.size}")

        val questionsListener = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapShot: DataSnapshot in snapshot.children) {
                    questionsModel = postSnapShot.getValue(Question::class.java)!!
                    Log.d("EQUA", "onDataChange: Enter")
                    Common.questionList.add(questionsModel!!)

                    Log.d("EQUA", "onDataChange: ${Common.questionList[0].answerA}")

                }


                activity?.toast("Questions Loaded!!")

                Log.d("EQUA", "onDataChange: ${Common.questionList.size}")

            }

            //
//
            override fun onCancelled(error: DatabaseError) {
                activity?.toast(error.message)
            }

        }
        questions.addListenerForSingleValueEvent(questionsListener)

            Common.questionList.shuffle()

        pb_quiz_load.visibility = View.GONE

        btn_start_quiz.isClickable = true


    }
}