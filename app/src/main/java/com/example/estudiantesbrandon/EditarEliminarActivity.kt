package com.example.estudiantesbrandon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.estudiantesbrandon.Data.ListStudents
import com.example.estudiantesbrandon.Tools.Constants
import com.example.estudianteseduardo.R
import com.example.estudianteseduardo.databinding.ActivityListBinding

class EditarEliminarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListBinding
    private val listEstudents= ListStudents()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.text_list)


        listEstudents.showListStudents()

        val adapter = ArrayAdapter<String>(this@EditarEliminarActivity,android.R.layout.simple_list_item_1,listEstudents.getStringArray(name = String()))
        binding.ltvStudents.adapter = adapter



        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val selectedItem=adapterView.getItemAtPosition(position)

            //Toast.makeText(this@EditarEliminarActivity,"$position $id $selectedItem", Toast.LENGTH_SHORT).show()



            val intent = Intent(this@EditarEliminarActivity,
                EditarActivity::class.java).apply{
                putExtra(Constants.ID,position)
            }

            startActivity((intent))
        }

    }




    /*override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@EditarEliminarActivity,android.R.layout.simple_list_item_1,listEstudents.getStringArray()
        )
        binding.ltvStudents.adapter=adapter

    } */


}