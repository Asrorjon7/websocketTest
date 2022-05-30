package com.example.cryptoboy.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoboy.viewmodel.MainViewModel
import com.template.websocket.databinding.FragmentDataBinding
import com.template.websocket.model.Data
import com.template.websocket.model.Senddata
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentDataBinding
    private val adapter = CryptoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.layoutManager = LinearLayoutManager(view.context)
        binding.list.adapter = adapter
  // viewModel.setData(Senddata(Data("M4U6F3CTDKVnNqOGqNlrtbQZzFE2oyRZ"),key="auth", status = 200))
       viewModel.connectSocket(Senddata(Data("M4U6F3CTDKVnNqOGqNlrtbQZzFE2oyRZ"),key="auth", status = 200))
      viewModel.prices.observe(viewLifecycleOwner) {
          Log.e("data", it.toString())
      }
    }

}