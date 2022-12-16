package com.beto.h2.relatoriodeserviodecampo2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.beto.h2.relatoriodeserviodecampo2.R
import com.beto.h2.relatoriodeserviodecampo2.databinding.FragmentRelatorioMensalBinding
import com.beto.h2.relatoriodeserviodecampo2.util.RelatorioGeralUtil
import com.beto.h2.relatoriodeserviodecampo2.util.Tempo
import com.beto.h2.relatoriodeserviodecampo2.viewmodel.RelatorioMensalViewModel
import java.util.*

class RelatorioMensalFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRelatorioMensalBinding? = null
    private lateinit var viewModel: RelatorioMensalViewModel

    private val cal = GregorianCalendar()
    private var ano = cal.get(Calendar.YEAR)
    private var mes = cal.get(Calendar.MONTH) + 1


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

        binding.butMesAnteriorRelGeral.setOnClickListener(this)
        binding.butMesPosteriorrelGeral.setOnClickListener(this)

        binding.tvAnoMes.setText("${Tempo.getMesString(mes)}/$ano")

        observe()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRelatorioAnoMesDetalhado(ano = ano, mes = mes)
    }

    private fun observe() {
        viewModel.relatorios.observe(viewLifecycleOwner) { lista ->
            val rel = RelatorioGeralUtil.obterSomaGeral(lista)

            binding.tvHorasEdit.setText(rel.horas.toString())
            binding.tvPublicacoesEdit.setText(rel.publicacoes.toString())
            binding.tvVideosEdit.setText(rel.videos.toString())
            binding.tvRevisitasEdit.setText(rel.revisitas.toString())
            binding.tvEstudosEdit.setText(rel.estudos.toString())
        }

        viewModel.listaVazia.observe(viewLifecycleOwner){
            if(it){
                binding.tvHorasEdit.setText(null)
                binding.tvPublicacoesEdit.setText(null)
                binding.tvVideosEdit.setText(null)
                binding.tvRevisitasEdit.setText(null)
                binding.tvEstudosEdit.setText(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.but_mes_anterior_rel_geral -> {
                if (mes == 1) {
                    mes = 12
                    ano--
                } else {
                    mes--
                }
                viewModel.getRelatorioAnoMesDetalhado(ano = ano, mes = mes)
                binding.tvAnoMes.setText("${Tempo.getMesString(mes)}/$ano")
            }
            R.id.but_mes_posteriorrel_geral -> {
                if (mes == 12) {
                    mes = 1
                    ano++
                } else {
                    mes++
                }
                viewModel.getRelatorioAnoMesDetalhado(ano = ano, mes = mes)
                binding.tvAnoMes.setText("${Tempo.getMesString(mes)}/$ano")
            }
        }
    }
}