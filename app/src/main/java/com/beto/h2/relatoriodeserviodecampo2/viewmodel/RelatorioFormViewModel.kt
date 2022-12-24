package com.beto.h2.relatoriodeserviodecampo2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.repository.RelatorioRepository

class RelatorioFormViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RelatorioRepository.getInstance(application)

    private val _relatorio = MutableLiveData<RelatorioModel>()
    val relatorio: LiveData<RelatorioModel> = _relatorio

    private val _relatorioEditado = MutableLiveData<Boolean>(false)
    val relatorioEditado: LiveData<Boolean> = _relatorioEditado

    private val _relatorioInserido = MutableLiveData<Boolean>(false)
    val relatorioInserido: LiveData<Boolean> = _relatorioInserido


    fun insert(relatorio: RelatorioModel) {
        _relatorioInserido.value = repository.insert(relatorio)
    }

    fun editar(id:Long,relatorio: RelatorioModel){
        _relatorioEditado.value = repository.update(id,relatorio)
    }

    fun get(id: Long) {
        _relatorio.value =
            repository.get(id)
    }

}