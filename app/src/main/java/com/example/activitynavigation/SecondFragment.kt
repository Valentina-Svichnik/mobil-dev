package com.example.activitynavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.activitynavigation.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secBack.setOnClickListener{
            MAIN.navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.secNext.setOnClickListener{
            dataModel.number2.value = binding.secNum.text.toString()
            MAIN.navController.navigate(R.id.action_secondFragment_to_thirdFragment)
        }
    }
}