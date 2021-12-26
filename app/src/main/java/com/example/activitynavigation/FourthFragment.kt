package com.example.activitynavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.activitynavigation.databinding.FragmenbFoutrhBinding

class FourthFragment : Fragment() {
    private lateinit var binding: FragmenbFoutrhBinding
    private val dataModel : DataModel by activityViewModels()
    private var number1: String = ""
    private var number2: String = ""
    private var math: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmenbFoutrhBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fourthBack.setOnClickListener{
            MAIN.navController.navigate(R.id.action_fourthFragment_to_thirdFragment)
        }

        dataModel.number1.observe(viewLifecycleOwner) {
            number1 = it
        }

        dataModel.number2.observe(viewLifecycleOwner) {
            number2 = it
        }

        dataModel.math.observe(viewLifecycleOwner) {
            math = it
            calc()
        }
    }
    private fun calc(){
            val result = when (math) {
                "+" -> number1.toInt()!!.plus(number2.toInt()!!)
                "-" -> number1.toInt()!!.minus(number2.toInt()!!)
                "*" -> number1.toInt()!!.times(number2.toInt()!!)
                "/" -> number1.toInt()!!.div(number2.toInt()!!)
                else -> return
            }
            binding.result.text = "$number1 $math $number2 = $result"
    }
}