package com.github.userfinder.data.response

import com.github.userfinder.data.model.User
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    var totalCount : Int,
    @SerializedName("incomplete_results")
    var incompleteResults : Boolean,
    @SerializedName("total_count")
    var items : List<User>
)