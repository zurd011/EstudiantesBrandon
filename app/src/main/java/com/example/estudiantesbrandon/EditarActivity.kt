package com.example.estudiantesbrandon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.estudiantesbrandon.Data.ListStudents
import com.example.estudiantesbrandon.Entity.EntityStudent
import com.example.estudiantesbrandon.Tools.Constants
import com.example.estudianteseduardo.R
import com.example.estudianteseduardo.databinding.ActivityEditarBinding
import com.google.android.material.snackbar.Snackbar


class EditarActivity : AppCompatActivity() {

    private val listEstudents =
        ListStudents()
    private lateinit var binding: ActivityEditarBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_Edit)

        val id: Int = intent.getIntExtra(Constants.ID, -1)

        if (id != -1) {
            val student = listEstudents.getStudent(id)


            binding.edtName2.setText(student.name)

            binding.edtLastName2.setText(student.lastName)

            binding.spnGender2.setSelection(student.gender)

            //binding.rgdDegre2.isEnabled=student.degree

            binding.swtFinancialAssistance2.isChecked=student.financialAssistance

            binding.ckbTravel2.isChecked=student.travel

            binding.ckbRead2.isChecked=student.read

            binding.ckbSport2.isChecked=student.sport



            binding.button2.setOnClickListener{

                if(binding.edtName2.text.isNotEmpty()&& binding.edtLastName2.text.isNotEmpty()) {

                    val student =
                        EntityStudent()

                    student.name = binding.edtName2.text.toString()
                    student.lastName = binding.edtLastName2.text.toString()
                    student.gender = binding.spnGender2.selectedItemPosition
                    // val genderText:String=binding.spnGender.selectedItem.toString()

                    when (binding.rgdDegre2.checkedRadioButtonId) {

                        binding.rdbUnfinishedStudies.id -> {
                            student.degree = "trunco"
                        }
                        binding.rdbUniversityIntern.id -> {
                            student.degree = "pasante"
                        }
                        binding.rdbFinishedStudies.id -> {
                            student.degree = "titulado"
                        }

                    }

                    student.sport = binding.ckbSport2.isChecked
                    student.travel = binding.ckbTravel2.isChecked
                    student.read = binding.ckbRead2.isChecked

                    student.financialAssistance = binding.swtFinancialAssistance2.isChecked



                    val index = listEstudents.add(student)

                    if (index >= 0) {

                        Toast.makeText(this@EditarActivity, "Editado", Toast.LENGTH_SHORT).show()

                        binding.edtName2.text.clear()

                        binding.edtLastName2.text.clear()

                        binding.rgdDegre2.clearCheck()

                        binding.ckbSport2.isChecked=false

                        binding.ckbTravel2.isChecked=false

                        binding.ckbRead2.isChecked=false

                        binding.swtFinancialAssistance2.isChecked=false

                        binding.spnGender2.setSelection(0)


                    } else {

                        Snackbar.make(it, "Estudiante NO Editado", Snackbar.LENGTH_SHORT).show()

                    }


                }
                else
                {
                    Snackbar.make(it, "El nombre y los apellidos son OBLIGATORIOS", Snackbar.LENGTH_SHORT).show()
                }




            }



        }






    }
}