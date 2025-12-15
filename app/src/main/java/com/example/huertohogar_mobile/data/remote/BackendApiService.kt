package com.example.huertohogar_mobile.data.remote

import com.example.huertohogar_mobile.data.model.CrearProductoRequest
import com.example.huertohogar_mobile.data.model.Producto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BackendApiService {

    @GET("api/productos")
    suspend fun getProductos(): List<Producto>

    @POST("api/productos")
    suspend fun crearProducto(@Body req: CrearProductoRequest): Producto
}
