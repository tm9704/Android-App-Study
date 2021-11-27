package com.chobo.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    // 제한 인원
    var p_num = 3
    // 현재 사람(참가자 번호), p_num과 비교해서 게임 중지 상황 만들기
    var k = 1
    val point_list = mutableListOf<Float>()
    var isBlind = false

    fun start(){
        setContentView(R.layout.activity_start)
        val tv_pnum: TextView = findViewById(R.id.tv_pnum)
        val btn_minus: TextView = findViewById(R.id.btn_minus)
        val btn_plus: TextView = findViewById(R.id.btn_plus)
        val btn_start: TextView = findViewById(R.id.btn_start)
        val btn_blind: TextView = findViewById(R.id.btn_blind)

        tv_pnum.text = p_num.toString()

        btn_minus.setOnClickListener{
            p_num--
            if(p_num == 0){
                p_num = 1
            }
            tv_pnum.text = p_num.toString()
        }

        btn_plus.setOnClickListener{
            p_num++
            tv_pnum.text = p_num.toString()
        }

        btn_blind.setOnClickListener{
            isBlind = !isBlind
            if(isBlind == true){
                btn_blind.text = "Blind 모드 on"
            }else {
                btn_blind.text = "Blind 모드 off"
            }
        }

        btn_start.setOnClickListener {
            main()
        }
    }

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
        val tv: TextView = findViewById(R.id.tv_pnum)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val tv_people: TextView = findViewById(R.id.tv_people)
        val btn: Button = findViewById(R.id.btn_start)
        val random_box = Random()
        //0~10 정수형 반환
        val num = random_box.nextInt(1001)

        //tv.text라는 위젯에 랜덤한 숫자를 표기
        tv.text = ((num.toFloat())/100).toString()
        btn.text = "시작"
        //$k를 하면 변수가 적용
        tv_people.text = "참가자 $k"

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
                        if(isBlind == false) {
                            tv_t.text = (sec.toFloat() / 100).toString()
                        }else if(isBlind == true && stage == 2){
                            tv_t.text = "???"
                        }
                    }
                }
                btn.text = "정지"
            }else if(stage == 3){
                tv_t.text = (sec.toFloat() / 100).toString()
                timerTask?.cancel()
                val point = abs(sec-num).toFloat()/100
                point_list.add(point)
                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            }else if(stage == 1){
                if(k<p_num) {
                    k++
                    //다시 함수를 부름, 초기화(재귀함수)
                    main()
                }else{
                    end()
                }
            }
        }
    }

    fun end(){
        setContentView(R.layout.activity_end)

        val tv_last: TextView = findViewById(R.id.tv_last)
        val tv_lpoint: TextView = findViewById(R.id.tv_lpoint)
        val btn_init: TextView = findViewById(R.id.btn_init)

        tv_lpoint.text = (point_list.maxOrNull()).toString()
        var index_last = point_list.indexOf(point_list.maxOrNull())
        tv_last.text = "참가자" + index_last.toString()

        btn_init.setOnClickListener{
            point_list.clear()
            k = 1
            start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }
}