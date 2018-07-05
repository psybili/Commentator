package sample.onursaygili.commentator.reviews.view.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import sample.onursaygili.commentator.R
import sample.onursaygili.commentator.common.di.Injectable
import sample.onursaygili.commentator.common.util.ext.observe
import sample.onursaygili.commentator.databinding.ReviewListFragmentBinding
import sample.onursaygili.commentator.reviews.model.Review
import sample.onursaygili.commentator.reviews.model.remote.ReviewQueryParams
import sample.onursaygili.commentator.reviews.view.adapter.ReviewAdapter
import javax.inject.Inject

class ReviewListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewListViewModel

    private lateinit var binding: ReviewListFragmentBinding

    private val adapter = ReviewAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<ReviewListFragmentBinding>(
            inflater,
            R.layout.review_list_fragment,
            container,
            false
    ).also {
        binding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
                activity!!,
                viewModelFactory
        ).get(ReviewListViewModel::class.java)

        adapter.itemClickListener = object : ReviewAdapter.ItemClickListener {
            override fun onItemClick(review: Review) {
                this@ReviewListFragment.onItemClick(review)
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.reviews.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        viewModel.params.value = ReviewQueryParams(0, 0, 0, "date_of_review", "DESC")
    }

    private fun onItemClick(review: Review) {
        Toast.makeText(context, review.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ReviewListFragment()
    }
}