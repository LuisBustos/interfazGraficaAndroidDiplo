package mx.com.gob.cdmx.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var movieGenres:String = ""
    // var ratings:ArrayList<String> = m()
    //metodo mutable List Of es para generar un list generico y después se pone el tipo cuando se compila.
    //
   //var ratings:List<String> = mutableListOf() // no funciona add ni remove
    var hours: MutableList<String> = mutableListOf()
///si la lista va tener cambio en el transcurso de la aplicación, es bueno usar mutableList
    //si es constante el tipo del list, entonces arraylist
    //Con mutable LIst como objeto si puede entrar a sus métodos.
    // Si declaramos un objeto de List pero implementando los mutableListOf no se pueden acceder a los
    //metodos de list
    //Cuando se crea un objeto es como si pusieras un new, pero hay que especificas la implementación que se hará
    var onCheckedChangeListenerRating: CompoundButton.OnCheckedChangeListener =CompoundButton.OnCheckedChangeListener {
        buttonView, _ ->
        setRating(buttonView)
    }

    var itemYears:Array<String> = arrayOf("1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001")

   // var itemYearRes = resources.getStringArray(R.array.years)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            movieGenres = when(checkedId){
                    R.id.radioButton->{
                            "Terror"
                    }
                    R.id.radioButton2->{
                        "Ciencia Ficción"
                    }
                    R.id.radioButton3->{
                        "Romance"
                    }
                    else ->{
                        "Otros"
                    }
                }
        })

        checkBox.setOnCheckedChangeListener (onCheckedChangeListenerRating)
        checkBox2.setOnCheckedChangeListener (onCheckedChangeListenerRating)
        checkBox3.setOnCheckedChangeListener (onCheckedChangeListenerRating)
        //Al momento de construir el adaptador hay que poner que tipo de elemento trabajará
        val yearAdapter = ArrayAdapter<String>(baseContext,android.R.layout.simple_spinner_item,itemYears)
        //FLecha que se vera en el spinner
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = yearAdapter

        var  yearSelected : String = ""

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                yearSelected= itemYears[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                yearSelected= ""
            }

        }
        //agregando evento al momento de que el usuario llena el cuadro de texto
        textInput.editText?.addTextChangedListener(
            object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                //caracter por caracter
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s.let { text ->
                        if(text?.length!! > textInput.counterMaxLength)
                            textInput.error = "Los carácteres llegaron a su limite"
                        else{
                            textInput.error = ""
                           textInput.isErrorEnabled = false
                        }

                    }
                    /*if(s?.length!! > textInput.counterMaxLength)
                        textInput.error = "Los carácteres llegaron a su limite"
                    else
                        textInput.error = ""*/
                }
                //Todo el contenido del textInput
                override fun afterTextChanged(s: Editable?) {
                    /*
                    val name = s?.toString()
                    if(name.isNullOrBlank())
                        textInput.error = "Cadena vacia o puro espacio"

                     */
                }
            }

            )

        button.setOnClickListener {
           /* Log.d("MOVIE GENRES",movieGenres.toString())

            Log.d("(YEARSELECTED)",yearSelected.toString())
            //EL textInputLayout puede tener nulos, por ello se debe de manejar como posible nulo
            val nameMovie = textInput.editText?.text.toString()
            if(nameMovie.isNotEmpty()) {
                Log.d("(MOVIEACTOR)", nameMovie)
                textInput.error = ""
            }
            else
                textInput.error = "No debe ser un valor vacio"
        */
            //Toast,Dialogo,SnapDiag
            Log.d("(RATINGSELECTED)",hours.toString())
            if(!textInput.isErrorEnabled){
                val detailIntent = Intent(this,DetailActivity::class.java).apply {
                    val detailBundle = Bundle().apply{
                        putString("nameActor",textInput.editText?.text.toString())
                        putString("genre",movieGenres)
                        putString("hours",hours.toString())
                        putString("year",yearSelected)
                        putString("numeroSugerencias",numeroSugerencia.text.toString())
                    }
                    putExtras(detailBundle)
                }
                startActivity(detailIntent)
            }else{
                Toast.makeText(baseContext,"Corrige el nombre de la pelicula",Toast.LENGTH_LONG).show()
            }

        }

        addSugerencia.setOnClickListener{
            setNumSugerencias(1)
        }


        removeSugerencia.setOnClickListener{
           setNumSugerencias(-1)
        }


    }

    private fun setNumSugerencias( i : Int){
        var numSugerencias : Int = numeroSugerencia.text.toString().toInt()
        if (!(numSugerencias == 0 && i< 0)){
            numSugerencias += i
            numeroSugerencia.text = numSugerencias.toString()
        }
    }


    private fun setRating(button: CompoundButton){
        val text = button.text.toString()
        if(hours.contains(text))
            hours.remove(text)
        else
            hours.add(text)
    }
}




























