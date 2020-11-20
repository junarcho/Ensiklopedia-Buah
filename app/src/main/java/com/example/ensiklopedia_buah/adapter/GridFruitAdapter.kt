package com.example.ensiklopedia_buah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ensiklopedia_buah.entity.Fruits
import com.example.ensiklopedia_buah.R

class GridFruitAdapter(private val listFruit: ArrayList<Fruits>) : RecyclerView.Adapter <GridFruitAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemCliked(data: Fruits)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_fruit, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFruit.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listFruit[position].photo)
            .into(holder.imgPhoto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemCliked(listFruit[holder.adapterPosition])
        }
    }

}