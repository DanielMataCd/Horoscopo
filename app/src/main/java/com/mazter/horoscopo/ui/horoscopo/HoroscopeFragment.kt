package com.mazter.horoscopo.ui.horoscopo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazter.horoscopo.databinding.FragmentHoroscopeBinding
import com.mazter.horoscopo.domain.model.HoroscopoInfo
import com.mazter.horoscopo.domain.model.HoroscopoInfo.*
import com.mazter.horoscopo.domain.model.HoroscopoModel
import com.mazter.horoscopo.ui.horoscopo.adapter.HoroscopoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopoViewModel by viewModels<HoroscopoViewModel> ()
    private lateinit var horoscopoAdapter:HoroscopoAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        initRecyclerView()
        initUIState()
    }

    private fun initRecyclerView(){
        horoscopoAdapter = HoroscopoAdapter(onItemSelected = {
        val type =    when(it){
                Acuario -> HoroscopoModel.Acuario
                Aries -> HoroscopoModel.Aries
                Cancer -> HoroscopoModel.Cancer
                Capricornio -> HoroscopoModel.Capricornio
                Escorpio -> HoroscopoModel.Escorpio
                Geminis -> HoroscopoModel.Geminis
                Leo -> HoroscopoModel.Leo
                Libra -> HoroscopoModel.Libra
                Piscis -> HoroscopoModel.Piscis
                Sagitario -> HoroscopoModel.Sagitario
                Tauro -> HoroscopoModel.Tauro
                Virgo -> HoroscopoModel.Virgo
            }
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopoDetailActivity(type)
            )
        })
        binding!!.rvHoroscopo.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = horoscopoAdapter
        }

    }

    private fun initUIState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopoViewModel.horoscopoListo.collect{
                    //Cambios en Horoscopo
                    horoscopoAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }
}