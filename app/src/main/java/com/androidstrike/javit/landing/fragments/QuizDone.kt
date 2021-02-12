package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstrike.javit.R
import com.androidstrike.javit.models.QuestionScore
import com.androidstrike.javit.utils.Common
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_quiz_done.*


class QuizDone : Fragment() {
    lateinit var database: FirebaseDatabase
    lateinit var questionScore: DatabaseReference

    var doneScore: Int? = 0
    var doneTotal: Int? = 0
    var doneCorrect: Int? = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_done, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        questionScore = database.getReference("QuestionScore")

        btn_try_again.setOnClickListener {
            val frag_retake = QuizLoad()

            val manager = fragmentManager
            val frag_transaction = manager?.beginTransaction()

            frag_transaction?.replace(R.id.fragment_container, frag_retake)
            frag_transaction?.commit()
        }

        //get scores from the quizInterface
        if (arguments?.getInt("SCORE") != null){
            doneScore = arguments?.getInt("SCORE")!!
            doneTotal = arguments?.getInt("TOTAL")!!
            doneCorrect = arguments?.getInt("CORRECT")!!

            txt_total_score.text = String.format("SCORE : %d", doneScore)
            txt_total_question_done.text = String.format("PASSED : %d / %d", doneCorrect, doneTotal)

            pb_done.max = doneTotal as Int
            pb_done.progress = doneCorrect as Int

            //upload score to db
            val newUserScore = QuestionScore(Common.student_name.toString(), doneScore.toString())
            questionScore.child(String.format("%s_score", Common.student_name)).setValue(newUserScore)
        }
    }
}