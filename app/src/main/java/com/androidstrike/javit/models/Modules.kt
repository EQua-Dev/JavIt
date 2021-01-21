package com.androidstrike.javit.models

class Modules {

    var id: String? = null
    var module: String? = null
    var name: String? = null
    var text: String? = null
    var video_link: String? = null

    constructor() {}
    constructor(id: String?, module: String?, name: String?, text: String?, video_link: String?) {
        this.id = id
        this.module = module
        this.name = name
        this.text = text
        this.video_link = video_link
    }


}