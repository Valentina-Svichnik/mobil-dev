package com.example.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.example.lesson4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
    }

    private fun setupRecycleView(){
        binding.recycleView.layoutManager = verticalLinearLayoutManager
        val adapter = AdapterExample(PicturesHolder.createCollectionPictures(),::showCard, ::showLike)
        binding.recycleView.adapter = adapter
    }

    private fun showCard(person: Picture): Unit{
        Snackbar.make(binding.root, "Нажата карточка: " + person.name, 2000).show()
    }
    private  fun showLike(person: Picture): Unit{
        Snackbar.make(binding.root, "Нажат лайк:  " + person.name, 2000).show()
    }
}