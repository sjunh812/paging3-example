package org.sjhstudio.paging3example.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Urls(
    @SerializedName("regular")
    val regularImage: String
): Parcelable