package com.example.worldsalaryconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        //setting action upon Convert Please button
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            convertMe(view)
        }
    }

        fun convertMe (view: View){
            //getting input data
            val currencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete).text

            val salaryValue = view.findViewById<EditText>(R.id.Salary_value_input).text

            // populating target table

            view.findViewById<EditText>(R.id.target_currency_output).text = currencyValue
            view.findViewById<EditText>(R.id.target_hourly_salary).text=salaryValue
        }

        //example from 2nd app
//
//        view.findViewById<Button>(R.id.count_button).setOnClickListener{
//            countMe(view)
//        }
//    }
//
//    fun countMe (view: View){
//        val showCountTextView = view.findViewById<TextView>(R.id.textView_count)
//        val countString = showCountTextView.text.toString()
//        var count: Int = Integer.parseInt(countString)
//        count++
//        showCountTextView.text = count.toString()
//    }
    }

