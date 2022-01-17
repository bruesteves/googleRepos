package com.googlerepos.repo.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.googlerepos.R
import com.googlerepos.api.model.retrofit.Repo
import com.googlerepos.repos.adapter.ReposAdapter
import com.googlerepos.repos.viewmodel.ReposViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repos.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

private const val REPO_ARG = "REPO_ARG"

class RepoFragment : Fragment() {

    private var repo: Repo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repo = it.getSerializable(REPO_ARG) as Repo
        }

        setHasOptionsMenu(true)
    }

    private fun setupView() {
        repo?.let {
            Picasso.with(context).load(it.owner.avatarUrl).into(img)
            name.text = it.name
            login.text = it.owner.login
            description.text = it.description
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(repo: Serializable) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(REPO_ARG, repo)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}
