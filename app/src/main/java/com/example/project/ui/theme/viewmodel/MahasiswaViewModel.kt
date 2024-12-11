package com.example.project.ui.theme.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.Repository.RepositoryMhs
import com.example.project.data.entity.Mahasiswa
import kotlinx.coroutines.launch

// data class var yg menyimpan
// data input form
data class MahasiswaEvent(
    val nim:String="",
    val nama:String="",
    val jenisKelamin:String="",
    val alamat:String="",
    val kelas:String="",
    val angkatan:String="",
)

//menyimpan input form kedlm entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa:Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
){
    fun isValid(): Boolean{
        return nim == null
                && nama == null
                && jenisKelamin == null
                && alamat == null
                && kelas == null
                &&angkatan == null
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)
class MahasiswaViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){

    var uiState by mutableStateOf(MhsUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    fun saveData(){
        val currentEvent = uiState.MahasiswaEvent

        if (validateFields()){
            viewModelScope.launch{
                try{
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        MahasiswaEvent = MahasiswaEvent(), //reset input form
                        isEntryValid = FormErrorState(), //reset error state
                    )
                } catch (e: exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    //validasi
    private fun validateFields(): Boolean{
        val event = uiState.MahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty())
                null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty())
                null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty())
                null else "jenisKelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty())
                null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty())
                null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty())
                null else "Angkatan tidak boleh kosong",
        )
    }
}

//reset pesan
fun resetSnackBarMessage(){
    uiState = uiState.copy(snackBarMessage = null)
}


