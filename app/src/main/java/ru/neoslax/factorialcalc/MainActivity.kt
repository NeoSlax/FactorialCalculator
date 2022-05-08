package ru.neoslax.factorialcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.neoslax.factorialcalc.databinding.ActivityMainBinding
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.btnCalculate.setOnClickListener {
            viewModel.calculate(binding.etNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            binding.btnCalculate.isEnabled = true
            binding.progressBar.visibility = View.GONE
            when (state) {
                is State.Error -> {
                    Toast.makeText(this, "You did not entered number", Toast.LENGTH_SHORT).show()
                }
                is State.Progress -> {
                    binding.btnCalculate.isEnabled = false
                    binding.progressBar.visibility = View.VISIBLE
                }
                is State.Result -> {
                    binding.tvResult.text = state.result
                }
            }
        }
    }
}