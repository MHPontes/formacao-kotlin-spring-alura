package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(private var topicos: List<Topico>) {        //Criado lista para simular um banco de dados

    init {
        val topico1 = Topico(
            id = 1,
            titulo = "Primeiro Topicos",
            mensagem = "Primeira mensagem",
            curso = Curso(
                id = 2,
                nome = "Formacao Kotlin",
                categoria = "BackEnd"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Dragone",
                email = "gabriel@gmail.com"
            )
        )
        val topico2 = Topico(
            id = 2,
            titulo = "Primeiro Topicos",
            mensagem = "Primeira mensagem",
            curso = Curso(
                id = 2,
                nome = "Formacao Kotlin",
                categoria = "BackEnd"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Dragone",
                email = "gabriel@gmail.com"
            )
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Primeiro Topicos",
            mensagem = "Primeira mensagem",
            curso = Curso(
                id = 2,
                nome = "Formacao Kotlin",
                categoria = "BackEnd"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Dragone",
                email = "gabriel@gmail.com"
            )
        )

        topicos = Arrays.asList(topico1, topico2, topico3)

    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter({ t ->            //Criado metodo buscar por ID
            t.id == id
        }).findFirst().get()
    }
}
