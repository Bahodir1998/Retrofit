package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.retrofit.adapters.PhotosAdapter
import com.example.retrofit.databinding.FragmentItemBinding
import com.example.retrofit.models.PhotosByType
import com.example.retrofit.models.Result
import com.example.retrofit.retrofit.Common
import com.example.retrofit.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"

class ItemFragment : Fragment() {

    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    lateinit var retrofitServices: RetrofitServices
    val type = "all"
    lateinit var list: ArrayList<Result>
    lateinit var photosAdapter: PhotosAdapter
    private val clientId = "KiZODt8OZoycMd1OZJkrPXspJyNTYrTFMvD952gWgOI"
    lateinit var binding : FragmentItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater)


        list = ArrayList()
        retrofitServices = Common.retrofitServices

        photosAdapter = PhotosAdapter(list,object : PhotosAdapter.MyItemClickListener{
            override fun onMyItemClick(result: Result) {
                val bundle = Bundle()
                bundle.putString("img",result.urls.regular)
                findNavController().navigate(R.id.secondFragment,bundle)
            }
        })

        binding.rv.adapter = photosAdapter

        val tabTitlList = arrayListOf<String>("all","new","animals","technology","nature")
        retrofitServices.getPhotos(tabTitlList[param1!!],clientId,30).enqueue(object : Callback<PhotosByType> {
            override fun onResponse(call: Call<PhotosByType>, response: Response<PhotosByType>) {
                if (response.isSuccessful){
                    list.addAll(response.body()?.results as ArrayList<Result>)
                    photosAdapter.notifyDataSetChanged()
                    Log.d("TTT", "onResponse: ${response.body()!!.results.size}")
                }
            }

            override fun onFailure(call: Call<PhotosByType>, t: Throwable) {
                Log.d("TTT", "onFailure: $t")
            }

        })



        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}