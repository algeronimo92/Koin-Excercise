package com.alangeronimo.koin_exercise.presentation.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alangeronimo.koin_exercise.R
import com.alangeronimo.koin_exercise.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginSharedViewModel>()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.viewModel = viewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    Log.d("Alantest LoginActivity", "uiState $state")
                    when (state) {
                        LoginUIState.Default -> {
                            binding.progress.isVisible = false
                            //no-op
                        }
                        LoginUIState.Loading -> {
                            binding.progress.isVisible = true
                        }
                        LoginUIState.Success -> {
                            Toast.makeText(this@LoginActivity, "Login success", Toast.LENGTH_SHORT).show()
                            viewModel.clearUIState()
                        }
                        LoginUIState.Error.MissingCredentials,
                        LoginUIState.Error.MissingUser,
                        LoginUIState.Error.MissingPassword -> {
                            viewModel.clearUIState()
                        }
                        is LoginUIState.Error.LoginFailed -> {
                            Toast.makeText(this@LoginActivity, state.message, Toast.LENGTH_SHORT).show()
                            viewModel.clearUIState()
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}