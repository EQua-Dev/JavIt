package com.androidstrike.javit.models

class User {

    lateinit var name:String
    lateinit var email: String
    lateinit var phone: String

    constructor(){

    }

    constructor(name: String, email: String, phone: String) {
        this.name = name
        this.email = email
        this.phone = phone
    }

    constructor(uid: String, email: String?)

}