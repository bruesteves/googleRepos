package com.googlerepos.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.googlerepos.R
import com.googlerepos.api.model.retrofit.Repo
import com.googlerepos.repos.ui.RepoView

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
    var reposList: MutableList<Repo> = mutableListOf()

    var fullList: MutableList<Repo> = mutableListOf()

    lateinit var onClickListener: (Repo) -> Unit

    class ViewHolder(val view: RepoView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_repo, parent, false) as RepoView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo: Repo = reposList[position]
        val view = holder.view

        view.bind(repo)

        view.setOnClickListener {
            onClickListener.invoke(repo)
        }
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    fun setItems(list: List<Repo>, onClickListener: (Repo) -> Unit) {
        this.onClickListener = onClickListener
        reposList = list.toMutableList()
        fullList = reposList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        reposList = reposList.filter { it.name.contains(query) }.toMutableList()
        notifyDataSetChanged()
    }

    fun setFullList() {
        reposList = fullList
        notifyDataSetChanged()
    }
}