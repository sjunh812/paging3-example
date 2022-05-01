package org.sjhstudio.paging3example.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    @SerializedName("results")
    val images: List<UnsplashImage>
): Parcelable