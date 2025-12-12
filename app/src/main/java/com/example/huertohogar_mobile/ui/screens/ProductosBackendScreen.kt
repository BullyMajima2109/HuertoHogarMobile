package com.example.huertohogar_mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar_mobile.viewmodel.ProductoViewModel

@Composable
fun ProductosBackendScreen(vm: ProductoViewModel = viewModel()) {

    val productos by vm.productos.collectAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()

    LaunchedEffect(Unit) {
        vm.cargarProductos()
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = { vm.cargarProductos() }, enabled = !loading) {
                Text("GET Productos")
            }
            Button(onClick = { vm.crearProductoDemo() }, enabled = !loading) {
                Text("POST Pera")
            }
        }

        Spacer(Modifier.height(12.dp))

        if (loading) CircularProgressIndicator()

        error?.let {
            Spacer(Modifier.height(8.dp))
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(productos) { p ->
                Text("• ${p.nombre} - $${p.precio} (id=${p.id})")
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
