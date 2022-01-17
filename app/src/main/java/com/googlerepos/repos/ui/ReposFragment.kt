package com.googlerepos.repos.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.googlerepos.MainActivity
import com.googlerepos.R
import com.googlerepos.repo.ui.RepoFragment
import com.googlerepos.repos.adapter.ReposAdapter
import com.googlerepos.repos.viewmodel.ReposViewModel
import kotlinx.android.synthetic.main.fragment_repos.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment() {

    private val viewModel by viewModel<ReposViewModel>()

    val adapter: ReposAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupObservers()
        viewModel.getRepos()
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    private fun setupObservers() {
        viewModel.repos.observe(requireActivity(), {
            it?.let { it1 ->
                adapter.setItems(it1) {
                    (activity as MainActivity).addFragment(RepoFragment.newInstance(it), it.name)
                }
                recyclerViewRepos.adapter = adapter
                recyclerViewRepos.layoutManager = LinearLayoutManager(context)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.search)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)

        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty())
                    adapter.setFullList()
                else
                    adapter.filter(newText)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lines -> {
                recyclerViewRepos.layoutManager = LinearLayoutManager(context)
            }
            R.id.grid -> {
                recyclerViewRepos.layoutManager = GridLayoutManager(context, 2)
            }
        }
        recyclerViewRepos.adapter?.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }
}
