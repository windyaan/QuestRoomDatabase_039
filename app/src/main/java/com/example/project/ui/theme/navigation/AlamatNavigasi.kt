package com.example.project.ui.theme.navigation

interface AlamatNavigasi {
    val route: String
}

//tujuan navigasi (destination) untuk halaman "Home"
object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetail :AlamatNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}
object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}