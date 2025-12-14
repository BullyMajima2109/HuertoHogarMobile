package com.example.huertohogar_mobile.repository

import com.example.huertohogar_mobile.data.model.CrearProductoRequest
import com.example.huertohogar_mobile.data.model.Producto
import com.example.huertohogar_mobile.data.remote.BackendRetrofitInstance

class ProductoRepository {

    suspend fun getProductos(): List<Producto> {
        return BackendRetrofitInstance.api.getProductos()
    }

    suspend fun crearProducto(nombre: String, precio: Int): Producto {
        return BackendRetrofitInstance.api.crearProducto(
            CrearProductoRequest(nombre, precio)
        )
    }
}
