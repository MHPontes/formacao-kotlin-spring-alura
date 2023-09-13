package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity // Sinalizando que e uma tabela no banco de dados JPA
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne  //Um curso pode ter varios topicos, mas um topico pertence ao um unico curso
    val curso: Curso,
    @ManyToOne
    val autor: Usuario,
    @Enumerated(value = EnumType.STRING)   //para salvar enums no banco
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico") // Um topico tem varias respostas
    val respostas: List<Resposta> = ArrayList()
)
