package com.example.worldsalaryconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

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
           // convertMe(view)

            val currencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete).text

            val salaryValue = view.findViewById<EditText>(R.id.Salary_value_input).text.toString().toDouble()

            //TODO - add proper requst via API for exchange rate
            val exchangeRate = 2

            // yet hardcode, later implement a switch
            var paymentPeriodMode : Double = 1.0
            var id: Int = payroll_period.checkedRadioButtonId
           // val radio: RadioButton = findViewById<RadioButton>(id) //TODO - 14-01 - somehow get his selection out. Or maybe implement spinner instead
            if (id == 0){
                 paymentPeriodMode = 800.0
            }
            if (id == 1){
                paymentPeriodMode = 1.0
            }
            if (id == 2) {
                 paymentPeriodMode = 0.08333
            }

            var targetSalary =(salaryValue * paymentPeriodMode * exchangeRate).toString()


            //navigating to second page
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(targetSalary)
            findNavController().navigate(action)
        }
    }

        fun convertMe (view: View){
            //getting input data
            val currencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete).text

            val salaryValue = view.findViewById<EditText>(R.id.Salary_value_input).text


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

