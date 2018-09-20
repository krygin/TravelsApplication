package com.travels.android.api.journeys

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import java.util.*


@Entity(tableName = "journey")
class JourneyModel(
        @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: String,
        @Relation(parentColumn = "id", entityColumn = "journey_id") val route: List<RouteItemModel>
)

@Entity(tableName = "route")
class RouteItemModel(
        @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) val uid: Long? = null,
        @ColumnInfo(name = "journey_id") val ownerId: Long? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "arrival") val arrival: Date,
        @ColumnInfo(name = "departure") val departure: Date,
        @Embedded val place: PlaceModel
)

@Entity(tableName = "place")
data class PlaceModel(
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String,
        @Embedded val location: LocationModel
)

data class LocationModel(
        @ColumnInfo(name = "lat") val lat: Double,
        @ColumnInfo(name = "lng") val lng: Double
)