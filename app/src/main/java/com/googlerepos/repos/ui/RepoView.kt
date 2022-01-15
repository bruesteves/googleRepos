package com.googlerepos.repos.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import com.googlerepos.R
import com.googlerepos.api.model.retrofit.Repo
import com.squareup.picasso.Picasso

open class RepoView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    private lateinit var img: ImageView
    private lateinit var name: TextView
    private lateinit var login: TextView
    private lateinit var description: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()

        img = findViewById(R.id.img)
        login = findViewById(R.id.login)
        name = findViewById(R.id.name)
        description = findViewById(R.id.description)
    }

    open fun bind(repo: Repo) {
        Picasso.with(context).load(repo.owner.avatarUrl).into(img)

        name.text = repo.name
        login.text = repo.owner.login
        description.text = repo.description

        description.visibility = if(!repo.description.isNullOrEmpty()) View.VISIBLE else View.GONE
    }
}