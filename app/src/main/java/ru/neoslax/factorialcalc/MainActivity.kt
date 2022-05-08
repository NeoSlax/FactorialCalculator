package ru.neoslax.factorialcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.neoslax.factorialcalc.databinding.ActivityMainBinding

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
            if (state.isError) {
                Toast.makeText(this, "You did not entered number", Toast.LENGTH_SHORT).show()
            }
            if (state.isInProgress) {
                binding.btnCalculate.isEnabled = false
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.btnCalculate.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
            binding.tvResult.text = state.factorial
        }
    }
}