package com.beto.h2.relatoriodeserviodecampo2.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.beto.h2.relatoriodeserviodecampo2.R
import com.beto.h2.relatoriodeserviodecampo2.constants.DataBaseConstants
import com.beto.h2.relatoriodeserviodecampo2.databinding.ActivityRelatorioFormBinding
import com.beto.h2.relatoriodeserviodecampo2.model.RelatorioModel
import com.beto.h2.relatoriodeserviodecampo2.util.Tempo
import com.beto.h2.relatoriodeserviodecampo2.viewmodel.RelatorioFormViewModel
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class RelatorioFormActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityRelatorioFormBinding? = null
    private lateinit var viewModel: RelatorioFormViewModel

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    private var selectedHour = 0
    private var selectedMinute = 0

    private var publicacoes = 0
    private var videos = 0
    private var revisitas = 0
    private var estudos = 0

    private var relatorioEditar: Boolean = false
    private var relatorioId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioFormBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        title = "Adicionar Registro"

        viewModel = ViewModelProvider(this)[RelatorioFormViewModel::class.java]

        binding!!.buttonSave.setOnClickListener(this)
        binding!!.butDataMais.setOnClickListener(this)
        binding!!.butDataMenos.setOnClickListener(this)
        binding!!.butHorasMais.setOnClickListener(this)
        binding!!.butHorasMenos.setOnClickListener(this)
        binding!!.butPublicacoesMais.setOnClickListener(this)
        binding!!.butPublicacoesMenos.setOnClickListener(this)
        binding!!.butVideosMais.setOnClickListener(this)
        binding!!.butVideosMenos.setOnClickListener(this)
        binding!!.butRevisitasMais.setOnClickListener(this)
        binding!!.butRevisitasMenos.setOnClickListener(this)
        binding!!.butEstudosMais.setOnClickListener(this)
        binding!!.butEstudosMenos.setOnClickListener(this)

        observer()
        carregarDados()

    }

    private fun observer() {
        viewModel.relatorio.observe(this, androidx.lifecycle.Observer { rel ->

            relatorioId = rel.id

            binding!!.inputData.setText(Tempo.dataSqlParaPadaoPtBr(rel.data))
            binding!!.inputHoras.setText(rel.horas.toString())
            binding!!.inputPublicacoes.setText(rel.publicacoes.toString())
            binding!!.inputVideos.setText(rel.videos.toString())
            binding!!.inputRevisitas.setText(rel.revisitas.toString())
            binding!!.inputEstudos.setText(rel.estudos.toString())

            selectedYear = rel.data.toString().subSequence(0,4).toString().toInt()
            selectedMonth = rel.data.toString().subSequence(5,7).toString().toInt()
            selectedDay = rel.data.toString().subSequence(8,10).toString().toInt()
            selectedHour = rel.horas.toString().subSequence(0,2).toString().toInt()
            selectedMinute = rel.horas.toString().subSequence(3,5).toString().toInt()

            publicacoes = rel.publicacoes
            videos = rel.videos
            revisitas = rel.revisitas
            estudos = rel.estudos
        })

    }

    override fun onClick(v: View) {
        Log.i("TESTE", "Passou aqui")
        when (v.id) {
            R.id.button_save -> {
                if (!verificarCamposVazios()) {
                    if (relatorioEditar) {
                        if(viewModel.editar(relatorioId,getValues())){
                            Toast.makeText(this, R.string.relatorio_editado, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        if (viewModel.insert(getValues())) {
                            Toast.makeText(this, R.string.relatorio_salvo, Toast.LENGTH_LONG).show()
                        }
                    }
                    limparFormulario()
                    finish()
                }
            }
            R.id.but_data_mais -> {
                getData()
            }
            R.id.but_data_menos -> {
                binding!!.inputData.text = null
            }
            R.id.but_horas_mais -> {
                getTime()
            }
            R.id.but_horas_menos -> {
                binding!!.inputHoras.text = null
            }
            R.id.but_publicacoes_mais -> {
                publicacoes++
                binding!!.inputPublicacoes.setText("$publicacoes")
            }
            R.id.but_publicacoes_menos -> {
                if (publicacoes != 0)
                    publicacoes--
                binding!!.inputPublicacoes.setText("$publicacoes")
            }
            R.id.but_videos_mais -> {
                videos++
                binding!!.inputVideos.setText("$videos")
            }
            R.id.but_videos_menos -> {
                if (videos != 0)
                    videos--
                binding!!.inputVideos.setText("$videos")
            }
            R.id.but_revisitas_mais -> {
                revisitas++
                binding!!.inputRevisitas.setText("$revisitas")
            }
            R.id.but_revisitas_menos -> {
                if (revisitas != 0)
                    revisitas--
                binding!!.inputRevisitas.setText("$revisitas")
            }
            R.id.but_estudos_mais -> {
                estudos++
                binding!!.inputEstudos.setText("$estudos")
            }
            R.id.but_estudos_menos -> {
                if (estudos != 0)
                    estudos--
                binding!!.inputEstudos.setText("$estudos")
            }
        }
    }


    private fun getData() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val listener =
            DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDay ->
                this.selectedYear = selectedYear
                this.selectedMonth = selectedMonth + 1
                this.selectedDay = selectedDay

                binding!!.inputData.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }
        val datePicker = DatePickerDialog(this, listener, year, month, day)
        datePicker.show()
    }

    private fun getTime() {

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            selectedHour = hour
            selectedMinute = minute

            binding!!.inputHoras.setText(SimpleDateFormat("HH:mm").format(cal.time))
        }
        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun getValues(): RelatorioModel {

        return RelatorioModel(
            0,
            Date.valueOf("$selectedYear-$selectedMonth-$selectedDay"),
            Time.valueOf("$selectedHour:$selectedMinute:00"),
            publicacoes,
            videos,
            revisitas,
            estudos
        )
    }

    fun limparFormulario() {
        binding!!.inputData.text = null
        binding!!.inputHoras.text = null
        binding!!.inputPublicacoes.text = null
        binding!!.inputVideos.text = null
        binding!!.inputRevisitas.text = null
        binding!!.inputEstudos.text = null

        publicacoes = 0
        videos = 0
        revisitas = 0
        estudos = 0
    }

    fun verificarCamposVazios(): Boolean {
        var campoVazio = false
        if (binding!!.inputData.text.toString() == "" || binding!!.inputData.text.isNullOrBlank()) {
            binding!!.inputLayoutData.error = "Por favor, informe a data!"
            campoVazio = true
        }
        if (binding!!.inputHoras.text.toString() == "" || binding!!.inputHoras.text.isNullOrBlank()) {
            binding!!.inputLayoutHoras.error = "Por favor, informe as horas!"
            campoVazio = true
        }
        return campoVazio
    }

    fun carregarDados() {
        val bundle = intent.extras
        if (bundle != null) {
            val id = bundle.getLong(DataBaseConstants.ID)
            if (id != null) {
                relatorioEditar = true
                title = "Editar Registro"
                viewModel.get(id)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}