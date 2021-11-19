package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sec : Int = 0
        val tv: TextView = findViewById(R.id.tv_hello)

        //period 는 주기 1000이면 1/1000초 마다 함수가 돌아
        timer(period = 1000){
            sec++
            tv.text = sec.toString()
        }

//        //변수 생성 (TextView, Button은 객체)
//        val tv: TextView = findViewById(R.id.tv_hello)
//        val btn: Button = findViewById(R.id.btn_kor)
//
//        //버튼에 기능 추가
//        btn.setOnClickListener {
//            tv.text = "안녕"
//        }
    }
}