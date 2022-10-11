package com.example.top10movies.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieUtilTest {

    @Test
    fun `ID contains non number characters returns false`() {
        val result = MovieUtil.validateMovieId("abc123")
        assertThat(result).isFalse()
    }

    @Test
    fun `ID is longer than 7 characters returns false`() {
        val result = MovieUtil.validateMovieId("123456789")
        assertThat(result).isFalse()
    }

    @Test
    fun `ID is empty returns false`() {
        val result = MovieUtil.validateMovieId("")
        assertThat(result).isFalse()
    }

    @Test
    fun `ID is in correct format returns true`() {
        val result = MovieUtil.validateMovieId("123456")
        assertThat(result).isTrue()
    }

}