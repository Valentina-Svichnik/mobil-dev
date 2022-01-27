package com.example.lesson6.fragments.add

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesson6.R
import com.example.lesson6.model.Node
import com.example.lesson6.viewmodel.NodeViewModel
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
        val value = editValue.text
//        var nodes = mutableListOf<Node>()

        if (inputCheck(value)) {
            val node = Node(0, Integer.parseInt(value.toString()), -1)
            mNodeViewModel.addNode(node)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(value: Editable): Boolean {
        return !(value.isEmpty())
    }
}

//class UpdateFragment : Fragment() {
//
//    fun insertDataToDatabase(node: Node) {
//        var mNodeViewModel: NodeViewModel
//        mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]
//        mNodeViewModel.updateNode(node)
//        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
//    }
//}
