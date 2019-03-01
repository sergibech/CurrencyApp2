package android.example.currencyapp2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_list_monedes.*

class ListMonedesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_monedes)
        initLlista(this)
        val llista :ListView = findViewById(R.id.llista_monedes)
        llista.setOnItemClickListener { parent, view, position, id ->
            escollirMoneda(llista.getItemAtPosition(position).toString())
        }
    }

    private fun escollirMoneda(moneda: String) {
        val intent= Intent()
        intent.putExtra("moneda", moneda)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun initLlista(it: ListMonedesActivity ) {
        var llista_monedes: ListView
        llista_monedes = findViewById<ListView>(R.id.llista_monedes)
        val monedes = listOf("euro", "dolar", "yen")
        val arrayAdapter = ArrayAdapter(it, android.R.layout.simple_list_item_1, monedes)
        llista_monedes.setAdapter(arrayAdapter)
    }

}
