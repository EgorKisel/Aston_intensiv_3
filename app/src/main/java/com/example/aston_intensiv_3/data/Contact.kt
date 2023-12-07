package com.example.aston_intensiv_3.data
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val id: Int? = null,
    val firstName: String? = "",
    val lastName: String? = "",
    val phoneNumber: String? = "",
): Parcelable
