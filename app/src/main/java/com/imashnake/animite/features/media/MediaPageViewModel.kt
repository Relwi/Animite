package com.imashnake.animite.features.media

import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imashnake.animite.api.anilist.AnilistMediaRepository
import com.imashnake.animite.api.anilist.MediaQuery
import com.imashnake.animite.api.anilist.sanitize.media.Media
import com.imashnake.animite.api.anilist.type.MediaRankType
import com.imashnake.animite.api.anilist.type.MediaType
import com.imashnake.animite.core.extensions.plus
import com.imashnake.animite.features.destinations.MediaPageDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MediaPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mediaRepository: AnilistMediaRepository
) : ViewModel() {
    private val navArgs = MediaPageDestination.argsFrom(savedStateHandle)

    var uiState by mutableStateOf(MediaUiState())
        private set

    init {
        viewModelScope.launch {
            try {
                val mediaType = MediaType.safeValueOf(navArgs.mediaType)
                val media = mediaRepository
                    .fetchMedia(navArgs.id, mediaType)
                    .firstOrNull()?.getOrNull()?.let { Media.sanitize(it) }

                uiState = uiState.copy(
                    bannerImage = media?.bannerImage,
                    coverImage = media?.coverImage,
                    color = media?.color,
                    title = media?.title,
                    description = media?.description,
                    ranks = media?.rankings,
                    genres = media?.genres,
                    characters = media?.characters,
                    trailer = media?.trailer,
                )
            } catch(ioe: IOException) {
                TODO()
            }
        }
    }
}
