package com.example.b24

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.b24.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btndate.setOnClickListener {
            selectDate()

        }
    }

    private fun selectDate() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        Log.wtf("tienpepe", "$day/${month + 1}/$year")

        val db = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, syear, smonth, sdayOfMonth ->
                val dateBith = "$sdayOfMonth/${smonth + 1}/$syear"
                binding.textView2.text = dateBith

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val born = sdf.parse(dateBith)
                born?.let {
                    val bornMinus = born.time/60000


                    val cuffDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    cuffDate?.let {
                        val currdateInMinute = cuffDate.time /60000

                        val diff = currdateInMinute - bornMinus

                        binding.txtAgeInMinute.text = diff.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        db.datePicker.maxDate = System.currentTimeMillis()-86400000
        db.show()

    }


}