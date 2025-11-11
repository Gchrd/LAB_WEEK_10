package com.example.lab_week_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class FirstFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[TotalViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViewModel()
    }

    private fun updateText(total: Int) {
        view?.findViewById<TextView>(R.id.text_total)?.text = "Total: $total"
    }

    private fun prepareViewModel(){
        viewModel.total.observe(viewLifecycleOwner) { total ->
            updateText(total)
        }

        view?.findViewById<Button>(R.id.button_increment)?.setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }
}
