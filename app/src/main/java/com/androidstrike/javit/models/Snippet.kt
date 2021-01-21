package com.androidstrike.javit.models

class Snippet {

//    var id: String? = null
    var name: String? = null
    var text: String? = null
    var input_link: String? = null
    var output_link: String? = null

    constructor()
    constructor(name: String?, text: String?, input_link: String?, output_link: String?) {
        this.name = name
        this.text = text
        this.input_link = input_link
        this.output_link = output_link
    }


}