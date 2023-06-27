package mx.com.android.maze.presentation.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class BaseFragment<T : ViewModel, B : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: T

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReady(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun onReady(savedInstanceState: Bundle?) {
        //Do nothing
    }
}