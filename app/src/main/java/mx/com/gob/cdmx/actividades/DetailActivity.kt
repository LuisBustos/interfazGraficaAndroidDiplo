package mx.com.gob.cdmx.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        buscaActor.text = intent.getStringExtra("nameActor")
        buscaGenero.text = intent.getStringExtra("genre")
        buscaAio.text = intent.getStringExtra("year")
        buscaDuracion.text = intent.getStringExtra("hours")
        buscaSugerencias.text = intent.getStringExtra("numeroSugerencias")

    }
}