package mx.com.android.maze.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.com.android.maze.domain.model.Person
import mx.com.android.maze.presentation.R
import mx.com.android.maze.presentation.databinding.ItemListPersonBinding
import mx.com.android.maze.presentation.extension.inflate

class CastAdapter : ListAdapter<Person, CastAdapter.CastViewHolder>(CastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(parent.inflate(R.layout.item_list_person))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListPersonBinding.bind(view)

        fun bind(item: Person) {
            with(binding) {
                root.tag = item
                person = item
                executePendingBindings()
            }
        }
    }

    class CastDiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.photoUrl == newItem.photoUrl
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}