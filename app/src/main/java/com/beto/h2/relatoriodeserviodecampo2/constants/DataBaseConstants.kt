package com.beto.h2.relatoriodeserviodecampo2.constants

import java.sql.Date
import java.sql.Time

class DataBaseConstants private constructor() {
    companion object {
        const val ID = "relatorioid"
        const val RELATORIO = "relatorio"
        const val TABLE_NAME = "relatorio"
        const val COLUNM_ID = "id"
        const val COLUNM_DATA = "data"
        const val COLUNM_HORAS = "horas"
        const val COLUNM_PUBLICACOES = "publicacoes"
        const val COLUNM_VIDEOS = "videos"
        const val COLUNM_REVISITAS = "revisitas"
        const val COLUNM_ESTUDOS = "estudos"
    }
}