package com.lordcodes.lazyviewbindingarticle.lazyfragment

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import kotlin.reflect.KProperty

fun <ViewT : View> Fragment.bindView(@IdRes idRes: Int): FragmentBinder<ViewT> {
    return FragmentViewBinder(this) {
        it.view!!.findViewById<ViewT>(idRes)
    }
}

interface FragmentBinder<out ViewT> {
    val value: ViewT

    fun isInitialized(): Boolean
}

inline operator fun <ViewT> FragmentBinder<ViewT>.getValue(
    thisRef: Any?,
    property: KProperty<*>
): ViewT = value

internal object UNINITIALIZED_VALUE

private class FragmentViewBinder<out ViewT : View>(
    val fragment: Fragment,
    val initializer: (Fragment) -> ViewT
) : FragmentBinder<ViewT>,
    LifecycleObserver {

    private var viewValue: Any? =
        UNINITIALIZED_VALUE

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer {
            it.lifecycle.addObserver(this)
        })
    }

    override val value: ViewT
        get() {
            if (viewValue === UNINITIALIZED_VALUE) {
                viewValue = initializer(fragment)
            }
            @Suppress("UNCHECKED_CAST")
            return viewValue as ViewT
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onViewDestroyed() {
        viewValue = UNINITIALIZED_VALUE
    }

    override fun isInitialized(): Boolean = viewValue !== UNINITIALIZED_VALUE

    override fun toString(): String =
        if (isInitialized()) {
            value.toString()
        } else {
            "Lazy value not initialized yet."
        }
}