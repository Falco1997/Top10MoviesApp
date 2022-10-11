package com.example.top10movies.util

object MovieUtil {

    /**
     * Test if ID is valid by:
     * Not containing characters except for numbers
     * Not being longer than 7 digits (As of writing this 2022-10-10, the latest ID is 1033962.) - https://www.themoviedb.org/talk/56766de392514179e3004f46
     * Not being empty
     */

    fun validateMovieId(id: String): Boolean {
        if (!isNumericWithoutDecimals(id)) return false
        if (id.length > 7) return false
        if (id.isEmpty()) return false
        return true
    }

    private fun isNumericWithoutDecimals(toCheck: String): Boolean {
        return try {
            toCheck.toInt()
            true
        } catch (e: java.lang.NumberFormatException) {
            false
        }
    }

}