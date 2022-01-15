package com.googlerepos.api.model.retrofit

import com.google.gson.annotations.SerializedName

class Repo(
    val name: String,
    val description : String,
    val owner: Owner
)

class Owner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl : String
)