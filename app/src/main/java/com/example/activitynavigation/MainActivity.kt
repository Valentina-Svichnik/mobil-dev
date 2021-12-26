package com.example.activitynavigation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.activitynavigation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.fragment_container)

        MAIN = this



        binding.firstFrag.setOnClickListener {
            MAIN.navController.navigate(R.id.firstFragment)
//            binding.secondFrag.setBackgroundColor(Color.GRAY)
//            binding.thirdFrag.setBackgroundColor(Color.GRAY)
//            binding.fourthFrag.setBackgroundColor(Color.GRAY)
        }

        binding.secondFrag.setOnClickListener {
            MAIN.navController.navigate(R.id.secondFragment)
//            binding.secondFrag.setBackgroundColor(Color.BLUE)
//            binding.thirdFrag.setBackgroundColor(Color.GRAY)
//            binding.fourthFrag.setBackgroundColor(Color.GRAY)
        }

        binding.thirdFrag.setOnClickListener {
            MAIN.navController.navigate(R.id.thirdFragment)
//            binding.secondFrag.setBackgroundColor(Color.BLUE)
//            binding.thirdFrag.setBackgroundColor(Color.BLUE)
//            binding.fourthFrag.setBackgroundColor(Color.GRAY)
        }

        binding.fourthFrag.setOnClickListener {
            MAIN.navController.navigate(R.id.fourthFragment)
//            binding.secondFrag.setBackgroundColor(Color.BLUE)
//            binding.thirdFrag.setBackgroundColor(Color.BLUE)
//            binding.fourthFrag.setBackgroundColor(Color.BLUE)
        }
    }
    }

