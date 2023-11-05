package com.example.amazonclone.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonclone.databinding.ItemCartBinding
import com.example.amazonclone.databinding.ItemTopdealsBinding
import com.example.amazonclone.model.cart.ItemsItems
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.utils.getProgressDrawable
import com.example.amazonclone.utils.loadImage


@SuppressLint("NotifyDataSetChanged")
class CartListAdapter(private val cartList: ArrayList<ItemsItems>, private val onCartClickListener: OnCartClickListener,
                      private val listener: ItemClickListener)
    :RecyclerView.Adapter<CartListAdapter.ViewHolder>(){

    fun updateCart(newcartlist: List<ItemsItems>) {
        cartList.clear()
        cartList.addAll(newcartlist)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        private val progressDrawable = getProgressDrawable(binding.root.context)
        fun bind(cart: ItemsItems,listener: ItemClickListener){
            binding.cart= cart;
            var quantity= cart.quantity
            binding.cartQuantity.setText(quantity.toString()!!)
            binding.quantityDecreaseCart.setOnClickListener{
      //          binding.cartQuantity.setText((quantity!!-1).toString())
                listener.onMinusItemClick(cart.itemId?.id !!)
            }
            binding.quantityIncreaseCart.setOnClickListener{
      //          binding.cartQuantity.setText((quantity!!+1).toString())
                listener.onPlusItemClick(cart.itemId?.id !!)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartList[position],listener)
        holder.itemView.setOnClickListener{onCartClickListener.onClick(cartList[position])}

    }

}

class OnCartClickListener(val clickListener: (cart: ItemsItems) -> Unit) {
    fun onClick(cart: ItemsItems) = clickListener(cart)
}

interface ItemClickListener{
    fun onPlusItemClick(itemId:String)
    fun onMinusItemClick(itemId: String)
}



