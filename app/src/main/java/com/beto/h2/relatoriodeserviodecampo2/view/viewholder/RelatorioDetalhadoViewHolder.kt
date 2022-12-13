package com.beto.h2.relatoriodeserviodecampo2.view.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.beto.h2.relatoriodeserviodecampo2.databinding.RowRelatorioDetalhadoBinding
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.util.Tempo
import com.beto.h2.relatoriodeserviodecampo2.view.listener.OnRelatorioListener

class RelatorioDetalhadoViewHolder(
    private val bind: RowRelatorioDetalhadoBinding,
    private val listener: OnRelatorioListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(relatorio: RelatorioModel) {

        bind.textData.setText(
            Tempo.getDiaDaSemana(relatorio.data) + " " + Tempo.dataSqlParaPadaoPtBr(
                relatorio.data
            )
        )
        bind.textHoras.setText(relatorio.horas.toString())

        bind.butEditar.setOnClickListener {
            listener.onClick(relatorio.id)
        }
        bind.butRemove.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de relatório!")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, witch ->
                    listener.onDelete(relatorio.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }

    }
}