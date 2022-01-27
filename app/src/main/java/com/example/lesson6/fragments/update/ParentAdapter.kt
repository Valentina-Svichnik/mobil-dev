package com.example.lesson6.fragments.update

import android.graphics.Color
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

        // окрашиваем зависимости в зеленый
        if (isParent) {
            if (currentItem.parent == allList.id) {
                holder.itemView.textNode.setBackgroundColor(Color.GREEN)
            }
        }
        if (isChildren) {
            if (currentItem.id == allList.parent) {
                holder.itemView.textNode.setBackgroundColor(Color.GREEN)
            }

            if (currentItem.parent == allList.id) {
                holder.itemView.textNode.setBackgroundColor(Color.WHITE)
            }
        }

        holder.itemView.rowLayout.setOnClickListener {
            if (isParent) {
                // убираем связь
                if (currentItem.parent > -1) {
                    updateItem(currentItem.id, currentItem.value, -1)
                    holder.itemView.textNode.setBackgroundColor(Color.WHITE)
                } else {
                    // добавляем связь
                    if (allList.parent != currentItem.id) {
                        updateItem(currentItem.id, currentItem.value, allList.id)
                        holder.itemView.textNode.setBackgroundColor(Color.GREEN)
                    } else {
//                        Toast.makeText(, "Ошибка! Невозможно добавить связь.", Toast.LENGTH_LONG).show()
                    }
                }
            }
            if (isChildren) {
                // убираем связь
                if (allList.parent > -1) {
                    updateItem(allList.id, allList.value, -1)
                    holder.itemView.textNode.setBackgroundColor(Color.WHITE)
                } else {
                    // добавляем связь
                    if (currentItem.parent != allList.id) {
                        updateItem(allList.id, allList.value, currentItem.id)
                        holder.itemView.textNode.setBackgroundColor(Color.GREEN)
                    } else {
//                        Toast.makeText(, "Ошибка! Невозможно добавить связь.", Toast.LENGTH_LONG).show()
                    }
                }
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
