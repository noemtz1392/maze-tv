package mx.com.android.maze.presentation.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import mx.com.android.maze.presentation.R
import mx.com.android.maze.presentation.common.ui.BaseFragment
import mx.com.android.maze.presentation.databinding.FragmentShowBinding
import mx.com.android.maze.presentation.extension.hideKeyboard
import mx.com.android.maze.presentation.extension.launchAndRepeatWithViewLifecycle
import mx.com.android.maze.presentation.ui.MainViewModel
import mx.com.android.maze.presentation.ui.ViewState
import mx.com.android.maze.presentation.ui.adapter.TvShowAdapter
import mx.com.android.maze.presentation.utils.Commons


class ShowFragment : BaseFragment<MainViewModel, FragmentShowBinding>() {

    override val viewModel: MainViewModel by activityViewModels()

    override val layoutId: Int = R.layout.fragment_show

    private val adapter by lazy {
        TvShowAdapter {
            findNavController().navigate(ShowFragmentDirections.toShowDetail(it.id))
        }
    }


    override fun onReady(savedInstanceState: Bundle?) {
        bindViews()
        bindObservers()
        if (savedInstanceState == null) {
            viewModel.getShowsTV()
        }
    }

    private fun bindViews() {
        binding.viewModel = viewModel
        binding.recycler.adapter = adapter
        binding.recycler.setHasFixedSize(true)
        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        binding.title.text = Commons.getCurrentDateFormat()
        binding.toolbar.inflateMenu(R.menu.main_menu)

        addSearchListener(binding.toolbar.menu.getItem(0))
    }

    private fun addSearchListener(menuItem: MenuItem) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        val searchView = menuItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))
        searchView.isIconified = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findTvShowByQuery(query)
                binding.root.hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        searchView.setOnCloseListener {
            adapter.submitList(emptyList())
            viewModel.getShowsTV()
            true
        }
    }

    private fun bindObservers() {
        launchAndRepeatWithViewLifecycle {
            viewModel.viewState.collect { viewState ->
                when (viewState) {
                    is ViewState.Error -> println()
                    is ViewState.Failure -> println()
                    is ViewState.Loaded -> adapter.submitList(viewState.data)
                    ViewState.Loading -> println()
                }
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.findViewState.collect { viewState ->
                when (viewState) {
                    is ViewState.Error -> println()
                    is ViewState.Failure -> println()
                    is ViewState.Loaded -> {
                        adapter.submitList(viewState.data)
                    }

                    ViewState.Loading -> println()
                }
            }
        }
    }
}