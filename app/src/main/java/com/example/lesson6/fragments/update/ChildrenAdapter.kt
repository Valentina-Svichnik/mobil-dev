package com.example.lesson6.fragments.update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.R
import com.example.lesson6.model.Node
import kotlinx.android.synthetic.main.nodes.view.*

class ChildrenAdapter : RecyclerView.Adapter<ChildrenAdapter.MyViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenAdapter.MyViewHolder {
        return ChildrenAdapter.MyViewHolder(
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
            holder.itemView.textNode.text =
                "id: " + allList.id.toString() + " | value: " + allList.value + " ---- id: " + currentItem.id.toString() + " | value: " + currentItem.value
        } else {
            holder.itemView.textNode.text = null
        }

        //        при нажатии на нод
        holder.itemView.rowLayout.setOnClickListener {

//            updateItem()
            isChildren = false
            isParent = false
        }
    }

    fun setData(node: List<Node>) {
        this.nodeList = node
        notifyDataSetChanged()
    }
}


