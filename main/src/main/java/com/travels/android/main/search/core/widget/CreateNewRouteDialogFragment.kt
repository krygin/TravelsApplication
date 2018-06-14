package com.travels.android.main.search.core.widget

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.travels.android.main.search.core.Location
import com.travels.android.main.search.core.Place
import java.util.*

class CreateOrUpdateRouteDialog(context: Context, val onSaveRouteListener: (RouteItem) -> Unit) : AlertDialog(context) {

    init {
        setTitle("EditRoute")
        setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", { dialog, which -> onSaveRouteListener(RouteItem(Place(Location(0.0, 0.0), Random().nextInt().toString()), Date())) })
    }

}