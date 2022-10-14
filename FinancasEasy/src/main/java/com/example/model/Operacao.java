/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Operacao {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    @ManyToOne
    private Usuario usuario;
    
    @Column
    private double valor;
    
    @Column
    private Date data;
    
    @ManyToOne
    private Categoria categoria;
    
    
}
