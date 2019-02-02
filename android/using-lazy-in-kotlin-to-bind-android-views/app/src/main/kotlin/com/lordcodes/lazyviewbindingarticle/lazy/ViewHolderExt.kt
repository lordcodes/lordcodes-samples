package com.lordcodes.lazyviewbindingarticle.lazy

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

fun <ViewT : View> RecyclerView.ViewHolder.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazyUnsychronized {
        itemView.findViewById<ViewT>(idRes)
    }
}