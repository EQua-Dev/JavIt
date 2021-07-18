package com.androidstrike.javit.landing.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.androidstrike.javit.models.Question
import com.androidstrike.javit.utils.Common
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_quiz.*
import java.lang.StringBuilder


class Quiz : Fragment(), View.OnClickListener {

    val INTERVAL: Long = 1000 //1 sec

    val TIMEOUT: Long = 15000 //15 secs

    var progressValue = 0

    var mCountDown: CountDownTimer? = null

    var index = 0
    private var score:Int = 0
    private var thisQuestion:Int = 0
    var totalQuestion:Int = Common.questionList.size
    private var correctAnswer:Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showQuestion(index)

        //makes the option buttons clickable
        btn_answerA?.setOnClickListener(this)
        btn_answerB?.setOnClickListener(this)
        btn_answerC?.setOnClickListener(this)
        btn_answerD?.setOnClickListener(this)

    }




    override fun onClick(v: View?) {
        //when a button is clicked...
        mCountDown?.cancel() //stops the timer
        if (index < totalQuestion){

            var clickedButton =  v as Button

            if (clickedButton.text.equals(Common.questionList[index].correctAnswer)){
                //if the correct answer is selected
                activity?.toast(clickedButton.text.toString())
                score += 10 //adds to the user's score
                correctAnswer++
                showQuestion(index++) //shows the next question
            }else{
                //if wrong answer is selected
                showQuestion(index++) //only shows next question
            }

            txt_score?.text = String.format("%d", score)
        }
        else{
            //if the quiz is done, sends the concluded info to the finish screen
            val frag_done = QuizDone()

            val bundle = Bundle()
            bundle.putInt("SCORE", score)
            bundle.putInt("TOTAL", totalQuestion!!)
            bundle.putInt("CORRECT", correctAnswer)
            frag_done.arguments = bundle


            val manager = fragmentManager

            val frag_transaction = manager?.beginTransaction()

            frag_transaction?.replace(R.id.fragment_container, frag_done)
            frag_transaction?.commit()
        }
    }

    private fun showQuestion(i: Int) {
        pb_quiz.visibility = View.VISIBLE
        if (i < totalQuestion){
            thisQuestion++
            txt_total_question?.text = String.format("%d/ %d", thisQuestion, totalQuestion)
            progress_bar?.progress = 0
            progressValue = 0

            question_text.text = Common.questionList[i].question.toString()
//            question_text.text = Common.questionList.get(index).question.toString()

            Log.d("EQUA", "showQuestion: ${Common.questionList.get(i).question.toString()}")

            btn_answerA.text = Common.questionList[i].answerA.toString()
            btn_answerB.text = Common.questionList[i].answerB.toString()
            btn_answerC.text = Common.questionList[i].answerC.toString()
            btn_answerD.text = Common.questionList[i].answerD.toString()

            pb_quiz.visibility = View.GONE
            mCountDown?.start()
        }
        else{
            val frag_done = QuizDone()

            val bundle = Bundle()
            bundle.putInt("SCORE", score)
            bundle.putInt("TOTAL", totalQuestion!!)
            bundle.putInt("CORRECT", correctAnswer)
            frag_done.arguments = bundle


            val manager = fragmentManager

            val frag_transaction = manager?.beginTransaction()

            frag_transaction?.replace(R.id.fragment_container, frag_done)
            frag_transaction?.commit()
        }
    }

    override fun onResume() {
        super.onResume()

        totalQuestion = Common.questionList.size
        mCountDown = object : CountDownTimer(TIMEOUT, INTERVAL){
            override fun onTick(millisUntilFinished: Long) {
                progress_bar?.progress = progressValue
                progressValue++
            }

            override fun onFinish() {
                mCountDown?.cancel()
                showQuestion(index++)
            }

        }
        showQuestion(index)
    }

    override fun onDestroy() {
        super.onDestroy()
    Log.d("EQUA", "onDestroy: Destroyed")
    }
}