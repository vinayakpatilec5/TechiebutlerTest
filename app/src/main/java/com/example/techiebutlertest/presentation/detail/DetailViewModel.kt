package com.example.techiebutlertest.presentation.detail

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techiebutlertest.domain.entities.PostDisplayData
import com.example.techiebutlertest.domain.util.getSpanData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _postData: MutableStateFlow<PostDisplayData?> = MutableStateFlow(null)
    val postData = _postData.asStateFlow()
    fun loadPostData(bundle: Bundle) {
        viewModelScope.launch {
            _postData.emit(
                PostDisplayData(
                    1,
                    bundle.getInt(DetailFragment.POST_ID),
                    bundle.getString(DetailFragment.POST_TITLE) ?: "",
                    getSpanData(bundle.getString(DetailFragment.POST_BODY) ?: "")
                )
            )
        }
    }
}