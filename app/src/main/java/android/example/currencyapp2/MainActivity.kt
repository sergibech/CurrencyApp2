package android.example.currencyapp2

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
            obrirLlista(it)
        }
        binding.buttonMoneda2.setOnClickListener {
            obrirLlista(it)
       }

        canviarTextBoto1()

        binding.buttonConvertir.setOnClickListener {
            calcula()
        }

        //binding.inverseButton.setOnClickListener() {
            //inverse()
        //}

    }

    fun inverse() {
        //val temp: String = binding.buttonMoneda.text.toString()
       // binding.buttonMoneda.setText(binding.buttonMoneda2.text.toString())
       // binding.buttonMoneda2.setText(temp)
    }

    fun obrirLlista(it: View) {
        val intent = Intent(this, ListMonedesActivity::class.java)
        startActivity(intent)
    }

    fun canviarTextBoto1(){
        val intent = intent
        val textBoto1: Button = binding.buttonMoneda
        textBoto1.setText(intent.getStringExtra("moneda"))

    }

    fun calcula() {
        val m: String = binding.buttonMoneda.text.toString()
        val n: String = binding.buttonMoneda2.text.toString()
        val r: TextView = binding.textValor2
        val q: Int = binding.textValor.text.toString().toInt()
        if (m == "euro" && n == "dollar"){
            val valor = q *2
            r.setText(valor.toString())
        }
    }

}
