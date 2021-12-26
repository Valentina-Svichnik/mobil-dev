package com.example.activitynavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.activitynavigation.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.thirdBack.setOnClickListener{
            MAIN.navController.navigate(R.id.action_thirdFragment_to_secondFragment)
        }

        binding.plus.setOnClickListener{
            dataModel.math.value = binding.plus.text.toString()
            MAIN.navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
        }

        binding.minus.setOnClickListener{
            dataModel.math.value = binding.minus.text.toString()
            MAIN.navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
        }

        binding.times.setOnClickListener{
            dataModel.math.value = binding.times.text.toString()
            MAIN.navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
        }

        binding.div.setOnClickListener{
            dataModel.math.value = binding.div.text.toString()
            MAIN.navController.navigate(R.id.action_thirdFragment_to_fourthFragment)
        }
    }
}