/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aluno
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    @Column(length = 200, nullable = false)
    private String login, senha;
    
    @Column(length = 100, nullable = false)
    private String nome, email;
    
    @Column
    private double saldo;
    
    @OneToMany(mappedBy = "usuario")
    private List<Operacao> operacoes; 
    
    @Column
    private boolean administrador;
    
    
    private void atualizarSaldo(Operacao operacao){
        if(operacao.getCategoria().isAcao()){
            setSaldo(getSaldo() + operacao.getValor());
        }else{
            setSaldo(getSaldo() - operacao.getValor());
        }
    }
    
    public void computarOperacao(Operacao operacao){
        atualizarSaldo(operacao);
        
        getOperacoes().add(operacao);
    }
    
    public double calcularGanhosMes(int mes){
        double ganhos = 0;
        
        for(Operacao op : operacoes){
            if(op.getData().getMonth()+1 == mes){
                if(op.getCategoria().isAcao()){
                    ganhos += op.getValor();
                }
            }
        }
        
        return ganhos;
    }
    
    public double calcularGastosMes(int mes){
        double gastos = 0;
        
        for(Operacao op : operacoes){
            if(op.getData().getMonth()+1 == mes){
                if(!op.getCategoria().isAcao()){
                    gastos += op.getValor();
                }
            }
        }
        
        return gastos;
    }
    
    public List<Double> montarHistoricoPositivo(){
        int mes = 1;
        List<Double> historico = new ArrayList<>();
        
        while(mes <= 6 /*junho*/){
            historico.add(calcularGanhosMes(mes));
            mes++;
        }
        
        return historico;
    }
    
    public List<Double> montarHistoricoNegativo(){
        int mes = 1;
        List<Double> historico = new ArrayList<>();
        
        while(mes <= 6 /*junho*/){
            historico.add(calcularGastosMes(mes));
            mes++;
        }
        
        return historico;
    }
    
    public List<Double> montarHistoricoPorcentagemPositiva(){
        int mes = 1;
        List<Double> historico = new ArrayList<>();
        
        double porcentagem;
        double quantiaPositiva, quantiaNegativa;
        while(mes <= 6 /*junho*/){
            quantiaPositiva = calcularGanhosMes(mes);
            quantiaNegativa = calcularGastosMes(mes);
            
            porcentagem = quantiaPositiva / (quantiaPositiva+quantiaNegativa);
            
            if(Double.isNaN(porcentagem)){
                porcentagem = 0;
            }
            
            historico.add((porcentagem*100));
            mes++;
        }
        
        return historico;
    }
    
    public List<Double> montarHistoricoPorcentagemNegativa(){
        int mes = 1;
        List<Double> historico = new ArrayList<>();
        
        double porcentagem;
        double quantiaPositiva, quantiaNegativa;
        while(mes <= 6 /*junho*/){
            quantiaPositiva = calcularGanhosMes(mes);
            quantiaNegativa = calcularGastosMes(mes);
            
            porcentagem = quantiaNegativa / (quantiaPositiva+quantiaNegativa);
            
            if(Double.isNaN(porcentagem)){
                porcentagem = 0;
            }
            
            historico.add((porcentagem*100));
            mes++;
        }
        
        return historico;
    }
    
    public double totalGanhos(){
        double ganhosTotais = 0;
        
        for(Operacao op : operacoes){
            if(op.getCategoria().isAcao()){
                ganhosTotais += op.getValor();
            }
        }
        
        return ganhosTotais;
    }
    
    public int contadorEntradas(){
        int cont = 0;
        
        for(Operacao op : operacoes){
            if(op.getCategoria().isAcao()){
                cont++;
            }
        }
        
        return cont;
    }
    
    public int contadorSaidas(){
        int cont = 0;
        
        for(Operacao op : operacoes){
            if(!op.getCategoria().isAcao()){
                cont++;
            }
        }
        
        return cont;
    }
    
    public double totalGastos(){
        double gastosTotais = 0;
        
        for(Operacao op : operacoes){
            if(!op.getCategoria().isAcao()){
                gastosTotais += op.getValor();
            }
        }
        
        return gastosTotais;
    }
}
