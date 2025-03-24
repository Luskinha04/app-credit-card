package com.lucas.mycreditcard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editCardNumber = findViewById<EditText>(R.id.editCardNumber)
        val editCardExpiry = findViewById<EditText>(R.id.editCardExpiry)

        // Máscara do número do cartão (1234 5678 9012 3456)
        editCardNumber.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return

                var input = s.toString().replace(" ", "")
                if (input.length > 16) input = input.substring(0, 16)

                val formatted = StringBuilder()
                for (i in input.indices) {
                    formatted.append(input[i])
                    if ((i + 1) % 4 == 0 && i != input.length - 1) {
                        formatted.append(" ")
                    }
                }

                isUpdating = true
                editCardNumber.setText(formatted.toString())
                editCardNumber.setSelection(formatted.length)
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Máscara da validade (MM/AA)
        editCardExpiry.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return

                var input = s.toString().replace("/", "")
                if (input.length > 4) input = input.substring(0, 4)

                val formatted = StringBuilder()
                for (i in input.indices) {
                    if (i == 2) formatted.append("/")
                    formatted.append(input[i])
                }

                isUpdating = true
                editCardExpiry.setText(formatted.toString())
                editCardExpiry.setSelection(formatted.length)
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
