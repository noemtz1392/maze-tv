package mx.com.android.maze.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Commons {
    fun getCurrentDate(): String {
        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(date)
    }

    fun getCurrentDateFormat(): String {
        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("dd 'de' MMMM yyyy", Locale("es", "MX"))
        return sdf.format(date).lowercase()
    }
}