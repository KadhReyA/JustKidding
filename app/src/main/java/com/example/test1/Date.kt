package com.example.test1

class Date {
    var id: Int = 0
    var dateExp: String? = null
    constructor(id: Int, dateExp: String) {
        this.id = id
        this.dateExp = dateExp
    }
    constructor(dateExp: String) {
        this.dateExp =dateExp
    }
}