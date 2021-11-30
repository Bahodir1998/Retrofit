package com.example.retrofit

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.databinding.FragmentSecondBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val url = arguments?.getString("img")

        Picasso.get().load(Uri.parse(url)).into(binding.imgView,object :Callback{
            override fun onSuccess() {
                binding.progresbar.visibility = View.INVISIBLE
                binding.imgView.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
            }

        })

        return binding.root
    }

}