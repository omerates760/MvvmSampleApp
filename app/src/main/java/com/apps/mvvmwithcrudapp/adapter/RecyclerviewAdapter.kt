package com.apps.mvvmwithcrudapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.mvvmwithcrudapp.R
import com.apps.mvvmwithcrudapp.databinding.ListItemBinding
import com.apps.mvvmwithcrudapp.db.Subscriber
import com.apps.mvvmwithcrudapp.generated.callback.OnClickListener

class RecyclerviewAdapter(private val subscriber: List<Subscriber>,private val clickListener: (Subscriber)->Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =subscriber.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(subscriber[position],clickListener)
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber,clickListener: (Subscriber)->Unit) {
        binding.txtName.text = subscriber.name
        binding.txtSubscriber.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}