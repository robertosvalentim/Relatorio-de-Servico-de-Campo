package com.beto.h2.relatoriodeserviodecampo2.util

import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import java.sql.Date
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Tempo {
    companion object {

        fun somarHoras(relatorios: List<RelatorioModel>): Time {
            var horas = 0
            var minutos = 0
            var segundos = 0
            var milessegundos:Long = 0

            for (rel in relatorios) {
                horas += rel.horas.toString().subSequence(0, 2).toString().toInt()
                minutos += rel.horas.toString().subSequence(3, 5).toString().toInt()
                segundos += rel.horas.toString().subSequence(6, 8).toString().toInt()
            }

            milessegundos = segundos*1000 as Long
            milessegundos+= minutos*60000 as Long
            milessegundos+= horas*3600*1000 as Long

            return Time(milessegundos)
        }

        fun dataSqlParaPadaoPtBr(data: Date): String {
            val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
            return df.format(data)
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