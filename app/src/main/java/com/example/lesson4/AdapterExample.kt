package com.example.lesson4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson4.databinding.ItemTestBinding

class AdapterExample (
    private val list: List<Picture>,
    private val clickCard: (Picture) -> Unit,
    private val clickLike: (Picture) -> Unit
): RecyclerView.Adapter<AdapterExample.ExampleHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExampleHolder {
        val binding = ItemTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleHolder(binding, clickCard, clickLike)
    }

    override fun onBindViewHolder(holder: ExampleHolder, position: Int) {
        val picture = list[position]
        holder.bind(picture)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ExampleHolder internal constructor(
        private val binding: ItemTestBinding,
        private val clickCard: (Picture) -> Unit,
        private val clickLike: (Picture) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(picture: Picture) = binding.run {
            name.text = picture.name
            date.text = picture.date
            avatar.setImageResource(picture.imageDrawableRes)
            description.text = picture.info
            gender.text = picture.gender

            binding.root.setOnClickListener{
                clickCard.invoke(picture)
            }

            binding.like.setOnClickListener{
                clickLike.invoke(picture)
            }
        }
    }
}