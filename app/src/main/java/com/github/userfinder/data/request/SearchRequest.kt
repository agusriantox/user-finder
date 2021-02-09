package com.github.userfinder.data.request

data class SearchRequest(
   var name: String,
   var page: Int,
   var perPage: Int
)