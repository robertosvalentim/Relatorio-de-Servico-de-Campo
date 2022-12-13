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


    fun insert(relatorio: RelatorioModel): Boolean {
        return repository.insert(relatorio)
    }

    fun editar(id:Long,relatorio: RelatorioModel): Boolean{
        return repository.update(id,relatorio)
    }

    fun get(id: Long) {
        _relatorio.value =
            repository.get(id)
    }

}