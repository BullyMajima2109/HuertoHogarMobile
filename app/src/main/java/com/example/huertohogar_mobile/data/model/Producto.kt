package com.example.huertohogar_mobile.data.model

data class Producto(
    val id: Long,
    val nombre: String,
    val precio: Int
)

data class CrearProductoRequest(
    val nombre: String,
    val precio: Int
)
