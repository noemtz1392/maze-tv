package mx.com.android.maze.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.com.android.maze.domain.model.Show
import mx.com.android.maze.presentation.R
import mx.com.android.maze.presentation.databinding.ItemListShowBinding
import mx.com.android.maze.presentation.extension.inflate

class TvShowAdapter(
    private val actionClick: (Show) -> Unit
) : ListAdapter<Show, TvShowAdapter.TvShowViewHolder>(TvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(parent.inflate(R.layout.item_list_show))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListShowBinding.bind(view)

        fun bind(item: Show) {
            with(binding) {
                show = item
                root.tag = item
                root.setOnClickListener {
                    actionClick(item)
                }
                executePendingBindings()
            }
        }
    }

    class TvShowDiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }
}

