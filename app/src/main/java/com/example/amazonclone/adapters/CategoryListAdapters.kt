package com.example.amazonclone.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.databinding.ItemTopdealsBinding
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.utils.getProgressDrawable
import com.example.amazonclone.utils.loadImage


@SuppressLint("NotifyDataSetChanged")
class CategoryListAdapters(private val categoryList: ArrayList<CategoryListItem>, private val onClickListener: OnClickListener)
    :RecyclerView.Adapter<CategoryListAdapters.ViewHolder>(){

    fun updateCategory(newCategory: List<CategoryListItem>) {
        categoryList.clear()
        categoryList.addAll(newCategory)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemTopdealsBinding) : RecyclerView.ViewHolder(binding.root){
        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(category: CategoryListItem){
            binding.item=category;
            binding.root.setOnClickListener{

            }
            binding.dealsIv.loadImage(category.icon, progressDrawable)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopdealsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
        holder.itemView.setOnClickListener { onClickListener.onClick(categoryList[position]) }
    }

}

class OnClickListener(val clickListener: (categories: CategoryListItem) -> Unit) {
    fun onClick(country: CategoryListItem) = clickListener(country)
}


