package com.github.userfinder.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    var login : String? = null,
    @SerializedName("avatar_url")
    var avatarUrl : String? = null
)