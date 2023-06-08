package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false) //툴바 제목 없애기

        val tabLayout = binding.tab
        val viewPage = binding.viewpager
        viewPage.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when (position) {
                0 -> tab.text = "냉장고" // Display "냉장고" for the first tab
                1 -> tab.text = "냉동고" // Display "냉동고" for the second tab
                2 -> tab.text = "실온" // Display "실온" for other tabs
            }
        }.attach()
    }
    }