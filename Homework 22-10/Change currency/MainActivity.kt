package com.example.learn1

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spn1: Spinner = findViewById(R.id.spn_currency1)
        val spn2: Spinner = findViewById(R.id.spn_currency2)
        val etCurrency1: EditText = findViewById(R.id.et_currency1)
        val etCurrency2: EditText = findViewById(R.id.et_currency2)

        val items = arrayListOf(
            "United States - Dollar", "United Kingdom - Pound", "Europe - Euro",
            "Japan - Yen", "China - Yuan", "Korean - Won", "Vietnam - Dong"
        )

        val convertToUSDs = arrayListOf(1.0, 1.2, 0.93, 152.0, 7.12, 1.388, 25355.0)
        var convert1 = 1.0
        var convert2 = 1.0
        var value1 = 0.0
        var value2 = 0.0

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn1.adapter = adapter
        spn2.adapter = adapter

        spn1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convert1 = convertToUSDs[position]
                etCurrency2.setText((value1 / convert1 * convert2).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spn2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convert2 = convertToUSDs[position]
                etCurrency1.setText((value2 / convert2 * convert1).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Thêm TextWatcher cho cả hai EditTexts và quản lý focus cho font chữ
        val currencyTextWatcher1 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (etCurrency1.hasFocus()) {
                    if (!s.isNullOrEmpty()) {
                        try {
                            value1 = s.toString().toDouble()
                            etCurrency2.setText((value1 / convert1 * convert2).toString())
                        } catch (e: NumberFormatException) {
                            etCurrency2.setText("Error")
                        }
                    } else {
                        etCurrency2.setText("0")
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        val currencyTextWatcher2 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (etCurrency2.hasFocus()) {
                    if (!s.isNullOrEmpty()) {
                        try {
                            value2 = s.toString().toDouble()
                            etCurrency1.setText((value2 / convert2 * convert1).toString())
                        } catch (e: NumberFormatException) {
                            etCurrency1.setText("Error")
                        }
                    } else {
                        etCurrency1.setText("0")
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        etCurrency1.addTextChangedListener(currencyTextWatcher1)
        etCurrency2.addTextChangedListener(currencyTextWatcher2)

        // Đổi font chữ khi focus thay đổi
        etCurrency1.setOnFocusChangeListener { _, hasFocus ->
            etCurrency1.setTypeface(etCurrency1.typeface, if (hasFocus) Typeface.BOLD else Typeface.NORMAL)
        }

        etCurrency2.setOnFocusChangeListener { _, hasFocus ->
            etCurrency2.setTypeface(etCurrency2.typeface, if (hasFocus) Typeface.BOLD else Typeface.NORMAL)
        }
    }
}
