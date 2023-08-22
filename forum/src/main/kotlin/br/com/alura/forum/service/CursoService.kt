package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private var cursos: List<Curso>) {

    init {
        val curso = Curso(
            id = 1,
            categoria = "Programação",
            nome = "Kotlin"
        )

        cursos = Arrays.asList(curso)
    }


    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter({ c ->            //Criado metodo buscar por ID
            c.id == id
        }).findFirst().get()
    }

}