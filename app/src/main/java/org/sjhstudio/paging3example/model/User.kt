package org.sjhstudio.paging3example.model

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String
): Parcelable