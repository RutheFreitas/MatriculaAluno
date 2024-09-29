package com.example.ruthe_desafio2_nivel1_teste1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ruthe_desafio2_nivel1_teste1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRecord.setOnClickListener {
            gravarAluno()
        }

        binding.btnSearch.setOnClickListener {
            pesquisarAluno()
        }
    }

    private fun gravarAluno() {
        val nome = binding.etName.text.toString()
        val turma = binding.etClass.text.toString()
        val nota = binding.etNote.text.toString().toDoubleOrNull()

        if (nome.isNotEmpty() && turma.isNotEmpty() && nota != null) {
            if (nota > 0.0 && nota <= 10) {
                AlunoDatabase.adicionar(Aluno(nome, turma, nota))
                //binding.etNameSearch.text = binding.etName.text
                //binding.etClassSearch.text = binding.etClass.text

                //binding.etName.text.clear()
                //binding.etClass.text.clear()
                binding.etNote.text.clear()

                binding.etNameSearch.setText(nome)
                binding.etClassSearch.setText(turma)

                binding.etRecord.text = AlunoDatabase.registros()
                //binding.tvMessage.text = "Registro gravado."
                Toast.makeText(this, "Registro gravado!", Toast.LENGTH_LONG).show()
            } else {
                binding.tvMessage.text = "Nota inválida. Tente novamente."
            }
        } else {
            binding.tvMessage.text = "Digitação incompleta. Tente novamente."
        }
    }

    private fun pesquisarAluno() {
        val nome = binding.etNameSearch.text.toString()
        val turma = binding.etClassSearch.text.toString()
        val alunosEncontrados = AlunoDatabase.pesquisar(nome, turma)
        var media = 0.0
        var situacao = "---"
        if (alunosEncontrados.size == 0) {
            binding.tvMessageSearch.text = "Nenhum registro encontrado."
        } else {
            media = if (alunosEncontrados.isNotEmpty()) {
                alunosEncontrados.map { it.nota }.average()
            } else {
                0.0
            }
            binding.tvMessageSearch.text = "${alunosEncontrados.size} ${if (alunosEncontrados.size == 1) " nota gravada." else "notas gravadas."}"
            situacao = if (media >= 7) "Aprovado" else "Reprovado"
        }
        binding.tvAverage.text = "Média: ${String.format("%.2f", media)}"
        if (situacao == "Reprovado") {
            binding.tvStatus.setTextColor(getColor(R.color.red2))
        } else {
            binding.tvStatus.setTextColor(getColor(R.color.black))
        }
        binding.tvStatus.text = "Situação: ${situacao}"
    }
}