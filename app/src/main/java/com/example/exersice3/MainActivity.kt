package com.example.exersice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long) {

        val selectedItem = spinnerAge.getItemAtPosition(position)
        Toast.makeText(this,
            "Selected Item =" + selectedItem,
            Toast.LENGTH_LONG ).show()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener {
            calculatePremium()
        }

    }

    private fun calculatePremium(){
        //Get the age group. Position of an array
        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60 //Less than 17
            1 -> 70 //17 to 25
            2 -> 90
            3 -> 120
            else -> 150
        }

        //Get the gender selection. ID of radio button
        val gender : Int= radioGroupGender.checkedRadioButtonId

        if(gender == R.id.radioButtonMale){
            //Calculate premium for male
            when(age){
                0-> premium += 0
                1-> premium += 50
                2-> premium += 100
                3-> premium += 150
                else -> premium += 200
            }
        }else {
            premium
            //Calculate premium for female
        }
        //Determine smoker or non-smoker
        val smoker : Boolean= checkBoxSmoker.isChecked
        if(smoker){
            //Calculate premium for smoker
            when(age){
                0-> premium += 0
                1-> premium += 100
                2-> premium += 150
                3-> premium += 200
                4-> premium += 250
                else-> premium += 300
            }
        }else{
            premium += 0
        }


        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = String.format("%s %d", symbol, premium)
    }

    fun reset(view: View) {
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        checkBoxSmoker.setChecked(false)

        textViewPremium.text = getString(R.string.insurance_premium);


    }

}
