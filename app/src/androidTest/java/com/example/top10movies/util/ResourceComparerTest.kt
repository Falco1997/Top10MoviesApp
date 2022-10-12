package com.example.top10movies.util

import com.example.top10movies.R
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceComparer: ResourceComparer
    @Before
    fun setup() {
        resourceComparer = ResourceComparer()
    }

    @After
    fun teardown() {
        
    }

    @Test
    fun stringResourceSameAsInputString_returnsTrue() {
        setup()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "Top10Movies")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceNotSameAsInputString_returnsFalse() {
        setup()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "NotTop10Movies")
        assertThat(result).isFalse()
    }

}