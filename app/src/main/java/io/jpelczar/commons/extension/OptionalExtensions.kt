package io.jpelczar.commons.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
fun <T> Optional<T>.orNull() = if (this.isPresent) this.get() else null