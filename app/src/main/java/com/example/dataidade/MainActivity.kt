package com.example.dataidade

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var message: EditText
    lateinit var buttonOk: Button
    private lateinit var resultado: TextView
    lateinit var lista:TextView
    private lateinit var btnCalendario: Button
    var cal = Calendar.getInstance()
    private lateinit var textIdade: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nome = findViewById(R.id.editNome)
        message = findViewById(R.id.editPres)
        buttonOk = findViewById(R.id.btnOk)
        resultado = findViewById(R.id.texRes)
        btnCalendario = findViewById(R.id.btnCalendario)


        buttonOk.setOnClickListener {
            val editNome = nome.text.toString()
            val editMsg = message.text.toString()

            // função para abrir o calendário e armazenar a data
            pegarData()

            //condiçâo para saber se os campos estão vazios
            when {
                editNome.isNotEmpty() && editMsg.isNotEmpty() -> {
                    resultado.text=("olá  $editNome, e o seu presente é um(a) $editMsg! ")
                }

                else ->{

                    nome?.error="Por favor, preencha o campo!"
                    message?.error="Por favor, preencha o campo!"
            }


            }
        }

    }

    // FUNCAO PARA PEGAR A DATA
    private fun pegarData() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                //CHAMANDO FUNCAO PARA CALCULAR IDADE DO DIA DO ANIVERSARIO MENOS DIA ATUAL
                calcularIdade(cal.get(Calendar.DAY_OF_YEAR))
            }

        btnCalendario.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_YEAR)).show()
            }
        })
    }

    // FUNCAO PARA CALCULAR DIA DO NIVER MENOS DIA ATUAL
    private fun calcularIdade(diaN: Int) {
        val cal2 = Calendar.getInstance()
        val diaA: Int = cal2.get(Calendar.DAY_OF_YEAR)

        val idade: Int = diaN - diaA
        textIdade.setText(idade)
    }
}








