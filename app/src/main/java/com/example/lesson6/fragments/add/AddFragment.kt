package com.example.lesson6.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesson6.R
import com.example.lesson6.db.Node
import com.example.lesson6.db.NodeViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val value = editValue.text.toString()

        if (inputCheck(value)) {
            val node = Node(0, value, value)
            mNodeViewModel.addNode(node)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(value: String): Boolean {
        return !(TextUtils.isEmpty(value))
    }
}
