package com.lordcodes.lazyviewbindingarticle.lazy

fun <T> lazyUnsychronized(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)