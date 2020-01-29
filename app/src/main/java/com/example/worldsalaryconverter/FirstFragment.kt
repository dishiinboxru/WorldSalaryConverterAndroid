package com.example.worldsalaryconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
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

        //populating autocomplete fields
        // Get a reference to the AutoCompleteTextView in the layout
        val textViewOfferCurrency = view.findViewById<AutoCompleteTextView>(R.id.Currency_offer_autocomplete)
        val textViewTargetCurrency = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete)
    // Get the string array
        val currencies: List<String> = resources.getStringArray(R.array.currencies_array).toList()
    // Create the adapter and set it to the AutoCompleteTextView
//       val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, currencies)


        ArrayAdapter<String>(context , android.R.layout.simple_list_item_1, currencies).also { adapter ->
            textViewOfferCurrency.setAdapter(adapter)
        }
        //TODO - obvious duplication, don't know how to optimise yet
        ArrayAdapter<String>(context , android.R.layout.simple_list_item_1, currencies).also { adapter ->
            textViewTargetCurrency.setAdapter(adapter)
        }

        //setting action upon Convert Please button
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
           // convertMe(view)

            val targetCurrencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_target_autocomplete).text.toString()

            val inputCurrencyValue = view.findViewById<AutoCompleteTextView>(R.id.Currency_offer_autocomplete).text.toString()

            val salaryValue = view.findViewById<EditText>(R.id.Salary_value_input).text.toString().toDouble()

            //TODO - add proper requst via API for exchange rate
            val exchangeRate :Double = fetchExchangeRate( inputCurrencyValue, targetCurrencyValue)


            var paymentPeriodMode : Double = 1.0 //default to monthly
            var id = payroll_period.checkedRadioButtonId

            val paymentPeriodButton = view.findViewById<RadioButton>(id).text

            if (paymentPeriodButton.equals("hourly")){
                paymentPeriodMode = 160.0
            }
            if (paymentPeriodButton.equals("monthly")){
                paymentPeriodMode = 1.0
            }
            if (paymentPeriodButton.equals("yearly")) {
                paymentPeriodMode = 0.08333
            }

            var targetSalary =(salaryValue * paymentPeriodMode * exchangeRate).toString() // outcommented for debugging

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

    fun fetchExchangeRate( inputCurrencyValue : String , targetCurrencyValue : String) : Double{
    var exchangeRate : Double = 0.0

        var firstCurrency :Double
        var secondCurrency : Double

        var inputCurrencySymbol = inputCurrencyValue.toLowerCase()
        var targetCurrencySymbol = targetCurrencyValue.toLowerCase()

       //TODO - replace if-s with API requests
        //from rubles
        if (inputCurrencySymbol.equals("rub") && targetCurrencySymbol.equals("usd")){
            exchangeRate = 0.016
        }
        if (inputCurrencySymbol.equals("rub") && targetCurrencySymbol.equals("eur")){
            exchangeRate = 0.015
        }
        if (inputCurrencySymbol.equals("rub") && targetCurrencySymbol.equals("gbp")){
            exchangeRate = 0.012
        }
        //to rubles
        if (inputCurrencySymbol.equals("usd") && targetCurrencySymbol.equals("rub")){
            exchangeRate = 62.7
        }
        if (inputCurrencySymbol.equals("eur") && targetCurrencySymbol.equals("rub")){
            exchangeRate = 69.0
        }
        if (inputCurrencySymbol.equals("gbp") && targetCurrencySymbol.equals("rub")){
            exchangeRate = 81.5
        }


        //here we are supposed to request API
//        val url = "https://api.exchangeratesapi.io/latest?base=USD"
//
//        val response = URL(url).openStream().bufferedReader().toString()

        //snippets to connect
//            var response = try {
//                URL("http://google.co.uk")
//                    .openStream()
//                    .bufferedReader()
//                    .use { it.readText() }

        //snippet to work with JSON
//            val obj : JSONObject = response.jsonObject
//                print(obj["Target currency"])

        //but yet we just hardcode stuff for USD to RUB
        //exchangeRate = 60.0

        return exchangeRate
    }
}

