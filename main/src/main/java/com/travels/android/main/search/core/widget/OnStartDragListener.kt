package com.travels.android.main.search.core.widget

import android.support.v7.widget.RecyclerView

interface OnStartDragListener {

    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    fun onMoveFinished()
}