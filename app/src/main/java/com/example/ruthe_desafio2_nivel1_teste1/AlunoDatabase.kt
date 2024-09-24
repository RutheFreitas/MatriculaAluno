package com.example.ruthe_desafio2_nivel1_teste1

object AlunoDatabase {
    private val alunos = mutableListOf<Aluno>()

    fun adicionar(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun pesquisar(nome: String, turma: String): List<Aluno> {
        return alunos.filter { it.nome.equals(nome, ignoreCase = true) && it.turma.equals(turma, ignoreCase = true) }
    }

    fun registros(): String {
        return "${alunos.size.toString()} ${if (alunos.size == 1) " registro." else " registros."}"
    }
}