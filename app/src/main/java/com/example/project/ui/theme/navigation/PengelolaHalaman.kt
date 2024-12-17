package com.example.project.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.project.ui.theme.view.mahasiswa.DestinasiInsert
import com.example.project.ui.theme.view.mahasiswa.HomeMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiInsert.route
    ){
        HomeMhsView(
            onDetailClick = { nim ->
                navController.navigate("${DestinasiDetail.route}/$nim")
                println(
                    "PengelolaHalaman: nim = $nim"
                )
            },
            onAddMhs = {
                navController.navigate(DestinasiInsert.route)
            },
            modifier = modifier
        )
    }
}