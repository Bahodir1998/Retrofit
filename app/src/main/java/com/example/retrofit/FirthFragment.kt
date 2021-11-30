package com.example.retrofit

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.adapters.ViewPagerAdapter
import com.example.retrofit.databinding.FragmentFirthBinding
import com.example.retrofit.databinding.TabItemBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FirthFragment : Fragment() {


    lateinit var binding: FragmentFirthBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirthBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0F
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("  Home")
        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewPagerAdapter
        val tabTitlList = arrayListOf<String>("ALL","NEW","ANIMALS","TECHNOLOGY","NATURE")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabItemBinding = TabItemBinding.bind(
                LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null, false)
            )
            tabItemBinding.tv.text = tabTitlList[position]

            if (position==0){
                tabItemBinding.tabIndicator.visibility = View.VISIBLE
                tabItemBinding.tv.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                tabItemBinding.tabIndicator.visibility = View.INVISIBLE
            }
            tab.customView = tabItemBinding.root
            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val bind = TabItemBinding.bind(customView!!)
                bind.tabIndicator.visibility = View.VISIBLE
                bind.tv.setTextColor(Color.parseColor("#FFFFFF"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val bind = TabItemBinding.bind(customView!!)
                bind.tabIndicator.visibility = View.INVISIBLE
                bind.tv.setTextColor(Color.parseColor("#808A93"))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return binding.root
    }

}