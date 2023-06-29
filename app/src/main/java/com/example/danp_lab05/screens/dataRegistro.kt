package com.example.danp_lab05.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.danp_lab05.models.Registro
import java.util.*

@Composable
fun dataRegistro(navController: NavController? = null) {

    val registro = remember { mutableStateListOf<Registro>() }
    var temperatura by remember { mutableStateOf("") }
    var intenLuz by remember { mutableStateOf("") }
    var presionAtm by remember { mutableStateOf("") }
    var humedadRel by remember { mutableStateOf("") }
    var fechaRegistro by remember { mutableStateOf("") }
    var horaRegistro by remember { mutableStateOf("") }
    var guardar by remember { mutableStateOf("Guardar") }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DatePicker(fechaRegistro)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        DateTimePicker(horaRegistro)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        TextField(
            value = temperatura,
            onValueChange = { temperatura = it },
            label = { Text("Temperatura") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        TextField(
            value = intenLuz,
            onValueChange = { intenLuz = it },
            label = { Text("Intensidad de luz") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        TextField(
            value = presionAtm,
            onValueChange = { presionAtm = it },
            label = { Text("Presión atmosférica") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        TextField(
            value = humedadRel,
            onValueChange = { humedadRel = it },
            label = { Text("Humedad Relativa") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        Button(
            onClick = {
                navController?.navigate("register_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            enviarRegistro()
            Text(text = "Registrar")
        }
    }
}
@Composable
fun enviarRegistro() {
    ///Cargar datos
}

@Composable
fun DatePicker(fecha: String) {
    var fecha by remember { mutableStateOf("") }
    val year: Int
    val month: Int
    val day: Int
    val cCalendar = Calendar.getInstance()
    year = cCalendar.get(Calendar.YEAR)
    month = cCalendar.get(Calendar.MONTH)
    day = cCalendar.get(Calendar.DAY_OF_MONTH)
    val cDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { DatePicker, year: Int, month: Int, day: Int ->
            fecha = "$day/${month + 1}/$year"
        }, year, month, day
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                readOnly = true,
                label = { Text(text = "Fecha del registro") }
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        cDatePickerDialog.show()
                    }
            )
        }
    }
}

@Composable
fun DateTimePicker(hora: String) {
    var hora by remember { mutableStateOf("") }
    val horas: Int
    val minutos: Int
    val c = Calendar.getInstance()
    horas = c.get(Calendar.HOUR_OF_DAY)
    minutos = c.get(Calendar.MINUTE)
    val timePickerDialog = TimePickerDialog(LocalContext.current,
        { DateTimePicker, horas, minutos ->
            hora = "$horas:$minutos"
        }, horas, minutos, false
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                readOnly = true,
                label = { Text(text = "Hora del registro") }
            )
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        timePickerDialog.show()
                    }
            )
        }
    }
}
