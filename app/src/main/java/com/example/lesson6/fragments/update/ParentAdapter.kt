package com.example.lesson6.fragments.update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.R
import com.example.lesson6.model.Node
import com.example.lesson6.viewmodel.NodeViewModel
import kotlinx.android.synthetic.main.nodes.view.*

class ParentAdapter(currentNode: Node) : RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {

    private lateinit var mNodeViewModel: NodeViewModel

    private var nodeList = emptyList<Node>()
    private var currentItem = currentNode
    private var isChildren: Boolean = false
    private var isParent: Boolean = false

    fun setIsChildren(isChildren: Boolean) {
        this.isChildren = isChildren
    }

    fun setIsParent(isParent: Boolean) {
        this.isParent = isParent
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nodes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val allList = nodeList[position]

        var text: String
        if (isChildren) {
            text = "id: " + currentItem.id.toString() + " | value: " + currentItem.value + " ---  id: " + allList.id.toString() + " | value: " + allList.value
        } else {
            text = "id: " + allList.id.toString() + " | value: " + allList.value + " ---  id: " + currentItem.id.toString() + " | value: " + currentItem.value
        }
        holder.itemView.textNode.text = text

        holder.itemView.rowLayout.setOnClickListener {
            if (isParent) {
                updateItem(currentItem.id, currentItem.value, allList.id)
            }
            if (isChildren) {
                updateItem(allList.id, allList.value, currentItem.id)
            }
            isChildren = false
            isParent = false
        }
    }

    fun setData(node: List<Node>, mNodeViewModel: NodeViewModel) {
        this.nodeList = node.filter { it!!.id != currentItem.id } as MutableList<Node>
        this.mNodeViewModel = mNodeViewModel
        notifyDataSetChanged()
    }

    private fun updateItem(id: Int, value: Int, idParent: Int) {
        val node = Node(id, value, idParent)
        var update = ParentFragment()
        update.insertDataToDatabase(node, mNodeViewModel)
    }
}
