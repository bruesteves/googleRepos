package com.googlerepos.api.model.retrofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Repo(
    val name: String,
    val description : String,
    val owner: Owner,
    @SerializedName("default_branch")
    val defaultBranch: String,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int
): Serializable

class Owner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl : String
)