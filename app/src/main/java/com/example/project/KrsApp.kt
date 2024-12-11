package com.example.project

import android.app.Application
import com.example.project.dependencyinjection.ContainerApp
import com.example.project.dependencyinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp //fungsinya utk menyimpan Instance

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //Membuat Instance ContainerApp
        //instance adalah obj yg dibuat dari class
    }

}