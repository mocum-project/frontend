package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val TAB_NUMS=3
class FragmentAdapter(fgManager : FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fgManager, lifecycle){

    override fun getItemCount(): Int = TAB_NUMS
    override fun createFragment(position: Int): Fragment {
        when (position){
            0-> return ExampleFragment()
            1-> return ExampleFragmentTwo()
            2-> return ExampleFragmentThree()
    }
        return  ExampleFragment()
    }
    }