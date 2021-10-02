package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var lastDot :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true

        //Toast.makeText(this,"Works", Toast.LENGTH_SHORT).show()
    }

    fun onClear(view : View){
        tvInput.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view : View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value : String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }

    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)

                }

                //Subtraction
                if(tvInput.text.contains("-")){
                    val spiltValue = tvValue.split("-")
                    var one = spiltValue[0]
                    var two = spiltValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDecimal((one.toDouble() - two.toDouble()).toString())
                }

                //Addition

               else if(tvInput.text.contains("+")){
                    val spiltValue = tvValue.split("+")
                    var one = spiltValue[0]
                    var two = spiltValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDecimal((one.toDouble() + two.toDouble()).toString())
                }
                //Multiplication
                else if(tvInput.text.contains("*")){
                    val spiltValue = tvValue.split("*")
                    var one = spiltValue[0]
                    var two = spiltValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDecimal((one.toDouble() * two.toDouble()).toString())
                }

                //Division
                else if(tvInput.text.contains("/")){
                    val spiltValue = tvValue.split("/")
                    var one = spiltValue[0]
                    var two = spiltValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDecimal((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }


    }

    private fun removeZeroAfterDecimal(result: String): String{
        var value = result
         if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }


}