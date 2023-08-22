package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
            id = 1,
            nome = "Gabriel Dragone",
            email = "gabriel@gmail.com"
        )

        usuarios = Arrays.asList(usuario)
    }


    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter({ u ->            //Criado metodo buscar por ID
            u.id == id
        }).findFirst().get()
    }

}




