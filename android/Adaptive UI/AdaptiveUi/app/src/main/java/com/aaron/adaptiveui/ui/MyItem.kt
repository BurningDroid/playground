package com.aaron.adaptiveui.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MyItem(
    val id: Int,
    val data: String,
) : Parcelable