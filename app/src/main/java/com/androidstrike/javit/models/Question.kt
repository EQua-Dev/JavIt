package com.androidstrike.javit.models

class Question {
    var answerA: String = ""
    var answerB: String = ""
    var answerC: String = ""
    var answerD: String = ""
    var correctAnswer: String = ""
    var question: String = ""


    constructor()
    constructor(answerA: String, answerB: String, answerC: String, answerD: String, correctAnswer: String, question: String) {
        this.answerA = answerA
        this.answerB = answerB
        this.answerC = answerC
        this.answerD = answerD
        this.correctAnswer = correctAnswer
        this.question = question
    }

}