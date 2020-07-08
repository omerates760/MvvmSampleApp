package com.apps.mvvmwithcrudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.mvvmwithcrudapp.adapter.RecyclerviewAdapter
import com.apps.mvvmwithcrudapp.databinding.ActivityMainBinding
import com.apps.mvvmwithcrudapp.db.Subscriber
import com.apps.mvvmwithcrudapp.db.SubscriberDatabase
import com.apps.mvvmwithcrudapp.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerview()
    }

    private fun displaySubscriberList() {
        subscriberViewModel.subscriber.observe(this, Observer {
            binding.subscriberRecyclerview.adapter = RecyclerviewAdapter(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
        })
    }

    private fun initRecyclerview() {
        binding.subscriberRecyclerview.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()

    }
    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"name is ${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscriberViewModel.updateOpDeleteSubscriber(subscriber)
    }
}