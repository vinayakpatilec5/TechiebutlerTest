package com.example.techiebutlertest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.techiebutlertest.domain.usecases.GetPostDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostDataUseCase: GetPostDataUseCase
) : ViewModel() {

    /*
        Keeping batch size as 10 while making api call
    */
    companion object {
        const val PAGE_LIMIT = 10
    }

    val posts = getPostDataUseCase(PAGE_LIMIT).cachedIn(viewModelScope)
}