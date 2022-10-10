package com.example.top10movies.util

import android.util.Log

object MovieUtil {

    /**
     * Test if ID is valid by:
     * Not containing characters except for numbers
     * Not being longer than 7 digits (As of writing this 2022-10-10, the latest ID is 1033962.) - https://www.themoviedb.org/talk/56766de392514179e3004f46
     * Not being empty
     */

    fun validateMovieId(id: String): Boolean {
        if (id.length > 7) return false
        if (id.isEmpty()) return false
        if (isNotNumeric(id)) return false
        return true
    }

    private fun isNotNumeric(toCheck: String): Boolean {
        Log.d("test", toCheck)
        return toCheck.toDoubleOrNull() == null
    }

}