package com.example.lesson6.fragments.update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
// import com.example.lesson6.DatabaseInterface
import com.example.lesson6.R
import com.example.lesson6.fragments.add.UpdateFragment
import com.example.lesson6.model.Node
import com.example.lesson6.viewmodel.NodeViewModel
import kotlinx.android.synthetic.main.nodes.view.*

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {

    private lateinit var mNodeViewModel: NodeViewModel

    private var currentNodeId: Int = -1
    private var isChildren: Boolean = false
    private var isParent: Boolean = false

    fun setCurrentNode(id: Int) {
        currentNodeId = id
    }

    fun setIsChildren(isChildren: Boolean) {
        this.isChildren = isChildren
    }

    fun setIsParent(isParent: Boolean) {
        this.isParent = isParent
    }

    private var nodeList = emptyList<Node>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentAdapter.MyViewHolder {
        return ParentAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nodes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // -----------------------старая версия--------------------
        val currentItem = nodeList[currentNodeId - 1]
        val allList = nodeList[position]
        // вывод нодов (как в list)

        if (currentItem != allList) {
            var text: String
            if (isChildren) {
                text = "id: " + currentItem.id.toString() + " | value: " + currentItem.value + " ----  id: " + allList.id.toString() + " | value: " + allList.value
            } else {
                text = "id: " + allList.id.toString() + " | value: " + allList.value + " ----  id: " + currentItem.id.toString() + " | value: " + currentItem.value
            }
            holder.itemView.textNode.text = text
        } else {
            holder.itemView.textNode.text = null
        }

        //        при нажатии на нод
        holder.itemView.rowLayout.setOnClickListener {
            println("***************************")
            println("CurrentId")
            println(currentItem.id)
            println("CurrentItem")
            println(currentItem.value)
            println("AllList")
            println(allList.id)
            if (isParent) {
                updateItem(currentItem.id, currentItem.value, allList.id)
            }
            if (isChildren) {
                updateItem(allList.id, allList.value, currentItem.id)
            }
            isChildren = false
            isParent = false
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(node: List<Node>) {
        this.nodeList = node
        notifyDataSetChanged()
    }

    private fun updateItem(id: Int, value: Int, idParent: Int) {
        println("########################################")
        val node = Node(id, value, idParent)
        println(node)
        var update = UpdateFragment()
        update.insertDataToDatabase(node)
    }

}
