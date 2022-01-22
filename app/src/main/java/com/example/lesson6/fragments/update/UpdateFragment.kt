
package com.example.lesson6.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lesson6.R
import com.example.lesson6.model.Node
import com.example.lesson6.viewmodel.NodeViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)

        view.updateValue.setText(args.currentNode.value)

        view.update_btn.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val value = updateValue.text.toString()

        if (inputCheck(value)) {
            // Create Node object
            val updatedNode = Node(args.currentNode.id, value)
            // Update Current Node
            mNodeViewModel.updateNode(updatedNode)
            Toast.makeText(requireContext(), "Successfully!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fell out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(value: String): Boolean {
        return !(TextUtils.isEmpty(value))
    }
}
