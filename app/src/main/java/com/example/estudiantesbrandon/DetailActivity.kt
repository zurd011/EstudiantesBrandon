package com.example.estudiantesbrandon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.widget.Toast
import com.example.estudiantesbrandon.Data.ListStudents
import com.example.estudiantesbrandon.Tools.Constants
import com.example.estudianteseduardo.R
import com.example.estudianteseduardo.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private val listEstudents= ListStudents()
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_detail)

        val id:Int = intent.getIntExtra(Constants.ID,-1)

        if(id!=-1)
        {
            val student= listEstudents.getStudent(id)

            binding.txvFullName.text= "${student.name} ${student.lastName}"

            binding.txvGender.text = "${if(student.gender==1) "Masculino" else if(student.gender==2) "Femenino" else "Genero no seleccionado"}"

            binding.txvDegree.text= "${student.degree}"

            binding.txvFinancialAssistence.text="${if(student.financialAssistance)"Con beca" else "Sin beca"}"

            binding.txvHobbies.text = "Pasatiempos: ${if(student.sport) "Deportes" else ""} ${if(student.read) "Leer" else ""} ${if(student.travel) "Viajar" else ""}"



            binding.btnDelete.setOnClickListener{

               if( listEstudents.delete(student.name))
               {
                   Toast.makeText(this@DetailActivity, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                   //finish()
                   super.onBackPressed()
               }
                else
               {
                   Toast.makeText(this@DetailActivity, "Elemento no eliminado", Toast.LENGTH_SHORT).show()
               }
            }

        }
        else
        {
            Toast.makeText(this@DetailActivity,"error al cargar actividad",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}