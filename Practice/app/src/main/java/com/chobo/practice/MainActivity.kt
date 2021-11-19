package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

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
        val tv: TextView = findViewById(R.id.tv_random)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val btn: Button = findViewById(R.id.btn_main)

        val random_box = Random()
        //0~10 정수형 반환
        val num = random_box.nextInt(1001)
        tv.text = ((num.toFloat())/100).toString()


        //버튼에 기능 추가
        btn.setOnClickListener {
            isRunning = !isRunning
            if(isRunning == true){
                //period 는 주기 1000이면 1000ms 마다 함수가 돎
                timerTask = kotlin.concurrent.timer(period = 10){
                    sec++
                    // 실시간으로 변화
                    runOnUiThread {
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
            }else{
                timerTask?.cancel()
                val point = abs(sec-num).toFloat()/100
                tv_p.text = point.toString()
            }
        }
    }
}