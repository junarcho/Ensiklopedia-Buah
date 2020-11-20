package com.example.ensiklopedia_buah

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ENGLISH = "extra_english"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_BENEFIT = "extra_benefit"
    }

    private var title: String = ""

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setActionBarTitle(title)

        val photo: ImageView = findViewById(R.id.tv_img_detail)
        val tvName: TextView = findViewById(R.id.tv_name_detail)
        val tvEnglishName: TextView = findViewById(R.id.tv_english_detail)
        val tvDetail: TextView = findViewById(R.id.tv_detail)
        val tvBenefit: TextView = findViewById(R.id.tv_benefit)

        val imgDataReceived = intent.getIntExtra(EXTRA_IMAGE, 0)
        val nameReceived = intent.getStringExtra(EXTRA_NAME)
        val englishReceived = intent.getStringExtra(EXTRA_ENGLISH)
        val detailReceived = intent.getStringExtra(EXTRA_DETAIL)
        val benefitReceived = intent.getStringExtra(EXTRA_BENEFIT)

        tvEnglishName.isClickable = false

        Glide.with(this)
            .load(imgDataReceived)
            .into(photo)
        tvName.text = nameReceived
        tvEnglishName.text = englishReceived
        tvDetail.text = detailReceived
        tvBenefit.text = benefitReceived
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}