package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NovoTopicoForm(
    @field:NotEmpty(message = "Valor não pode ser nulo ou vazio.")
    val titulo: String,
    @field:NotEmpty(message = "Valor não pode ser nulo ou vazio.")
    val mensagem: String,
    @field:NotNull(message = "Valor não pode ser nulo ou vazio.")
    val idCurso: Long,
    @field:NotNull(message = "Valor não pode ser nulo ou vazio.")
    val idAutor: Long
)
