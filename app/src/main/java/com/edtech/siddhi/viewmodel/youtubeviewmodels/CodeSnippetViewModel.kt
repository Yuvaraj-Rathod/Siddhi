package com.edtech.siddhi.viewmodel.youtubeviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edtech.siddhi.model.YoutubeVideo
import com.edtech.siddhi.repository.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CodeSnippetViewModel @Inject constructor(private val youtubeRepository: YoutubeRepository) : ViewModel(){

    private val _codesnippetplaylist = MutableLiveData<List<YoutubeVideo>>(emptyList())
    val codesnippetplaylist: LiveData<List<YoutubeVideo>>
        get() = _codesnippetplaylist

    init {
        _codesnippetplaylist.value = youtubeRepository.codesnippetPlaylist()
    }
}