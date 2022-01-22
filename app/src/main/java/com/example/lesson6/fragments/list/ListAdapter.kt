package com.example.lesson6.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.R
import com.example.lesson6.db.Node
import kotlinx.android.synthetic.main.nodes.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var nodeList = emptyList<Node>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nodes, parent, false))
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = nodeList[position]
        holder.itemView.textNode.text = "id: " + currentItem.id.toString() + " | value: " + currentItem.value
    }

    fun setData(node: List<Node>) {
        this.nodeList = node
        notifyDataSetChanged()
    }
}
