package com.alangeronimo.koin_exercise.presentation.login.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alangeronimo.koin_exercise.databinding.FragmentFirstBinding
import com.alangeronimo.koin_exercise.presentation.login.LoginSharedViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel by activityViewModel<LoginSharedViewModel>()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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