package com.developersbreach.simplesearchapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sports(
    val icon: Int,
    val title: String,
    val originated: String,
    val about: String
) : Parcelable