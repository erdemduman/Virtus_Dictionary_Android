package com.virtusdictionary.app.ui.view.popup

import androidx.lifecycle.MutableLiveData

class LoadingDialog(isVisible: Boolean) {
    var isVisible: MutableLiveData<Boolean> = MutableLiveData()

    init {
        this.isVisible.value = isVisible
    }
}

class ToastDialog(text: Int, duration: Int) {
    var text: MutableLiveData<Int> = MutableLiveData()
    var duration: MutableLiveData<Int> = MutableLiveData()

    init {
        this.text.value = text
        this.duration.value = duration
    }
}