package com.beto.h2.relatoriodeserviodecampo2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.beto.h2.relatoriodeserviodecampo2.databinding.FragmentRelatorioMensalBinding
import com.beto.h2.relatoriodeserviodecampo2.viewmodel.RelatorioMensalViewModel

class RelatorioMensalFragment : Fragment() {

    private var _binding: FragmentRelatorioMensalBinding? = null
    private lateinit var viewModel:RelatorioMensalViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(RelatorioMensalViewModel::class.java)

        _binding = FragmentRelatorioMensalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //viewModel.getRelatorioAnoMesDetalhado()

        observe()
        return root
    }

    private fun observe() {
        viewModel.relatorios.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}