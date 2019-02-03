package com.lordcodes.lazyviewbindingarticle.lazyfragment

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <ViewT : View> Fragment.bindView(@IdRes idRes: Int): ReadOnlyProperty<Fragment, ViewT> {
    return FragmentBinder(this) {
        it.view!!.findViewById<ViewT>(idRes)
    }
}

private class FragmentBinder<out ViewT : View>(
    val fragment: Fragment,
    val initializer: (Fragment) -> ViewT
) : ReadOnlyProperty<Fragment, ViewT>, LifecycleObserver {
    private object EMPTY
    private var viewValue: Any? = EMPTY

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer {
            it.lifecycle.addObserver(this)
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): ViewT {
        if (viewValue === EMPTY) {
            viewValue = initializer(fragment)
        }
        @Suppress("UNCHECKED_CAST")
        return viewValue as ViewT
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onViewDestroyed() {
        viewValue = EMPTY
    }
}