package sample.onursaygili.commentator.reviews.view.adapter

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import sample.onursaygili.commentator.R
import sample.onursaygili.commentator.databinding.ReviewListItemBinding
import sample.onursaygili.commentator.reviews.model.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    val items = ArrayList<Review>()

    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(review: Review)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(
                LayoutInflater.from(parent.context),
                R.layout.review_list_item,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem) {
            itemClickListener.onItemClick(it)
        }

    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ReviewListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Review, listener: (Review) -> Unit) = with(binding) {
            binding.setVariable(com.android.databinding.library.baseAdapters.BR.review, currentItem)
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener(currentItem) }
        }
    }
}