package com.example.ifly

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.content_form.*
import java.util.*


class FormActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datepicker.setOnClickListener{
            val picker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
            date_time.setText("" + mDay + "/" + mMonth + "/" + mYear)}, day, month, year)
            picker.show()
        }

        fab.setOnClickListener {
            val replyIntent = Intent()
            val date= date_time.text.toString()
            val departure = departure.text.toString()
            val arrival = arrival.text.toString()
            val code = code.text.toString()

            replyIntent.putExtra("DateTime", date)
            replyIntent.putExtra("Departure", departure)
            replyIntent.putExtra("Arrival", arrival)
            replyIntent.putExtra("Code", code)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

}
