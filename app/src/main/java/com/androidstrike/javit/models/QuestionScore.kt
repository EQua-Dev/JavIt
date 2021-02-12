package com.androidstrike.javit.models

class QuestionScore {

    lateinit var userName: String
    lateinit var userScore: String

    constructor()
    constructor(userName: String, userScore: String) {
        this.userName = userName
        this.userScore = userScore
    }


}