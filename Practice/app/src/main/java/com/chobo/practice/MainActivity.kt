package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    fun main(){
        //xml파일의 layout을 불러옴
        setContentView(R.layout.activity_main)

        //timer 변수
        var timerTask: Timer? = null

        // 동작중인지 확인하기 위한 변수
        //var isRunning = false

        // 각 스테이지 카운트
        var stage = 1
        var sec : Int = 0
        //변수 생성 (TextView, Button은 객체), 위젯 선택
        val tv: TextView = findViewById(R.id.tv_random)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val btn: Button = findViewById(R.id.btn_main)
        val random_box = Random()
        //0~10 정수형 반환
        val num = random_box.nextInt(1001)

        //tv.text라는 위젯에 랜덤한 숫자를 표기
        tv.text = ((num.toFloat())/100).toString()
        btn.text = "시작"

        //버튼에 기능 추가
        btn.setOnClickListener {
            //isRunning = !isRunning
            stage++
            if(stage == 2){
                //period 는 주기 1000이면 1000ms 마다 함수가 돎
                timerTask = kotlin.concurrent.timer(period = 10){
                    sec++
                    // 실시간으로 변화
                    runOnUiThread {
                        tv_t.text = (sec.toFloat()/100).toString()
                    }
                }
                btn.text = "정지"
            }else if(stage == 3){
                timerTask?.cancel()
                val point = abs(sec-num).toFloat()/100
                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            }else if(stage == 1){
                //다시 함수를 부름, 초기화
                main()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main()
    }
}