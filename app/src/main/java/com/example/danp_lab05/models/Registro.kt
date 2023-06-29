package com.example.danp_lab05.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registros")
data class Registro (
    @PrimaryKey(autoGenerate = true)
    val idRegistro: Int = 0,
    val fechaRegistro: String,
    val horaRegistro: String,
    val temperatura: String,
    val intenLuz: String,
    val presionAtm: String,
    val humedadRel: String
)
