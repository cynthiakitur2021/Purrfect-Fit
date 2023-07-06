package com.example.nyumbakumi

class House {
    var houseNumber:String = ""
    var houseSize:String = ""
    var housePrice:String = ""
    var userID:String = ""
    var houseID:String = ""
    var houseImage:String = ""

    constructor(
        houseNumber: String,
        houseSize: String,
        housePrice: String,
        userID: String,
        houseID: String,
        houseImage: String
    ) {
        this.houseNumber = houseNumber
        this.houseSize = houseSize
        this.housePrice = housePrice
        this.userID = userID
        this.houseID = houseID
        this.houseImage = houseImage
    }
    constructor()
}