/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
    public Usuario findByLogin(String login);
    public Usuario findByLoginAndSenha(String login, String senha);
}
