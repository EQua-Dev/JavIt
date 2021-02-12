package com.androidstrike.javit.models

class Ranking {

    lateinit var userName: String
    var score: Long? = null

    constructor()
    constructor(userName: String, score: Long?) {
        this.userName = userName
        this.score = score
    }


}