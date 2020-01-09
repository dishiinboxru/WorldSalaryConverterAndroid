package com.example.worldsalaryconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            var currencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete).text.toString()

            val salaryValue = view.findViewById<EditText>(R.id.Salary_value_input).text.toString()
////
            currencyValue.plus(salaryValue)
//
//            //Bundle attempt - failed
//            savedInstanceState?.putInt("salary" , salaryValue)
//            savedInstanceState?.putString("currency" , currencyValue)

            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currencyValue)
            findNavController().navigate(action)
        }
    }
}
