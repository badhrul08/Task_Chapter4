package com.binar.task_chapter4_badhrulsalman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentFirstBinding
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var bindingPage: FragmentSecondBinding? = null
    private val binding get() = bindingPage!!
    private val arguments: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingPage = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments.cars.id
        val name = arguments.cars.name
        val price = arguments.cars.price

        binding.idTv.text = id.toString()
        binding.titleTv.text = name
        binding.priceTv.text = price.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingPage = null
    }
}