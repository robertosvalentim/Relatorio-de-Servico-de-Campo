package com.beto.h2.relatoriodeserviodecampo2.model

import java.sql.Date
import java.sql.Time

data class RelatorioModel(
    var id: Long = 0,
    var data: Date,
    var horas: Time,
    var publicacoes: Int = 0,
    var videos: Int = 0,
    var revisitas: Int = 0,
    var estudos: Int = 0
): java.io.Serializable
