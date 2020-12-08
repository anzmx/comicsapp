package com.zeltixdev.comicapp.ui.comic.detail

import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zeltixdev.comicapp.entity.Comic
import com.zeltixdev.comicapp.repository.ComicRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class ComicInitParams(val comicId: String)

class ComicDetailViewModel @AssistedInject constructor(
        private val comicRepository: ComicRepository,
        @Assisted private val initParams: ComicInitParams
): ViewModel(){

    private val _displayComic = MutableLiveData<Comic>()
    val displayComic: LiveData<Comic> get() = _displayComic

    init {
        getComic(initParams.comicId)
    }

    private fun getComic(comicId: String){
        viewModelScope.launch {
                comicRepository.getComicDetail(comicId).collect {
                    _displayComic.value = it

                }
            }
        }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(initParams: ComicInitParams): ComicDetailViewModel
    }

    companion object {
        fun provideFactory(
                assistedFactory: AssistedFactory,
                initParams: ComicInitParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(initParams) as T
            }
        }
    }
}