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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
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

    fun inverse() {
        val temp: String = binding.buttonMoneda.text.toString()
        binding.buttonMoneda.setText(binding.buttonMoneda2.text.toString())
        binding.buttonMoneda2.setText(temp)
        val temp1: String = binding.textValor.text.toString()
        binding.textValor.setText(binding.textValor2.text.toString())
        binding.textValor2.setText(temp1)
    }

    fun obrirLlista(select: Int) {
        val intent = Intent (this, ListMonedesActivity::class.java)
        startActivityForResult(intent, select)
    }

    fun calcula() {
        val m: String = binding.buttonMoneda.text.toString()
        val n: String = binding.buttonMoneda2.text.toString()
        val r: TextView = binding.textValor2
        val q: Double = binding.textValor.text.toString().toDouble()
        if (m == "euro" && n == "dollar"){
            r.text = convertEuroToDolar(q).toString()
        }
    }

    fun convertEuroToDolar(euro: Double): Double{
        val dolar = euro*1.14
        return dolar
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
}
