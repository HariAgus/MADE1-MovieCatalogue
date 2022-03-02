package com.hariagus.staterproject.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hariagus.staterproject.R
import com.hariagus.staterproject.core.domain.model.Movie
import com.hariagus.staterproject.core.utils.loadImage
import com.hariagus.staterproject.databinding.ActivityDetailBinding
import com.jakewharton.rxbinding2.view.clicks
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null) {
            loadDataDetail(detailMovie)
        }
    }

    @SuppressLint("CheckResult")
    private fun loadDataDetail(movie: Movie) {
        binding.apply {
            tvTitleDetail.text = movie.title
            tvLanguage.text = movie.originalLanguage
            tvPopularity.text = movie.popularity.toString()
            tvOverview.text = movie.overview
            tvReleaseDate.text = movie.releaseDate
            tvScoreDetail.text = movie.voteAverage.toString()
            loadImage(getString(R.string.url_poster, movie.posterPath), roundedPosterDetail)
            loadImage(getString(R.string.url_poster, movie.backdropPath), posterBg)

            var statusFavorite = movie.isFavorite
            setFavorite(statusFavorite)
            fabFavorite.clicks().subscribe {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setFavorite(statusFavorite)
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_primary
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_border
                )
            )
        }
    }
}