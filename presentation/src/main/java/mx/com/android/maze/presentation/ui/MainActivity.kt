package mx.com.android.maze.presentation.ui

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.com.android.maze.presentation.R
import mx.com.android.maze.presentation.common.ui.BaseActivity
import mx.com.android.maze.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_main
}