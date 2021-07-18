package com.androidstrike.javit.utils

import com.androidstrike.javit.models.Question
import com.androidstrike.javit.models.User


object Common {
    var currentUser: User? = null
    var student_name: String? = null
    var indexId: String? = null
    var questionList: MutableList<Question> =  ArrayList<Question>()
}