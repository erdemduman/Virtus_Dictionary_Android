package com.example.dictionaryapp.ui.converter

import android.view.View

class Converter {
    companion object {
        @JvmStatic
        fun boolToVisibility(value: Boolean): Int {
            return when (value) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        }

        @JvmStatic
        fun nullCheck(value: String?): Int {
            return if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }
}
