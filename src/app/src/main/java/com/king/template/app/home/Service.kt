package com.king.template.app.home

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.LeadingMarginSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.king.template.R

class Service : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.service)

        val nutritionString = getString(R.string.service)
        val indentSize = 100 // 设置缩进的大小，可以根据需要调整

        val spannableString = SpannableString(nutritionString)
        spannableString.setSpan(
            LeadingMarginSpan.Standard(indentSize, 0),
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val nutritionTextView = findViewById<TextView>(R.id.service_description)
        nutritionTextView.text = spannableString
    }
}