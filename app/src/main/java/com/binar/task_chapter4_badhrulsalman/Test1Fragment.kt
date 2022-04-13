package com.binar.task_chapter4_badhrulsalman

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentFirstBinding
import com.binar.task_chapter4_badhrulsalman.databinding.FragmentTest1Binding
import com.binar.task_chapter4_badhrulsalman.network.CarsApi
import com.binar.task_chapter4_badhrulsalman.request.RegisterRequest
import com.binar.task_chapter4_badhrulsalman.response.RegisterResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Test1Fragment : Fragment() {

    private var bindingPage: FragmentTest1Binding? = null
    private val binding get() = bindingPage!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingPage = FragmentTest1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val passingData = RegisterRequest(
                email = binding.username.text.toString(),
                password = binding.password.text.toString(),
                role = binding.role.text.toString()
            )
            CarsApi.instance.registerBody(passingData).enqueue(object : Callback<RegisterResponseItem>{
                override fun onResponse(
                    call: Call<RegisterResponseItem>,
                    response: Response<RegisterResponseItem>
                ) {
                    Log.d("passingData", "Register Body => ${response.body()}")
                }
                override fun onFailure(call: Call<RegisterResponseItem>, t: Throwable) {
                }
            })
            findNavController().navigate(Test1FragmentDirections.actionTest1FragmentToTest2Fragment(passingData))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingPage = null
    }

}