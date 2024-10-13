package com.example.a77_contextmenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var buttonRandomNumberBTN: Button

    private lateinit var textET: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        buttonRandomNumberBTN = findViewById(R.id.buttonRandomNumberBTN)

        buttonRandomNumberBTN.setOnClickListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_color_quality -> {
                when (textET.text.toString().toInt()) {
                    1 -> textET.setBackgroundColor(Color.parseColor("#FF5722"))
                    2 -> textET.setBackgroundColor(Color.YELLOW)
                    3 -> textET.setBackgroundColor(Color.GREEN)
                    4 -> textET.setBackgroundColor(Color.BLUE)
                    5 -> textET.setBackgroundColor(Color.RED)
                    in 1..10 -> textET.setBackgroundColor(Color.RED)
                    in 11..20 -> textET.setBackgroundColor(Color.parseColor("#FF5722"))
                    in 21..30 -> textET.setBackgroundColor(Color.YELLOW)
                    in 31..40 -> textET.setBackgroundColor(Color.GREEN)
                    in 41..50 -> textET.setBackgroundColor(Color.BLUE)
                }
                Toast.makeText(this, "Меняем цвет", Toast.LENGTH_LONG).show()
            }

            R.id.menu_exit -> {
                finish()
                Toast.makeText(this, "Выход из приложения", Toast.LENGTH_LONG).show()
            }

            else -> return super.onContextItemSelected(item)
        }

        return true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.buttonRandomNumberBTN) {
            val numbers = (1..50)
            textET.setText(numbers.random().toString())
        }
    }
}