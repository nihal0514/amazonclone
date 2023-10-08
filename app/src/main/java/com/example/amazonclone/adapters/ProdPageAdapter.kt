package com.example.amazonclone.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.databinding.ItemProdpageBinding
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.utils.getProgressDrawable
import com.example.amazonclone.utils.loadImage

@SuppressLint("NotifyDataSetChanged")
class ProdPageAdapter(private val prodListItem: ArrayList<ProdListItem>, private var categoryname: String, private val onProdClickListener: OnProdClickListener):
    RecyclerView.Adapter<ProdPageAdapter.ViewHolder>(){

    fun updateProducts(newProducts: List<ProdListItem>) {
        prodListItem.clear()
        prodListItem.addAll(newProducts)

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemProdpageBinding) : RecyclerView.ViewHolder(binding.root){
        private val progressDrawable = getProgressDrawable(binding.root.context)
        fun bind(products: ProdListItem){
            binding.prod= products
            binding.root.setOnClickListener{

            }
            binding.prodImage.loadImage(products.images?.get(0), progressDrawable)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProdpageBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(prodListItem[position])
        holder.itemView.setOnClickListener { onProdClickListener.onClick(prodListItem[position]) }

    }

    override fun getItemCount(): Int {
       return prodListItem.size
    }


}

class OnProdClickListener(val clickListener: (categories: ProdListItem) -> Unit) {
    fun onClick(country: ProdListItem) = clickListener(country)
}