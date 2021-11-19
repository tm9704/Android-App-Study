package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //timer 변수
        var timerTask: Timer? = null

        // 동작중인지 확인하기 위한 변수
        var isRunning = false
        var sec : Int = 0
        //변수 생성 (TextView, Button은 객체)
        val tv: TextView = findViewById(R.id.tv_hello)
        val btn: Button = findViewById(R.id.btn_kor)

        //버튼에 기능 추가
        btn.setOnClickListener {
            isRunning = !isRunning
            if(isRunning == true){
                //period 는 주기 1000이면 1000ms 마다 함수가 돎
                timerTask = kotlin.concurrent.timer(period = 10){
                    sec++
                    // 실시간으로 변화
                    runOnUiThread {
                        tv.text = (sec.toFloat()/100).toString()
                    }
                }
            }else{
                timerTask?.cancel()
            }

        }
    }
}