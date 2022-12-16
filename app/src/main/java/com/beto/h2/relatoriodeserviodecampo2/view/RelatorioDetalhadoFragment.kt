package com.beto.h2.relatoriodeserviodecampo2.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beto.h2.relatoriodeserviodecampo2.R
import com.beto.h2.relatoriodeserviodecampo2.constants.DataBaseConstants
import com.beto.h2.relatoriodeserviodecampo2.databinding.FragmentRelatorioDetalhadoBinding
import com.beto.h2.relatoriodeserviodecampo2.util.Tempo
import com.beto.h2.relatoriodeserviodecampo2.view.adapter.RelatorioDetalhadoAdapter
import com.beto.h2.relatoriodeserviodecampo2.view.listener.OnRelatorioListener
import com.beto.h2.relatoriodeserviodecampo2.viewmodel.RelatorioDetalhadoViewModel
import java.util.*

class RelatorioDetalhadoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRelatorioDetalhadoBinding? = null
    private lateinit var viewModel: RelatorioDetalhadoViewModel

    private val currentDate = Calendar.getInstance()
    private var year = currentDate.get(Calendar.YEAR)
    private var month = currentDate.get(Calendar.MONTH) + 1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = RelatorioDetalhadoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel =
            ViewModelProvider(this).get(RelatorioDetalhadoViewModel::class.java)
        _binding = FragmentRelatorioDetalhadoBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerRelatorioDetalhado.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerRelatorioDetalhado.adapter = adapter

        binding.tvAnoMes.setText("${Tempo.getMesString(month)}/$year")
        binding.butMesAnterior.setOnClickListener(this)
        binding.butMesPosterior.setOnClickListener(this)

        val listener = object : OnRelatorioListener {
            override fun onClick(id: Long) {
                val intent = Intent(context, RelatorioFormActivity::class.java)
                val bundle = Bundle()
                bundle.putLong(DataBaseConstants.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Long) {
                viewModel.delete(id)
                viewModel.getRelatorioAnoMesDetalhado(year, month)
            }

        }

        adapter.attachListener(listener)

        observer()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRelatorioAnoMesDetalhado(ano = year, mes = month)
    }

    private fun observer() {
        viewModel.relatorios.observe(viewLifecycleOwner) { lista ->
            adapter.updateRelatorios(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.but_mes_anterior -> {
                if (month == 1) {
                    month = 12
                    year--
                } else {
                    month--
                }
                viewModel.getRelatorioAnoMesDetalhado(ano = year, mes = month)
                binding.tvAnoMes.setText("${Tempo.getMesString(month)}/$year")
            }
            R.id.but_mes_posterior -> {
                if (month == 12) {
                    month = 1
                    year++
                } else {
                    month++
                }
                viewModel.getRelatorioAnoMesDetalhado(ano = year, mes = month)
                binding.tvAnoMes.setText("${Tempo.getMesString(month)}/$year")
            }
        }
    }
}