package com.beto.h2.relatoriodeserviodecampo2.model

import java.sql.Time

data class RelatorioGeralModel(
    var horas: Time,
    var publicacoes: Int = 0,
    var videos: Int = 0,
    var revisitas: Int = 0,
    var estudos: Int = 0
)