package mx.com.android.maze.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import mx.com.android.maze.presentation.R
import mx.com.android.maze.presentation.common.ui.BaseFragment
import mx.com.android.maze.presentation.databinding.FragmentShowDetailBinding
import mx.com.android.maze.presentation.extension.launchAndRepeatWithViewLifecycle
import mx.com.android.maze.presentation.ui.CastViewState
import mx.com.android.maze.presentation.ui.MainViewModel
import mx.com.android.maze.presentation.ui.adapter.CastAdapter

class ShowDetailFragment : BaseFragment<MainViewModel, FragmentShowDetailBinding>() {

    override val viewModel: MainViewModel by activityViewModels()

    override val layoutId: Int = R.layout.fragment_show_detail

    private val args: ShowDetailFragmentArgs by navArgs()

    private val adapter by lazy { CastAdapter() }

    override fun onReady(savedInstanceState: Bundle?) {
        super.onReady(savedInstanceState)
        bindViews()
        bindObservers()
        viewModel.getTvShowById(args.showId)
        viewModel.getCastFromTvShow(args.showId)
    }

    private fun bindViews() {
        binding.recycler?.adapter = adapter
        binding.recycler?.setHasFixedSize(true)
    }

    private fun bindObservers() {
        viewModel.tvShowState.observe(viewLifecycleOwner) { binding.show = it }
        launchAndRepeatWithViewLifecycle {
            viewModel.castViewState.collect { viewState ->
                when (viewState) {
                    is CastViewState.Failure -> println()
                    is CastViewState.Loaded -> adapter.submitList(viewState.data)
                    CastViewState.Loading -> println()
                }
            }
        }
    }
}