package com.virtusdictionary.app.ui.view.converter

import android.view.View

class Converter {
    companion object{
        @JvmStatic
        fun boolToVisibility(value: Boolean): Int {
            return when (value) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        }
    }
}
