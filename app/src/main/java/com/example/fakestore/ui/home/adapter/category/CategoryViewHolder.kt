package com.example.fakestore.ui.home.adapter.category

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.databinding.ItemCategoryBinding
import com.example.fakestore.domain.model.CategoryType

class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoryType, onCategoryClick: (CategoryType) -> Unit) {
        binding.apply {
            tvCategoryTitle.text = item.getCapilatizedTitle()
            ivCategoryIcon.setImageResource(item.image)

            cardView.setOnClickListener { onCategoryClick(item) }
        }
    }

    companion object {
        fun from(context: Context): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val binding = ItemCategoryBinding.inflate(layoutInflater)
            return CategoryViewHolder(binding)
        }
    }
}