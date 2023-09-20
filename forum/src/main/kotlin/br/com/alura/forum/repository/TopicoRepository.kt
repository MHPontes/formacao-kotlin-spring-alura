package br.com.alura.forum.repository

import br.com.alura.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>      //Dando um select no banco, quando o nome curso for passado no filtro listar. Alterando retorno para Page e não mais List, devido a paginacao
}