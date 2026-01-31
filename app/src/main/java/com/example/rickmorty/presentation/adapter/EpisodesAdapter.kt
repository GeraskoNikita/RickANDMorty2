package com.example.rickmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.presentation.model.EpisodeUi

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.VH>() {

    private val items = mutableListOf<EpisodeUi>()
    private var expandedPos: Int = RecyclerView.NO_POSITION

    fun submit(list: List<EpisodeUi>) {
        items.clear()
        items.addAll(list)
        expandedPos = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_charachter_detail, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val isExpanded = position == expandedPos
        holder.bind(item, isExpanded)

        holder.itemView.setOnClickListener {
            val old = expandedPos
            expandedPos = if (isExpanded) RecyclerView.NO_POSITION else position
            if (old != RecyclerView.NO_POSITION) notifyItemChanged(old)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = items.size

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvSeason: TextView = view.findViewById(R.id.tvSeason)
        private val tvEpisode: TextView = view.findViewById(R.id.tvEpisode)
        private val expanded: View = view.findViewById(R.id.expanded)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvAirDate: TextView = view.findViewById(R.id.tvAirDate)
        private val ivChevron: ImageView = view.findViewById(R.id.ivChevron)

        fun bind(item: EpisodeUi, isExpanded: Boolean) {
            tvSeason.text = "Season ${item.season}"
            tvEpisode.text = "Episode ${item.episodeNumber}"
            tvName.text = item.name
            tvAirDate.text = item.airDate

            expanded.visibility = if (isExpanded) View.VISIBLE else View.GONE
            ivChevron.rotation = if (isExpanded) 180f else 0f
        }
    }
}
