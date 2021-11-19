package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //변수 생성
        val tv: TextView = findViewById(R.id.tv_hello)
        //내용 선택
        tv.text = "안녕"

//        val textView: TextView = findViewById(R.id.android_text) as TextView
//        textView.setOnClickListener {
//            textView.text = getString(R.string.name)
//        }
    }
}