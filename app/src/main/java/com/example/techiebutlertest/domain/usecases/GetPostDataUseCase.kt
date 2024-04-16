package com.example.techiebutlertest.domain.usecases

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.paging.PagingData
import androidx.paging.map
import com.example.techiebutlertest.domain.entities.PostDisplayData
import com.example.techiebutlertest.domain.repository.HomeRepository
import com.example.techiebutlertest.domain.util.getSpanData
import com.example.techiebutlertest.domain.util.makeFirstLetterCapital
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/*
    Here adding some Extra fake computation on data to make first letter capital of each word
    + adding first char as bold styling for post body
 */
class GetPostDataUseCase @Inject constructor(private val repository: HomeRepository) {
    operator fun invoke(pageLimit: Int): Flow<PagingData<PostDisplayData>> {
        return repository.getPostData(pageLimit).map {
            it.map { post ->
                PostDisplayData(
                    post.userId,
                    post.id,
                    makeFirstLetterCapital(post.title),
                    getSpanData(makeFirstLetterCapital(post.body))
                )
            }
        }
    }
}