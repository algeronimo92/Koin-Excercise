package com.alangeronimo.koin_exercise.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alangeronimo.koin_exercise.R
import com.alangeronimo.koin_exercise.databinding.ActivityMainBinding
import com.alangeronimo.koin_exercise.presentation.viewmodel.LoginSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.annotation.KoinInternalApi

@OptIn(KoinInternalApi::class)
class MainActivity : AppCompatActivity() {

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

        viewModel.loginResult.observe(this) {
            Log.d("Alantest MainActivity", "Login result: $it")
        }
    }



    override fun onResume() {
        super.onResume()

        Log.d("Alantest MainActivity", viewModel.session())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}