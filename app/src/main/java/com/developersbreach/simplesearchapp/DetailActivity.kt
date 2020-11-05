package com.developersbreach.simplesearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sports: Sports? = intent.getParcelableExtra("DETAIL_SPORTS_DATA")

        findViewById<TextView>(R.id.detail_title_text).text = sports?.title
        findViewById<TextView>(R.id.detail_subtitle_text).text = sports?.originated
        findViewById<TextView>(R.id.detail_about_text).text = sports?.about
        sports?.icon?.let {
            findViewById<ImageView>(R.id.detail_image_view).setImageResource(it)
        }
    }
}