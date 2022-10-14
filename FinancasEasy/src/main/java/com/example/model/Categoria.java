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
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(length = 200)
    private String descricao;
    
    @Column
    private boolean acao; //entrada ou saida
    
    @OneToMany(mappedBy = "categoria")
    private List<Operacao> operacoes; 
}
