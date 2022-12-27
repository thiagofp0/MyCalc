package com.thiago.mycalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thiago.mycalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.zero.setOnClickListener { addExpression("0", true) }
        binding.um.setOnClickListener { addExpression("1", true) }
        binding.dois.setOnClickListener { addExpression("2", true) }
        binding.tres.setOnClickListener { addExpression("3", true) }
        binding.quatro.setOnClickListener { addExpression("4", true) }
        binding.cinco.setOnClickListener { addExpression("5", true) }
        binding.seis.setOnClickListener { addExpression("6", true) }
        binding.sete.setOnClickListener { addExpression("7", true) }
        binding.oito.setOnClickListener { addExpression("8", true) }
        binding.nove.setOnClickListener { addExpression("9", true) }

        binding.adicao.setOnClickListener { addExpression("+", false) }
        binding.subtracao.setOnClickListener { addExpression("-", false) }
        binding.multiplicacao.setOnClickListener { addExpression("*", false) }
        binding.divisao.setOnClickListener { addExpression("/", false) }

        binding.limpar.setOnClickListener {
            binding.expressao.text = ""
            binding.resultado.text = ""
        }

        binding.ponto.setOnClickListener { addExpression(".", true) }

        binding.backsapce.setOnClickListener {
            val string = binding.expressao.text.toString()
            if (string.isNotEmpty()) {
                binding.expressao.text = string.substring(0, string.length - 1)
            }
            binding.resultado.text = ""
        }

        binding.igual.setOnClickListener {
            try {
                var text = binding.expressao.text.toString();
                val expression = ExpressionBuilder(text).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.resultado.text = longResult.toString()
                } else {
                    binding.resultado.text = result.toString()
                }
            } catch (e: Exception) {
                println("Message: " + e.message)
            }
        }
    }

    fun addExpression(string: String, canClear: Boolean) {
        if(binding.resultado.text.isNotEmpty()) {
            binding.expressao.text = "";
        }
        if (canClear) {
            binding.resultado.text = "";
            binding.expressao.append(string);
        } else {
            binding.expressao.append(binding.resultado.text);
            binding.expressao.append(string);
            binding.resultado.text = "";
        }
    }
}