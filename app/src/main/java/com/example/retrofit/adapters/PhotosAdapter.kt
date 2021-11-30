package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.PhotoItemBinding
import com.example.retrofit.models.Result
import com.squareup.picasso.Picasso

class PhotosAdapter(var list: List<Result>,var myItemClickListener: MyItemClickListener): RecyclerView.Adapter<PhotosAdapter.Vh>() {

    inner class Vh(var photoItemBinding: PhotoItemBinding): RecyclerView.ViewHolder(photoItemBinding.root){

        fun onBind(result: Result){
            Picasso.get().load(result.urls.small).into(photoItemBinding.imgView)
            photoItemBinding.imgView.setOnClickListener {
                myItemClickListener.onMyItemClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface MyItemClickListener{
        fun onMyItemClick(result: Result)
    }
}