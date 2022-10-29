package com.example.week2assessment.view

interface ISearchArtist {
    fun search(
        term: String
        //do we have to put those param here?
        //if we do, what will happen if we pass values through mainactivity, values in artistservice or values from lainac
        //media: String, entity: String, limit: Int
    )
}