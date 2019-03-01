package android.example.currencyapp2

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.example.currencyapp2.databinding.ActivityMainBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonMoneda.setOnClickListener {
            obrirLlista(1)
        }
        binding.buttonMoneda2.setOnClickListener {
            obrirLlista(2)
       }
        binding.buttonConvertir.setOnClickListener {
            calcula()
        }
        binding.inverseButton.setOnClickListener() {
            inverse()
        }
    }

     private fun inverse() {
        val temp: String = binding.buttonMoneda.text.toString()
        binding.buttonMoneda.setText(binding.buttonMoneda2.text.toString())
        binding.buttonMoneda2.setText(temp)
        val temp1: String = binding.textValor.text.toString()
        binding.textValor.setText(binding.textValor2.text.toString())
        binding.textValor2.setText(temp1)
    }

     private fun obrirLlista(select: Int) {
        val intent = Intent (this, ListMonedesActivity::class.java)
        startActivityForResult(intent, select)
    }

    private fun calcula() {
        val m: String = binding.buttonMoneda.text.toString()
        val n: String = binding.buttonMoneda2.text.toString()
        val r: TextView = binding.textValor2
        var q: String = binding.textValor.text.toString()
        if (q == "") q = "0"
        if (m == "euro" && n == "dollar"){
            r.text = convertEuroToDolar(q.toFloat()).toString()
        }
        else if (m == "euro" && n == "yen") {
            r.text = convertEuroToYen(q.toFloat()).toString()
        }
        else if (m == "dollar" && n == "yen") {
            r.text = convertDollarToYen(q.toFloat()).toString()
        }
        else if (m == "dollar" && n == "euro") {
            r.text = convertDollarToEuro(q.toFloat()).toString()
        }
        else if (m == "yen" && n == "euro") {
            r.text = convertYenToEuro(q.toFloat()).toString()
        }
        else if (m == "yen" && n == "dollar") {
            r.text = convertYenToDollar(q.toFloat()).toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){

                binding.buttonMoneda.text = data?.getStringExtra("moneda")
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                binding.buttonMoneda2.text = data?.getStringExtra("moneda")

            }
        }
    }

    private fun convertDollarToEuro(dollar: Float): Float {
        var euro = (dollar*Constants.DOLLAR_TO_EURO).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return euro
    }
    private fun convertDollarToYen(dollar: Float): Float {
        val yen = (dollar*Constants.DOLLAR_TO_YEN).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return yen
    }

    private fun convertEuroToDolar(euro: Float): Float{
        val dolar = (euro*Constants.EURO_TO_DOLLAR).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return dolar
    }
    private fun convertEuroToYen(euro: Float): Float{
        val  yen = (euro*Constants.EURO_TO_YEN).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return yen
    }
    private fun convertYenToEuro(yen: Float): Float {
        val euro = (yen*Constants.YEN_TO_EURO).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return euro
    }
    private fun convertYenToDollar(yen: Float): Float {
        val dollar = (yen*Constants.YEN_TO_DOLLAR).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return dollar
    }

    class Constants {
        companion object {
            const val EURO_TO_DOLLAR = 1.14f
            const val EURO_TO_YEN = 127.28f
            const val DOLLAR_TO_YEN = 111.96f
            const val DOLLAR_TO_EURO = 0.88f
            const val YEN_TO_EURO = 0.0079f
            const val YEN_TO_DOLLAR = 0.0089f
        }
    }

}
