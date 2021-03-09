package com.example.estudiantesbrandon.Data

import android.util.Log
import com.example.estudiantesbrandon.Entity.EntityStudent
import com.example.estudiantesbrandon.Tools.Constants

class ListStudents {



    fun add(student: EntityStudent):Int{
        var answer= -1

        if (!existNameFilter(student.name)){
            listStudents.add(student)
            answer = listStudents.size -1

        }
        return answer;
    }

    fun delete(name:String):Boolean{
        var answer:Boolean = false

        for ((index,item) in listStudents.withIndex()){

            if (item.name == name){
                listStudents.removeAt(index)
                answer=true;
                break;
            }
        }
            return answer;
    }


    fun edit(name:String, student: Unit):Boolean{

        var answer:Boolean = false

        for ((index,item) in listStudents.withIndex()){

            if (item.name == name){
                answerList.add("${item.name} ${item.lastName} ${if(item.gender==1) "Masculino" else if(item.gender==2) "Femenino" else "Genero no seleccionado"} ${item.degree}")
            }
        }
        return answer;

    }

    fun showListStudents(){

        Log.d(Constants.LOG_TAG,"${listStudents.size}")

        for ((index,item) in listStudents.withIndex()){

            Log.d(Constants.LOG_TAG,"$index ${listStudents[index].name} ${listStudents[index].degree} ${listStudents[index].gender}")

        }
    }
/*
    fun existsName(name:String):Boolean {
        var answer: Boolean = false
        for (element in listStudents) {
            if (element.name == name) {
                answer = true;
                break;
            }
        }
        return answer;
    }
    */

    fun existNameFilter(name:String):Boolean{
        var answer: Boolean=false

        if ( listStudents.filter { e-> e.name==name }.count()==1){
            answer=true
        }

        return answer
    }
    val answerList= arrayListOf<String>()//va dentro del fun getstringarray
    fun getStringArray(name: String):Array<String>{



        for((index,item) in listStudents.withIndex()){

            answerList.add("${item.name} ${item.lastName} ${if(item.gender==1) "Masculino" else if(item.gender==2) "Femenino" else "Genero no seleccionado"} ${item.degree}" )
        }
        return answerList.toTypedArray()
    }

    fun getEntityEstudentArray():Array<EntityStudent>{

        return listStudents.toTypedArray()

    }

    fun getStudent(index:Int): EntityStudent {

        return listStudents[index]

    }

    companion object{
        private var listStudents = arrayListOf<EntityStudent>()
    }
}