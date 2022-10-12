package com.example.top10movies.util

import android.content.Context

class ResourceComparer {

    fun isEqual(context: Context, resourceId: Int, inputString: String): Boolean {
        return context.getString(resourceId) == inputString
    }

}