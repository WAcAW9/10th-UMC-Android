package com.wacaw.single_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.wacaw.single_ui.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt

class MainActivity : ComponentActivity() {
    // 바인딩 객체 변수 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 객체 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)

        // R.layout.activity_main -> binding.root
        setContentView(binding.root)

        // 우표 클릭시 색상 변화
        binding.layoutHappy.setOnClickListener {
            binding.tvHappyDesc.setBackgroundColor("#FFEFB6".toColorInt())
        }
        binding.layoutExciting.setOnClickListener {
            binding.tvExciting.setBackgroundColor("#CEE7F5".toColorInt())
        }
        binding.layoutNormal.setOnClickListener {
            binding.tvNormal.setBackgroundColor("#BEC3ED".toColorInt())
        }
        binding.layoutAnxious.setOnClickListener {
            binding.tvAnxious.setBackgroundColor("#B1D3B9".toColorInt())
        }
        binding.layoutAngry.setOnClickListener {
            binding.tvAngry.setBackgroundColor("#EB8B8B".toColorInt())
        }



    }
}
