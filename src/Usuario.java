package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.Utils;

public class Usuario {
    public String nome;
    public String cpf;
    public String email;
    public String numero;
    public boolean status;
    public int fidelidade;

    public List<Produto> carrinho;
    public List<Produto> alugados;

    //TODO: usuario servindo como data class?

    public Usuario(){}

    public Usuario(String _nome, String _CPF, String _email, String _numero) {

        this.nome = _nome;
        this.cpf = _CPF;
        this.email = _email;
        this.numero = _numero;
        this.setStatus(false);
        carrinho = new ArrayList<Produto>();
        alugados = new ArrayList<Produto>();
        this.setFidelidade(0);
    }

    public String displayName(){
        return this.nome + " ["+this.cpf+"]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public int getfidelidade()
    {
        return fidelidade;
    }

    public void setFidelidade()
    {
        this.fidelidade  = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return String.valueOf(numero);
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(int fidelidade) {
        this.fidelidade = fidelidade;
    }
}
