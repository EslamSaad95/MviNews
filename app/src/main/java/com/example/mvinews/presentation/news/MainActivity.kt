package com.example.mvinews.presentation.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvinews.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private val viewModel by viewModels<NewsViewModel>()
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private lateinit var newsRvAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        render()
        lifecycleScope.launch { viewModel.channelIntent.send(NewsIntents.initialize) }
    }


    private fun initNewsRv() {
        if (::newsRvAdapter.isInitialized)
            binding.rvNews.apply {
                layoutManager = linearLayoutManager
                adapter = newsRvAdapter
            }
    }

    //send
    //render
    private fun render(){
        lifecycleScope.launch {
            viewModel.state.collect {
                when(it)
                {
                   is NewsViewStates.loading->binding.progress.visibility=View.VISIBLE
                   is NewsViewStates.success->{
                        binding.progress.visibility=View.GONE
                        if (it.newsList.isNotEmpty()) {
                            newsRvAdapter = NewsAdapter(it.newsList)
                            initNewsRv()
                        }
                    }
                    is NewsViewStates.error->{
                        binding.progress.visibility=View.GONE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}