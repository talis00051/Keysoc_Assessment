package com.talischeung.keysoc_assessment.activity

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    private var loading = false

    abstract fun showLoading(): Boolean
}