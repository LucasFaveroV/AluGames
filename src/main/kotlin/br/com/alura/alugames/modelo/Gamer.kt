package br.com.alura.alugames.modelo

import br.com.alura.alugames.br.com.alura.alugames.modelo.Jogo
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrEmpty()){
                criarIdInterno()
            }
        }
    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(nome: String, email: String, dataNascimento: String, usuario: String): this(nome, email){
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno(){
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)
        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }else {
            throw IllegalArgumentException("Email Inválido")
        }
    }

//    init {
//        if(nome.isNullOrBlank()){
//            throw IllegalArgumentException("Nome está em Branco")
//        }
//        this.email = validarEmail()
//    }

    companion object{
        fun criarGamer(): Gamer{
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = readln()
            println("Digite seu e-mail:")
            val email = readln()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = readln()
            if(opcao.equals("S",true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = readln()
                println("Digite seu nome de usuário:")
                val usuario = readln()
                return Gamer(nome, email, nascimento, usuario)
            } else {
                return Gamer(nome, email)
            }
        }
    }
}
