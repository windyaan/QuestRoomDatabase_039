package com.example.project.dependencyinjection

import android.content.Context
import com.example.project.Repository.LocalRepositoryMhs
import com.example.project.Repository.RepositoryMhs
import com.example.project.data.database.KrsDatabase

interface InterfaceContainerApp{
    val repositoryMhs: RepositoryMhs
}

class ContainerApp (private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}