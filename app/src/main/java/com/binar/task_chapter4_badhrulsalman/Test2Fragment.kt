package com.binar.task_chapter4_badhrulsalman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentSecondBinding
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentTest1Binding
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentTest2Binding

class Test2Fragment : Fragment() {

    private var bindingPage: FragmentTest2Binding? = null
    private val binding get() = bindingPage!!
    private val arguments: Test2FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingPage = FragmentTest2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.username.text = arguments.registerData.email
        binding.password.text = arguments.registerData.password
        binding.role.text = arguments.registerData.role
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingPage = null
    }
}