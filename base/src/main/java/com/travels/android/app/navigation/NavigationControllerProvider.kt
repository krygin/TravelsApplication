package com.travels.android.app.navigation

import android.app.Activity
import android.support.v4.app.Fragment

interface NavigationControllerProvider {
    val navigationController: NavigationController
}

fun Activity.navigationController() = (applicationContext as NavigationControllerProvider).navigationController

fun Fragment.navigationController() = activity?.navigationController()