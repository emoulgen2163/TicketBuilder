package com.mycompany.ticketbuilder

import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mycompany.ticketbuilder.databinding.ActivityMainBinding
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var count = 0
    var price = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ticketType.setOnCheckedChangeListener { group, checkedId ->
            binding.purchase.apply {
                isEnabled = true
                setBackgroundColor("#dfe26f".toColorInt())
            }

            price = when(checkedId){
                R.id.standard -> binding.standardPrice.text.toString().toInt()
                R.id.vip -> binding.vipPrice.text.toString().toInt()
                R.id.backstage -> binding.backstagePrice.text.toString().toInt()
                else -> 0
            }

            updateTotal(binding.totalPrice)
        }

        binding.increment.setOnClickListener {
            count++
            binding.quantity.text = count.toString()
            updateTotal(binding.totalPrice)
        }

        binding.decrement.setOnClickListener {
            if (count > 0) count--
            binding.quantity.text = count.toString()
            updateTotal(binding.totalPrice)
        }

    }

    private fun updateTotal(totalPrice: TextView) {
        val total = count * price
        totalPrice.text = "$total"
    }
}