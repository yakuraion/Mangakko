package net.yakuraion.mangakko.genres

import androidx.fragment.app.Fragment

interface GenresFeature {

    fun getGenresFragment(): Fragment

    interface Owner {

        fun onGenreChosen(genre: String)
    }
}
