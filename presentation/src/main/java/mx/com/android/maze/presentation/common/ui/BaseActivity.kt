package mx.com.android.maze.presentation.common.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import mx.com.android.maze.presentation.R


abstract class BaseActivity<T : ViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: T

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected val binding: B by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView<B>(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configureOrientation()
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun configureOrientation() {
        val orientation = resources.configuration.orientation
        if (isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR)
        } else if (orientation != Configuration.ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun isTablet() = resources.getBoolean(R.bool.isTablet)
}