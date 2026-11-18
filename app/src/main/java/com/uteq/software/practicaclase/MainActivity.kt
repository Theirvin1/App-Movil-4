package com.uteq.software.practicaclase

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.uteq.software.practicaclase.data.SupabaseManager
import com.uteq.software.practicaclase.models.Alumno
import com.uteq.software.practicaclase.utils.SupabaseErrorHandler
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var txtAlumnos: EditText
    private lateinit var progressMaterias: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtAlumnos = findViewById(R.id.txtAlumnos)
        progressMaterias = findViewById(R.id.progressMaterias)
        txtAlumnos.keyListener = null // viewOnly: no se puede modificar

        consultarAlumnos()
    }

    private fun consultarAlumnos() {
        lifecycleScope.launch {
            progressMaterias.visibility = View.VISIBLE
            try {
                val alumnos = SupabaseManager.client
                    .from("alumnos")
                    .select {
                        order("nombres", Order.ASCENDING)
                    }
                    .decodeList<Alumno>()

                txtAlumnos.setText(if (alumnos.isEmpty()) {
                    "No se encontraron alumnos."
                } else {
                    alumnos.joinToString("\n\n") { a ->
                        "Nombre: ${a.nombres}\n" +
                            "Paralelo: ${a.paralelo}\n" +
                            "Correo: ${a.correo ?: "Sin registrar"}\n" +
                            "Teléfono: ${a.telefono ?: "Sin registrar"}"
                    }
                })
            } catch (e: Exception) {
                SupabaseErrorHandler.show(this@MainActivity, e)
            } finally {
                progressMaterias.visibility = View.GONE
            }
        }
    }
}
