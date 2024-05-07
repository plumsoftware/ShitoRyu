package com.example.shitoryu.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class EventData(
    val dayOfMonth: Int = -1,
    val month: Int = -1,
    val year: Int = -1,
    val hour: Int = -1,
    val minute: Int = -1,
    val isCompetition: Int = -1,
    val place: String = "",
    val group: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dayOfMonth)
        parcel.writeInt(month)
        parcel.writeInt(year)
        parcel.writeInt(hour)
        parcel.writeInt(minute)
        parcel.writeInt(isCompetition)
        parcel.writeString(place)
        parcel.writeString(group)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventData> {
        override fun createFromParcel(parcel: Parcel): EventData {
            return EventData(parcel)
        }

        override fun newArray(size: Int): Array<EventData?> {
            return arrayOfNulls(size)
        }
    }
}
