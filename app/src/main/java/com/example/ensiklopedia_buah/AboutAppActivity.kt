package com.example.ensiklopedia_buah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class AboutAppActivity : AppCompatActivity() {

    private var title: String = "Tentang Developer"

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        setActionBarTitle(title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}