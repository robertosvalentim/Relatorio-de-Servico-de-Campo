package com.beto.h2.relatoriodeserviodecampo2.util

import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioGeralModel
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import java.sql.Time

class RelatorioGeralUtil {

    companion object{
        fun obterSomaGeral(relatorios : List<RelatorioModel>):RelatorioGeralModel{
            var horas: Time
            var publicacoes = 0
            var videos: Int = 0
            var revisitas: Int = 0
            var estudos: Int = 0

            for(rel in relatorios){
                publicacoes+= rel.publicacoes
                videos+=rel.videos
                revisitas+=rel.revisitas
                estudos+=rel.estudos
            }
            return RelatorioGeralModel(
                horas= Tempo.somarHoras(relatorios),
                publicacoes=publicacoes,
                videos= videos,
                revisitas = revisitas,
                estudos=estudos
            )
        }
    }
}