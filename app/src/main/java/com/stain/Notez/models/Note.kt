package com.stain.Notez.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(var id: Int,
                var title: String,
                var text: String): Parcelable, Comparable<Note> {

    override fun compareTo(other: Note) = compareValuesBy(this, other, {it.id}, {it.title}, {it.text})
}