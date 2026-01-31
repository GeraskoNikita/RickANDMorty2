package com.example.rickmorty.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickmorty.databinding.ItemCharacterBinding
import com.example.rickmorty.domain.model.Character

class CharactersAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<Character, CharactersAdapter.CharactersHolder>(Diff()) {

    inner class CharactersHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: Character) {
            binding.name.text = item.name
            binding.subtitle.text = "${item.status} • ${item.species}"
            binding.avatar.load(item.image)
            binding.location.text = item.location.name

            binding.root.setOnClickListener { onClick(item.id) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        holder.onBind(getItem(position)!!)
    }

    class Diff : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
    }
}
