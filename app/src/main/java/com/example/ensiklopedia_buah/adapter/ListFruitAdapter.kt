package com.example.ensiklopedia_buah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ensiklopedia_buah.entity.Fruits
import com.example.ensiklopedia_buah.R

class ListFruitAdapter(private val listFruit: ArrayList<Fruits>) : RecyclerView.Adapter<ListFruitAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemCliked(data: Fruits)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvEnglish: TextView = itemView.findViewById(R.id.tv_item_english)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_fruit, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFruit.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val fruits = listFruit[position]
        Glide.with(holder.itemView.context)
            .load(fruits.photo)
            //.apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = fruits.name
        holder.tvEnglish.text = fruits.english_name
        holder.tvDetail.text = fruits.detail
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemCliked(listFruit[holder.adapterPosition])
        }
    }


}