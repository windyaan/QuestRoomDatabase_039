package com.example.project.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import com.example.project.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
}