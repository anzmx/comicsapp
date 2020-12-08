package com.zeltixdev.comicapp.ui.comic.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.networking.Resource
import com.zeltixdev.comicapp.repository.ComicRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ComicListViewModel @ViewModelInject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {

    private val _displayComicList = MutableLiveData<Resource<List<Comic>>>()
    val displayComicList: LiveData<Resource<List<Comic>>> get() = _displayComicList

    init {
        getComics()
    }

    private fun getComics() {
        viewModelScope.launch {
            comicRepository.getComics().collect {
                _displayComicList.value = it
            }
        }
    }
}