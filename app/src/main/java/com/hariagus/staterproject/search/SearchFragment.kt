package com.hariagus.staterproject.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.hariagus.staterproject.core.ui.MovieAdapter
import com.hariagus.staterproject.core.utils.gone
import com.hariagus.staterproject.core.utils.visible
import com.hariagus.staterproject.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _searchBinding: FragmentSearchBinding? = null
    private val binding get() = _searchBinding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _searchBinding = FragmentSearchBinding.inflate(
            layoutInflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMovie?.adapter = movieAdapter

        binding?.svMovie?.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    getItemFromDb(query.orEmpty())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    getItemFromDb(newText.orEmpty())
                    return true
                }
            })
        }
    }

    private fun getItemFromDb(searchText: String) {
        var text = searchText
        text = "%$text%"

        viewModel.searchForItems(text).observe(this) { list ->
            movieAdapter.setData(list)
            if (list.isNullOrEmpty()) {
                binding?.apply {
                    noDataLayout.visible()
                    layoutSearch.gone()
                    rvMovie.gone()
                }
            } else {
                binding?.apply {
                    rvMovie.visible()
                    layoutSearch.gone()
                    noDataLayout.gone()
                }
            }
        }
    }

}