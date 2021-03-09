package com.example.estudiantesbrandon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.estudiantesbrandon.Data.ListStudents
import com.example.estudiantesbrandon.Entity.EntityStudent
import com.example.estudianteseduardo.R

import com.example.estudianteseduardo.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding

    private var listEstudents= ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_home)


        binding.button.setOnClickListener {

           //va dentro del if

            if(binding.edtName.text.isNotEmpty()&& binding.edtLastName.text.isNotEmpty()) {

                val student =
                    EntityStudent()

                student.name = binding.edtName.text.toString()
                student.lastName = binding.edtLastName.text.toString()
                student.gender = binding.spnGender.selectedItemPosition
                // val genderText:String=binding.spnGender.selectedItem.toString()

                when (binding.rgdDegree.checkedRadioButtonId) {

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

                student.sport = binding.ckbSport.isChecked
                student.travel = binding.ckbTravel.isChecked
                student.read = binding.ckbRead.isChecked

                student.financialAssistance = binding.swtFinancialAssistance.isChecked



                val index = listEstudents.add(student)

                if (index >= 0) {

                    Toast.makeText(this@FormActivity, "Estudiante guardado.", Toast.LENGTH_SHORT).show()

                    binding.edtName.text.clear()

                    binding.edtLastName.text.clear()

                    binding.rgdDegree.clearCheck()

                    binding.ckbSport.isChecked=false

                    binding.ckbTravel.isChecked=false

                    binding.ckbRead.isChecked=false

                    binding.swtFinancialAssistance.isChecked=false

                    binding.spnGender.setSelection(0)


                } else {

                    Snackbar.make(it, "Estudiante NO guardado", Snackbar.LENGTH_SHORT).show()

                }


            }
            else
            {
                Snackbar.make(it, "El nombre y los apellidos son OBLIGATORIOS", Snackbar.LENGTH_SHORT).show()
            }




        }

        /*
        binding.spnGender.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selectedText = p0?.getItemAtPosition(p2)

                Toast.makeText(this@FormActivity,"estoy en evento onItemSelected $p2 $selectedText",Toast.LENGTH_SHORT).show()

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@FormActivity,"estoy en evento onNothingSelected",Toast.LENGTH_SHORT).show()
            }

        }

        binding.swtFinancialAssistance.setOnCheckedChangeListener { it,isChecked ->

            val checked = if (isChecked) "On" else "Off"

            Toast.makeText(this@FormActivity,"estoy en evento setOnCheckedListener $checked",Toast.LENGTH_SHORT).show()

        }

        */


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmList ->{
                val intent = Intent(this@FormActivity,
                    ListActivity::class.java)
                startActivity(intent)
            }
            R.id.itmForm ->{
                val intent = Intent(this@FormActivity,
                    EditarEliminarActivity::class.java)
                startActivity(intent)

            }
            R.id.itmExit ->{
                finish()

            }
        }


        return super.onOptionsItemSelected(item)
    }
}