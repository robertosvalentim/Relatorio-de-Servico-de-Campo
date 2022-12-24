package com.beto.h2.relatoriodeserviodecampo2.repository

import android.content.ContentValues
import android.content.Context
import com.beto.h2.relatoriodeserviodecampo2.constants.DataBaseConstants
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import java.sql.Date
import java.sql.Time

class RelatorioRepository private constructor(context: Context) {

    private val relatorioDataBase = RelatorioDataBase(context)

    companion object {
        private lateinit var repository: RelatorioRepository

        fun getInstance(context: Context): RelatorioRepository {
            if (!Companion::repository.isInitialized) {
                repository = RelatorioRepository(context)
            }
            return repository
        }
    }

    fun insert(relatorio: RelatorioModel): Boolean {
        return try {
            val db = relatorioDataBase.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.COLUNM_DATA, relatorio.data.toString())
            values.put(DataBaseConstants.COLUNM_HORAS, relatorio.horas)
            values.put(DataBaseConstants.COLUNM_PUBLICACOES, relatorio.publicacoes)
            values.put(DataBaseConstants.COLUNM_VIDEOS, relatorio.videos)
            values.put(DataBaseConstants.COLUNM_REVISITAS, relatorio.revisitas)
            values.put(DataBaseConstants.COLUNM_ESTUDOS, relatorio.estudos)

            db.insert(DataBaseConstants.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(id: Long,relatorio: RelatorioModel): Boolean {
        return try {
            val db = relatorioDataBase.writableDatabase
            val values = ContentValues()

            values.put(DataBaseConstants.COLUNM_DATA, relatorio.data.toString())
            values.put(DataBaseConstants.COLUNM_HORAS, relatorio.horas)
            values.put(DataBaseConstants.COLUNM_PUBLICACOES, relatorio.publicacoes)
            values.put(DataBaseConstants.COLUNM_VIDEOS, relatorio.videos)
            values.put(DataBaseConstants.COLUNM_REVISITAS, relatorio.revisitas)
            values.put(DataBaseConstants.COLUNM_ESTUDOS, relatorio.estudos)

            val selection = "${DataBaseConstants.COLUNM_ID} = ?"
            val args = arrayOf(id.toString())

            db.update(DataBaseConstants.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(relatorioId: Long): Boolean {
        return try {
            val db = relatorioDataBase.writableDatabase

            val selection = "${DataBaseConstants.COLUNM_ID} = ?"
            val args = arrayOf(relatorioId.toString())

            db.delete(DataBaseConstants.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getRelatorioDetalhadoAnoMes(ano: Int, mes: Int): List<RelatorioModel> {
        val list = mutableListOf<RelatorioModel>()
        try {
            val db = relatorioDataBase.readableDatabase
            //val args = arrayOf("$ano-$mes")
            val query =
                "SELECT * FROM " +
                        DataBaseConstants.TABLE_NAME +
                        " WHERE " + DataBaseConstants.COLUNM_DATA +
                        " LIKE '$ano-$mes-%'"
            val cursor = db.rawQuery(
                query, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndex(DataBaseConstants.COLUNM_ID))
                    val data =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUNM_DATA))
                    val horas =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUNM_HORAS))
                    val publicacoes =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_PUBLICACOES))
                    val videos =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_VIDEOS))
                    val revisitas =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_REVISITAS))
                    val estudos =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_ESTUDOS))

                    val relatorio = RelatorioModel(
                        id,
                        Date.valueOf(data),
                        horas,
                        publicacoes,
                        videos,
                        revisitas,
                        estudos
                    )
                    list.add(relatorio)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun get(id: Long): RelatorioModel? {
        var relatorio: RelatorioModel? = null
        try {
            val db = relatorioDataBase.readableDatabase

            val colunsArray = arrayOf(
                DataBaseConstants.COLUNM_ID,
                DataBaseConstants.COLUNM_DATA,
                DataBaseConstants.COLUNM_HORAS,
                DataBaseConstants.COLUNM_PUBLICACOES,
                DataBaseConstants.COLUNM_VIDEOS,
                DataBaseConstants.COLUNM_REVISITAS,
                DataBaseConstants.COLUNM_ESTUDOS
            )
            val selection = "${DataBaseConstants.COLUNM_ID} = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.TABLE_NAME, colunsArray, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val data =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUNM_DATA))
                    val horas =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUNM_HORAS))
                    val publicacoes =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_PUBLICACOES))
                    val videos =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_VIDEOS))
                    val revisitas =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_REVISITAS))
                    val estudos =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUNM_ESTUDOS))

                    relatorio = RelatorioModel(
                        id,
                        Date.valueOf(data),
                        horas,
                        publicacoes,
                        videos,
                        revisitas,
                        estudos
                    )

                }
            }
            cursor.close()
        } catch (e: Exception) {
            return relatorio
        }
        return relatorio
    }
}