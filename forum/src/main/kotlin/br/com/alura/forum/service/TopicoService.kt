package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper // Instance of TopicoViewMapper interface.
) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter({ t ->            //Criado metodo buscar por ID
            t.id == id
        }).findFirst().get()
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(@RequestBody form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        val topico = topicos.stream().filter({ t ->
            t.id == form.id
        }).findFirst().get()
        topicos = topicos.minus(topico).plus(
            Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                dataCriacao = topico.dataCriacao,
                curso = topico.curso,
                autor = topico.autor,
                status = topico.status,
                respostas = topico.respostas
            )
        )
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter({ t ->
            t.id == id
        }).findFirst().get()
        topicos = topicos.minus(topico)
    }
}
