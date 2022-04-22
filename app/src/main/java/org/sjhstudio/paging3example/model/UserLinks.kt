package org.sjhstudio.paging3example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLinks(
    val html: String
): Parcelable