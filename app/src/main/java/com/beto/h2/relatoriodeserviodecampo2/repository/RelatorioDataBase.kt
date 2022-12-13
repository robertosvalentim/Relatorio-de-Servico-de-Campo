package com.beto.h2.relatoriodeserviodecampo2.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.beto.h2.relatoriodeserviodecampo2.constants.DataBaseConstants

class RelatorioDataBase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {


    companion object {
        private const val NAME = "relatoriodb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + DataBaseConstants.TABLE_NAME + " (" +
                    "${DataBaseConstants.COLUNM_ID} integer primary key autoincrement," +
                    "${DataBaseConstants.COLUNM_DATA} text," +
                    "${DataBaseConstants.COLUNM_HORAS} text," +
                    "${DataBaseConstants.COLUNM_PUBLICACOES} integer," +
                    "${DataBaseConstants.COLUNM_VIDEOS} integer," +
                    "${DataBaseConstants.COLUNM_REVISITAS} integer," +
                    "${DataBaseConstants.COLUNM_ESTUDOS} integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersio: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}