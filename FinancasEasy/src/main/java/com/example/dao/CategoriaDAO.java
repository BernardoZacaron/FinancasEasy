/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

/**
 *
 * @author Aluno
 */
public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{
}
