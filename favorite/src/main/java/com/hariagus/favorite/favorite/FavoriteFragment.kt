package com.hariagus.favorite.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hariagus.favorite.databinding.FragmentFavoriteBinding
import com.hariagus.favorite.di.favoriteModule
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.ui.MovieAdapter
import com.hariagus.staterproject.core.utils.gone
import com.hariagus.staterproject.core.utils.startActivity
import com.hariagus.staterproject.core.utils.visible
import com.hariagus.staterproject.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _favoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _favoriteBinding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _favoriteBinding = FragmentFavoriteBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        binding?.progressSpinKitList?.visible()
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movies ->
            movieAdapter.setData(movies)
            showDataFavorite(movies)
        }

        movieAdapter.onItemClick = { selectedData ->
            requireActivity().startActivity<DetailActivity>(
                DetailActivity.EXTRA_DATA to selectedData
            )
        }

        with(binding?.rvMovieFavorite) {
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun showDataFavorite(movies: List<Movie>) {
        if (movies.isEmpty()) {
            binding?.apply {
                progressSpinKitList.gone()
                emptyLayout.visible()
                rvMovieFavorite.gone()
            }
        } else {
            binding?.apply {
                progressSpinKitList.gone()
                emptyLayout.gone()
                rvMovieFavorite.visible()
            }
        }
    }
}