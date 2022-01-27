package com.example.lesson6.fragments.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.R
import com.example.lesson6.model.Node
import kotlinx.android.synthetic.main.nodes.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var nodeList = emptyList<Node>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nodes, parent, false))
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = nodeList[position]
        holder.itemView.textNode.text = "id: " + currentItem.id.toString() + " | value: " + currentItem.value

        // и родитель и ребёнок
        if (funIsChildren(currentItem) && (funIsParent(currentItem, nodeList as MutableList<Node>))) {
            holder.itemView.textNode.setBackgroundColor(Color.parseColor("#F64236"))
        } else {
            // только родитель
            if (funIsParent(currentItem, nodeList as MutableList<Node>)) {
                holder.itemView.textNode.setBackgroundColor(Color.parseColor("#FFEB3A"))
            }
            // только ребёнок
            if (funIsChildren(currentItem)) {
                holder.itemView.textNode.setBackgroundColor(Color.parseColor("#2196F5"))
            }
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(node: List<Node>) {
        this.nodeList = node
        notifyDataSetChanged()
    }

    fun funIsChildren(node: Node): Boolean {
        return (node.parent > -1)
    }

    fun funIsParent(node: Node, list: MutableList<Node>): Boolean {
        var k = 0
        for (i in list) {
            if (node.id == i.parent) {
                k = 1
            }
        }
        return (k == 1)
    }
}
