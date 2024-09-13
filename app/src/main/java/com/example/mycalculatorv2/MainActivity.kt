package com.example.mycalculatorv2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculatorv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dotCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberButtons = listOf<Button>(binding.oneBtn, binding.twoBtn, binding.threeBtn, binding.fourBtn,
            binding.fiveBtn, binding.sixBtn, binding.sevenBtn, binding.eightBtn, binding.nineBtn,
            binding.zeroBtn, binding.dotBtn)

        val operationButtons = listOf<Button>(binding.divideBtn, binding.multiplyBtn, binding.minusBtn,
            binding.plusBtn)


        for (button in numberButtons)
        {
            button.setOnClickListener {
                val inp = (button as Button).text

                if ((inp == "." && dotCnt < 1) || inp != ".") {
                    if (inp == ".") ++dotCnt
                    binding.tVInput.text = binding.tVInput.text.toString() + inp
                }
            }
        }

        for (button in operationButtons)
        {
            button.setOnClickListener {
                dotCnt = 0
                val inp = (button as Button).text
                binding.tVInput.text = binding.tVInput.text.toString() + inp
            }
        }


        binding.acBtn.setOnClickListener {
            binding.tVInput.text = ""
            binding.tVResult.text = ""
        }

        binding.backspaceBtn.setOnClickListener {
            var inp = binding.tVInput.text.toString()
            val len = inp.length - 1

            binding.tVInput.text = if (len > 0) inp.removeRange(len, len + 1) else ""
        }

        binding.equalsBtn.setOnClickListener {
            try {
                val tokens = Tokenizer(binding.tVInput.text.toString())
                val parser = MyParser(tokens.tokenize())
                val tree = parser.parse()

                if (tree == null) binding.tVResult.text = "Invalid Input."
                else binding.tVResult.text = tree.evaluate().toString()
            }
            catch (e: Exception) {
                binding.tVResult.text = "Error ${e.message}"
            }
        }
    }
}