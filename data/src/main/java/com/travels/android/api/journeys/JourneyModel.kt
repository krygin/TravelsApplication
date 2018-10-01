package com.travels.android.api.journeys

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(tableName = "journey")
class JourneyModel(
        @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: String
)

@Entity(
        tableName = "route",
        foreignKeys = [
            ForeignKey(
                    entity = JourneyModel::class,
                    parentColumns = ["uid"],
                    childColumns = ["journey_uid"],
                    onDelete = ForeignKey.CASCADE
            )]
)
class RouteItemModel(
        @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long? = null,
        @ColumnInfo(name = "journey_uid") val ownerId: Long? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "arrival") val arrival: Date,
        @ColumnInfo(name = "departure") val departure: Date,
        @Embedded(prefix = "place_") val place: PlaceModel
)

@Entity(tableName = "place")
data class PlaceModel(
        @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "lat") val lat: Double,
        @ColumnInfo(name = "lng") val lng: Double
)