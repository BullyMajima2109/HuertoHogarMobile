package com.example.huertohogar_mobile.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ProfileViewModel : ViewModel() {

    private val _photoUri = MutableStateFlow<Uri?>(null)
    val photoUri: StateFlow<Uri?> = _photoUri.asStateFlow()

    // ✅ NUEVO: SharedPreferences para guardar/cargar la foto
    private var prefs: android.content.SharedPreferences? = null

    private val PREFS_NAME = "profile_prefs"
    private val KEY_PHOTO_URI = "photo_uri"

    // ✅ NUEVO: llamar una vez desde la UI (ProfileContent) para cargar lo guardado
    fun init(context: Context) {
        if (prefs != null) return // evita re-inicializar
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val saved = prefs?.getString(KEY_PHOTO_URI, null)
        _photoUri.value = saved?.let { Uri.parse(it) }
    }

    // ✅ MODIFICADO: guarda la URI además de actualizar el StateFlow
    fun updatePhotoUri(context: Context, uri: Uri) {
        viewModelScope.launch {
            _photoUri.value = uri
            prefs?.edit()?.putString(KEY_PHOTO_URI, uri.toString())?.apply()
        }
    }

    // ✅ MODIFICADO: limpia también lo guardado
    fun clearPhoto() {
        viewModelScope.launch {
            _photoUri.value = null
            prefs?.edit()?.remove(KEY_PHOTO_URI)?.apply()
        }
    }
}
