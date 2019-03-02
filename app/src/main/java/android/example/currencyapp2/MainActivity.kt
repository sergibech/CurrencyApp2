package android.example.currencyapp2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.example.currencyapp2.databinding.ActivityMainBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
            setSwitch(isChecked)
        }
        binding.buttonMoneda.setOnClickListener {
            obrirLlista(1)
        }
        binding.buttonMoneda2.setOnClickListener {
            obrirLlista(2)
       }
        binding.buttonConvertir.setOnClickListener {
            calculWithHash()
        }
        binding.inverseButton.setOnClickListener() {
            inverse()
        }

    }

    private fun setSwitch(isChecked: Boolean) {
        val switch: Switch = binding.switch1
        if (switch.isChecked) {
            binding.ratioText.visibility = View.VISIBLE
        } else {
            binding.ratioText.setText("")
            binding.ratioText.visibility = View.GONE
        }

    }
    private fun obrirLlista(select: Int) {
        val intent = Intent (this, ListMonedesActivity::class.java)
        startActivityForResult(intent, select)
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

     private fun inverse() {
        val temp: String = binding.buttonMoneda.text.toString()
        binding.buttonMoneda.setText(binding.buttonMoneda2.text.toString())
        binding.buttonMoneda2.setText(temp)
        //val temp1: String = binding.textValor.text.toString()
        //binding.textValor.setText(binding.textValor2.text.toString())
        //binding.textValor2.setText(temp1)
    }

    private fun calculWithHash() {
        var canvi: Float = Constants.EURO_TO_DOLLAR
        var comissio: Float = 1f

        val euro_to_all: HashMap<String, Float> = hashMapOf("euro" to Constants.EURO_TO_EURO, "yen" to Constants.EURO_TO_YEN, "dollar" to Constants.EURO_TO_DOLLAR)
        val all_to_euro: HashMap<String, Float> = hashMapOf("euro" to Constants.EURO_TO_EURO, "yen" to Constants.YEN_TO_EURO, "dollar" to Constants.DOLLAR_TO_EURO)

        val moneda_origen: String = binding.buttonMoneda.text.toString()
        val moneda_desti: String = binding.buttonMoneda2.text.toString()

        var valor_inicial: String = binding.textValor.text.toString()
        var result: TextView = binding.textValor2
        
        if (valor_inicial == "") valor_inicial = "0"

        if (binding.textCommission.text.toString() != "") comissio = 1 - (binding.textCommission.text.toString().toFloat() / 100f)

        if (binding.ratioText.text.toString() != "") {
            var r = convertCustom(valor_inicial.toFloat(), comissio)
            result.text = r.toString()

        } else {
            canvi = (all_to_euro[moneda_origen]!!*euro_to_all[moneda_desti]!!) * comissio
            canvi*= valor_inicial.toFloat()
            var round_canvi = canvi.toBigDecimal().setScale(2, RoundingMode.UP).toFloat()
            result.text = round_canvi.toString()
        }
    }

    private fun convertCustom(moneda: Float, comissio: Float): Float {
        var ratio = (moneda*binding.ratioText.text.toString().toFloat()*comissio).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
        return ratio
    }
}

class Constants {
    companion object {
        const val EURO_TO_DOLLAR = 1.14f
        const val EURO_TO_YEN = 127.28f
        const val DOLLAR_TO_YEN = 111.96f
        const val DOLLAR_TO_EURO = 0.88f
        const val YEN_TO_EURO = 0.0079f
        const val YEN_TO_DOLLAR = 0.0089f
        const val EURO_TO_EURO = 1f
    }
}
