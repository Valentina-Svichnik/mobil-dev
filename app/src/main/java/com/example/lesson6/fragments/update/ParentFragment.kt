
package com.example.lesson6.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson6.R
import com.example.lesson6.model.Node
import com.example.lesson6.viewmodel.NodeViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*

class ParentFragment : Fragment() {

    private val args by navArgs<ParentFragmentArgs>()

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Recyclerview
        val adapter = ParentAdapter()
        println(args.currentNodeParent.id)
        adapter.setCurrentNode(args.currentNodeParent.id)
        val recyclerView = view.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // NodeViewModel
        mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)
        mNodeViewModel.readAllData.observe(
            getViewLifecycleOwner(),
            Observer { node ->
                adapter.setData(node)
            }
        )

        view.childButton.setOnClickListener {
            adapter.setIsParent(false)
            adapter.setIsChildren(true)
            mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)
            mNodeViewModel.readAllData.observe(
                getViewLifecycleOwner(),
                Observer { node ->
                    adapter.setData(node)
                }
            )
//            findNavController().navigate(R.id.action_updateFragment_to_childrenFragment)
        }

        view.parentButton.setOnClickListener {
            adapter.setIsChildren(false)
            adapter.setIsParent(true)
            mNodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)
            mNodeViewModel.readAllData.observe(
                getViewLifecycleOwner(),
                Observer { node ->
                    adapter.setData(node)
                }
            )
//            findNavController().navigate(R.id.action_updateFragment_to_childrenFragment)
        }

//        view.parentButton.setOnClickListener {
//            adapter.setIsParent(true)
//        }
        return view
    }

    private fun inputCheck(value: String): Boolean {
        return !(TextUtils.isEmpty(value))
    }

    fun getArg(): Int {
        return args.currentNodeParent.id
    }
}
