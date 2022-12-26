package com.example.autos.data




const val TITLE = "TITLE"
const val DESCRIPTION = "DESCRIPTION"
const val LIKES="LIKES"
const val DISLIKES="DISLIKES"
const val USER="USER"
const val ID="ID"


data class Post(
    val _id : String,
    val Title: String,
    val Description : String,
    val User: String,
    val likes:Array<String>,
    val dislikes:Array<String>
)
