package com.hariagus.staterproject.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hariagus.staterproject.R
import com.hariagus.staterproject.core.data.source.Resource
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.ui.MovieAdapter
import com.hariagus.staterproject.core.utils.SortUtils
import com.hariagus.staterproject.core.utils.gone
import com.hariagus.staterproject.core.utils.showToastShort
import com.hariagus.staterproject.core.utils.startActivity
import com.hariagus.staterproject.core.utils.visible
import com.hariagus.staterproject.databinding.FragmentHomeBinding
import com.hariagus.staterproject.detail.DetailActivity
import com.jakewharton.rxbinding2.view.clicks
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()
    private val moviesAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(
            layoutInflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setList(SortUtils.NEWEST)

        moviesAdapter.onItemClick = { selectedData ->
            requireActivity().startActivity<DetailActivity>(
                DetailActivity.EXTRA_DATA to selectedData
            )
        }

        binding?.progressSpinKitList?.visible()
        with(binding?.rvMovie) {
            this?.setHasFixedSize(true)
            this?.adapter = moviesAdapter
        }

        onClickFAB()
    }

    @SuppressLint("CheckResult")
    private fun onClickFAB() {
        binding?.apply {
            fabNewest.clicks().subscribe { setList(SortUtils.NEWEST) }
            fabOldest.clicks().subscribe { setList(SortUtils.OLDEST) }
            fabPopularity.clicks().subscribe { setList(SortUtils.POPULARITY) }
        }
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> binding?.progressSpinKitList?.visible()
                is Resource.Success -> {
                    binding?.progressSpinKitList?.gone()
                    moviesAdapter.setData(movies.data)
                }
                is Resource.Error -> {
                    binding?.progressSpinKitList?.gone()
                    requireActivity().showToastShort(getString(R.string.there_is_an_error))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentHomeBinding = null
    }
}