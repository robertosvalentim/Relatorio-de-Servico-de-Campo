package com.beto.h2.relatoriodeserviodecampo2.util

import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Tempo {
    companion object {
        fun intToHoras(horas: Int, minutos: Int, segundos: Int): String{
            var horasAux=""
            if(horas<10){
                horasAux="0$horas:"
            }else{
                horasAux="$horas:"
            }
            if(minutos<10){
                horasAux+="0$minutos:"
            }else{
                horasAux+="$minutos:"
            }
            if(segundos<10){
                horasAux+="0$segundos"
            }else{
                horasAux+="$segundos"
            }
            return horasAux
        }
        fun somarHoras(relatorios: List<RelatorioModel>): String {
            var horas = 0
            var minutos = 0
            var segundos = 0
            //var milessegundos: Long = 0

            for (rel in relatorios) {
                horas += rel.horas.subSequence(0, 2).toString().toInt()
                minutos += rel.horas.subSequence(3, 5).toString().toInt()
                segundos += rel.horas.subSequence(6, 8).toString().toInt()
            }

            if (segundos >= 60) {
                val min = Math.floor(segundos.toDouble() / 60).toInt()
                val seg = segundos - (min * 60)

                minutos += min
                segundos = seg
            }
            if (minutos >= 60) {
                val hor = Math.floor(minutos.toDouble() / 60).toInt()
                val min = minutos - (hor * 60)

                horas += hor
                minutos = min
            }

            return intToHoras(horas,minutos,segundos)
            /*
            milessegundos = (segundos * 1000).toLong()
            milessegundos += (minutos * 60000).toLong()
            milessegundos += (horas * 3600 * 1000).toLong()

            return Time(milessegundos)

             */
        }

        fun dataSqlParaPadaoPtBr(data: Date): String {
            val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
            return df.format(data)
        }

        fun getMesString(mes: Int): String {
            return when (mes) {
                1 -> {
                    "Jan"
                }
                2 -> {
                    "Fer"
                }
                3 -> {
                    "Mar"
                }
                4 -> {
                    "Abr"
                }
                5 -> {
                    "Mai"
                }
                6 -> {
                    "Jun"
                }
                7 -> {
                    "Jul"
                }
                8 -> {
                    "Ago"
                }
                9 -> {
                    "Set"
                }
                10 -> {
                    "Out"
                }
                11 -> {
                    "Nov"
                }
                12 -> {
                    "Dez"
                }
                else -> {
                    "Erro"
                }
            }
        }

        fun getDiaDaSemana(data: Date): String {
            val calendar: Calendar = GregorianCalendar()
            calendar.time = data

            return when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SUNDAY -> {
                    "Dom"
                }
                Calendar.MONDAY -> {
                    "Seg"
                }
                Calendar.TUESDAY -> {
                    "Ter"
                }
                Calendar.WEDNESDAY -> {
                    "Qua"
                }
                Calendar.THURSDAY -> {
                    "Qui"
                }
                Calendar.FRIDAY -> {
                    "Sex"
                }
                Calendar.SATURDAY -> {
                    "Sáb"
                }

                else -> {
                    return ""
                }
            }
        }
    }
}