package com.example.learn1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvRes: TextView
    private lateinit var tvOperation: TextView
    private var operand: Int = 0
    private var result: Int = 0
    private var operator: Char = '0'
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRes = findViewById(R.id.tv_result)
        tvOperation = findViewById(R.id.tv_operation)

        val btn0 : AppCompatButton = findViewById(R.id.btn0)
        val btn1 : AppCompatButton = findViewById(R.id.btn1)
        val btn2 : AppCompatButton = findViewById(R.id.btn2)
        val btn3 : AppCompatButton = findViewById(R.id.btn3)
        val btn4 : AppCompatButton = findViewById(R.id.btn4)
        val btn5 : AppCompatButton = findViewById(R.id.btn5)
        val btn6 : AppCompatButton = findViewById(R.id.btn6)
        val btn7 : AppCompatButton = findViewById(R.id.btn7)
        val btn8 : AppCompatButton = findViewById(R.id.btn8)
        val btn9 : AppCompatButton = findViewById(R.id.btn9)
        val btnPlus: AppCompatButton = findViewById(R.id.btnPlus)
        val btnMinus: AppCompatButton = findViewById(R.id.btnMinus)
        val btnDivisor: AppCompatButton = findViewById(R.id.btnDivisor)
        val btnMultiply: AppCompatButton = findViewById(R.id.btnMultiply)
        val btnPlusMinus : AppCompatButton = findViewById(R.id.btnPlusMinus)
        val btnCal: AppCompatButton = findViewById(R.id.btnResult)
        val btnC: AppCompatButton = findViewById(R.id.btnC)
        val btnCE: AppCompatButton = findViewById(R.id.btnCE)
        val btnBS: AppCompatImageButton = findViewById(R.id.btnBS)

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnCE.setOnClickListener(this)
        btnC.setOnClickListener(this)
        btnBS.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivisor.setOnClickListener(this)
        btnPlusMinus.setOnClickListener(this)
        btnCal.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {
        val id = p0?.id
        val idOperators:IntArray = intArrayOf(R.id.btnPlus, R.id.btnMinus, R.id.btnDivisor,
            R.id.btnMultiply, R.id.btnResult)

        when (id) {
            R.id.btn0 -> {
                addDigit(0)
            }
            R.id.btn1 -> {
                addDigit(1)
            }
            R.id.btn2 -> {
                addDigit(2)
            }
            R.id.btn3 -> {
                addDigit(3)
            }
            R.id.btn4 -> {
                addDigit(4)
            }
            R.id.btn5 -> {
                addDigit(5)
            }
            R.id.btn6 -> {
                addDigit(6)
            }
            R.id.btn7 -> {
                addDigit(7)
            }
            R.id.btn8 -> {
                addDigit(8)
            }
            R.id.btn9 -> {
                addDigit(9)
            }
            R.id.btnPlus->{
                calculate('+')
            }
            R.id.btnMinus->{
                calculate('-')
            }
            R.id.btnMultiply->{
                calculate('*')
            }
            R.id.btnDivisor->{
                calculate('/')
            }
            R.id.btnResult->{
                calculate('=')
            }
            R.id.btnC->{
                reset()
            }
            R.id.btnCE->{
                operand = 0
                tvRes.text = "$operand"
            }
            R.id.btnBS->{
                if(operand != 0){
                    operand /= 10
                    tvRes.text = "$operand"
                }
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun addDigit(digit: Int){
        operand = operand*10 + digit
        tvRes.text = "$operand"
    }

    private fun handleException(opt: Char) {

    }

    @SuppressLint("SetTextI18n")
    private fun calculate(opt: Char){
        val mp = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2, '0' to 100);

        handleException(opt);

        when(operator){
            '+' ->{
                result += operand
                operation = "$operation $operand"
            }
            '-' ->{
                result -= operand
                operation = "$operation $operand"
            }
            '*' ->{
                result *= operand
                operation = "$operation $operand"
            }
            '/' ->{
                result /= operand
                operation = "$operation $operand"
            }
            '0' ->{
                result = operand
                operation = "$operand"
            }
        }

        if(opt != '='){
            if(mp[opt]!! > mp[operator]!!){
                operation = "($operation) $opt"
            }else{
                operation = "$operation $opt"
            }
            operand = 0
            operator = opt

            tvOperation.text = operation
            tvRes.text = "$operand"
        }
        else{
            operand = 0
            operator = '0'
            tvOperation.text = operation
            tvRes.text = "= $result"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun reset(){
        tvOperation.text = ""
        tvRes.text = "0"
        operand = 0
        operator = '0'
        operation = ""
    }
}