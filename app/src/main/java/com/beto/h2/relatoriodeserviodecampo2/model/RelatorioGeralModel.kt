package com.beto.h2.relatoriodeserviodecampo2.model


data class RelatorioGeralModel(
    var horas: String,
    var publicacoes: Int = 0,
    var videos: Int = 0,
    var revisitas: Int = 0,
    var estudos: Int = 0
)