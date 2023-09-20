package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors

@Service
class TopicoService(
//    private var topicos: List<Topico> = ArrayList(),   Para utilizar topicos sem banco de dados
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper, // Instance of TopicoViewMapper interface.
    private val notFoundMessage : String = "Topico não encontrado."
) {


    /*  Abaixo são formas as services sem o banco de dados, utlizando uma Arraylist e tendo os valores das entidades iniciados com init.


//    fun listar(): List<TopicoView> {
//        return topicos.stream().map { t ->
//            topicoViewMapper.map(t)
//        }.collect(Collectors.toList())
//    }
//
//    fun buscarPorId(id: Long): TopicoView {
//        val topico = topicos.stream().filter({ t ->            //Criado metodo buscar por ID
//            t.id == id
//        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
//        return topicoViewMapper.map(topico)
//    }
//
//    fun cadastrar(@RequestBody form: NovoTopicoForm): TopicoView {
//        val topico = topicoFormMapper.map(form)
//        topico.id = topicos.size.toLong() + 1
//        topicos = topicos.plus(topico)
//        return topicoViewMapper.map(topico)
//    }
//
//    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
//        val topico = topicos.stream().filter({ t ->
//            t.id == form.id
//        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
//        val topicoAtualizado = Topico(
//            id = form.id,
//            titulo = form.titulo,
//            mensagem = form.mensagem,
//            dataCriacao = topico.dataCriacao,
//            curso = topico.curso,
//            autor = topico.autor,
//            status = topico.status,
//            respostas = topico.respostas
//        )
//        topicos = topicos.minus(topico).plus(topicoAtualizado)
//        return topicoViewMapper.map(topicoAtualizado)
//    }
//
//    fun deletar(id: Long) {
//        val topico = topicos.stream().filter({ t ->
//            t.id == id
//        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
//        topicos = topicos.minus(topico)
//    }
}
     */

    fun listar(nomeCurso: String?): List<TopicoView> {
        val topicos = if (nomeCurso == null) {          //Se nomeCurso, não for passado no filtro, retorna a lista de todos os cursos
            repository.findAll()
        } else {
            repository.findByCursoNome(nomeCurso)      //Se for passado um nomeCurso, como filtro.
        }
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

}

