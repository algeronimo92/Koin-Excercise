package com.alangeronimo.koin_exercise.presentation.login.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.alangeronimo.koin_exercise.R
import com.alangeronimo.koin_exercise.databinding.FragmentFirstBinding
import com.alangeronimo.koin_exercise.presentation.login.LoginSharedViewModel
import com.alangeronimo.koin_exercise.presentation.login.LoginUIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel by activityViewModel<LoginSharedViewModel>()

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        LoginUIState.Default,
                        LoginUIState.Error.LoginFailed -> {
                            // no-op
                        }
                        LoginUIState.Error.MissingCredentials -> {
                            (state as LoginUIState.Error.MissingCredentials).let { myState ->
                                binding.eTextUser.error = myState.message
                                binding.eTextPassword.error = myState.message
                            }
                        }
                        LoginUIState.Error.MissingPassword -> {
                            (state as LoginUIState.Error.MissingPassword).let { myState ->
                                binding.eTextPassword.error = myState.message
                            }
                        }
                        LoginUIState.Error.MissingUser -> {
                            (state as LoginUIState.Error.MissingUser).let { myState ->
                                binding.eTextUser.error = myState.message
                            }
                        }
                        LoginUIState.Loading -> {
                            binding.eTextUser.error = null
                            binding.eTextPassword.error = null
                        }
                        LoginUIState.Success -> {
                            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                        }
                    }
                }
            }
        }

        //
        //binding.buttonFirst.setOnClickListener {
        //    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}