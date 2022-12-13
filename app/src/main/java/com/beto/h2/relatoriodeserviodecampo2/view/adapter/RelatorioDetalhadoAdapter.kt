package com.beto.h2.relatoriodeserviodecampo2.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beto.h2.relatoriodeserviodecampo2.databinding.RowRelatorioDetalhadoBinding
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.view.listener.OnRelatorioListener
import com.beto.h2.relatoriodeserviodecampo2.view.viewholder.RelatorioDetalhadoViewHolder

class RelatorioDetalhadoAdapter : RecyclerView.Adapter<RelatorioDetalhadoViewHolder>() {
    private var relatorioDetalhado: List<RelatorioModel> = listOf()
    private lateinit var listener: OnRelatorioListener
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RelatorioDetalhadoViewHolder {
        val item =
            RowRelatorioDetalhadoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RelatorioDetalhadoViewHolder(item,listener)
    }

    override fun onBindViewHolder(holder: RelatorioDetalhadoViewHolder, position: Int) {
        holder.bind(relatorioDetalhado[position])
    }

    override fun getItemCount(): Int {
        return relatorioDetalhado.count()
    }

    fun updateRelatorios(list: List<RelatorioModel>) {
        relatorioDetalhado = list
        notifyDataSetChanged()
    }

    fun attachListener(relatorioListener: OnRelatorioListener){
        listener = relatorioListener
    }

}