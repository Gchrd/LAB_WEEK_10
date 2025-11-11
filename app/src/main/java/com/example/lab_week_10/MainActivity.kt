package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.database.TotalDatabase
import com.example.lab_week_10.repositories.TotalRepository
import com.example.lab_week_10.viewmodels.TotalViewModel
import com.example.lab_week_10.viewmodels.TotalViewModelFactory

class MainActivity : AppCompatActivity() {

    // Create an instance of the TotalViewModel
    // by lazy is used to create the ViewModel only when it's needed
    private val viewModel by lazy {
        val database = TotalDatabase.getDatabase(this)
        val repository = TotalRepository(database.totalDao())
        val factory = TotalViewModelFactory(repository)
        ViewModelProvider(this, factory)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare the ViewModel
        prepareViewModel()
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    private fun prepareViewModel() {
        // Observe the LiveData object
        viewModel.total.observe(this) {
            // Whenever the value of the LiveData object changes
            // the updateText() is called, with the new value as the parameter
            updateText(it)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}
