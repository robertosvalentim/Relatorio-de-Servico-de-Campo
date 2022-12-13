package com.beto.h2.relatoriodeserviodecampo2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.repository.RelatorioRepository

class RelatorioDetalhadoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        RelatorioRepository.getInstance(application.applicationContext)

    private val listRelatorioAnoMesDetalhado = MutableLiveData<List<RelatorioModel>>()
    val relatorios: LiveData<List<RelatorioModel>> = listRelatorioAnoMesDetalhado


    fun getRelatorioAnoMesDetalhado(ano: Int, mes: Int) {
        listRelatorioAnoMesDetalhado.value = repository.getRelatorioDetalhadoAnoMes(ano, mes)
    }

    fun delete(id: Long) {
        repository.delete(id)
    }

    fun get(id: Long):RelatorioModel? {
        return repository.get(id)
    }
}