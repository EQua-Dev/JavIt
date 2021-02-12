package com.androidstrike.javit.utils

import com.androidstrike.javit.models.Question
import com.androidstrike.javit.models.User


object Common {
    lateinit var currentUser: User
    var student_name: String? = null
    var indexId: String? = null
    var questionList: MutableList<Question> =  ArrayList<Question>()
}