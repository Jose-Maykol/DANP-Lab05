package com.example.danp_lab05.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
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
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.IoT
import com.example.danp_lab05.models.Registro
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun dataRegistro(navController: NavController? = null) {

    val registro = remember { mutableStateListOf<Registro>() }
    var temperatura by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }
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
        DatePicker(fecha = fechaRegistro) { fecha ->
            fechaRegistro = fecha
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        DateTimePicker(hora = horaRegistro) { horaSeleccionada ->
            horaRegistro = horaSeleccionada
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        TextField(
            value = temperatura,
            onValueChange = { temperatura = it },
            label = { Text("Temperatura") },
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

        TextField(
            value = nota,
            onValueChange = { nota = it },
            label = { Text("Nota") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        Button(
            onClick = {
                enviarRegistro(fechaRegistro, horaRegistro, temperatura, humedadRel, nota)
                //navController?.navigate("register_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar")
        }
    }
}
fun enviarRegistro(fechaRegistro: String, horaRegistro: String, temperatura: String, humedadRel: String, nota: String) {
    Log.i("fecha", " " + convertirFechaHora(fechaRegistro, horaRegistro))
    ///Cargar datos
    val item: IoT = IoT.builder()
        .datetime(Temporal.DateTime(convertirFechaHora(fechaRegistro, horaRegistro)))
        .temperature(temperatura.toDoubleOrNull()?:0.0)
        .humidity(humedadRel.toDoubleOrNull()?:0.0)
        .note(nota)
        .build()
    Amplify.DataStore.save(
        item,
        { success -> Log.i("Amplify", "Saved item: " + success.item().note) },
        { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
    )
}

@Composable
fun DatePicker(fecha: String, onFechaSeleccionada: (String) -> Unit) {
    val selectedDate = remember { mutableStateOf("") }
    val year: Int
    val month: Int
    val day: Int
    val cCalendar = Calendar.getInstance()
    year = cCalendar.get(Calendar.YEAR)
    month = cCalendar.get(Calendar.MONTH)
    day = cCalendar.get(Calendar.DAY_OF_MONTH)
    val cDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { datePicker, year: Int, month: Int, day: Int ->
            selectedDate.value = "$day/${month + 1}/$year"
            onFechaSeleccionada(selectedDate.value)
        }, year, month, day
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = fecha,
                onValueChange = { },
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
fun DateTimePicker(hora: String, onHoraSeleccionada: (String) -> Unit) {
    var hora by remember { mutableStateOf("") }
    val horas: Int
    val minutos: Int
    val c = Calendar.getInstance()
    horas = c.get(Calendar.HOUR_OF_DAY)
    minutos = c.get(Calendar.MINUTE)
    val timePickerDialog = TimePickerDialog(LocalContext.current,
        { _, horas, minutos ->
            val horaSeleccionada = "$horas:$minutos"
            hora = horaSeleccionada
            onHoraSeleccionada(horaSeleccionada)
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

fun convertirFechaHora(fecha: String, hora: String): String {
    val formatoEntrada = DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    val formatoSalida = DateTimeFormatter.ISO_INSTANT

    val fechaHora = LocalDateTime.parse("$fecha $hora", formatoEntrada)
    val instant = fechaHora.toInstant(ZoneOffset.UTC)
    val cadenaFormateada = formatoSalida.format(instant)

    return cadenaFormateada
}

