package br.com.alura.forum.mapper

interface Mapper<T, U> {            //Sera usada por todos os outros mappers

    fun map(t: T) : U

}
