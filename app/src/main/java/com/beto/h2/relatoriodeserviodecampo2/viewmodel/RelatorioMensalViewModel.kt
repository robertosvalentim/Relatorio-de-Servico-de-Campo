package com.beto.h2.relatoriodeserviodecampo2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.repository.RelatorioRepository

class RelatorioMensalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        RelatorioRepository.getInstance(application.applicationContext)

    private val listRelatorio = MutableLiveData<List<RelatorioModel>>()
    val relatorios: LiveData<List<RelatorioModel>> = listRelatorio



}