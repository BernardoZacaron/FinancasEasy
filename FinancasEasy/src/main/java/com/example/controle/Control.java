/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controle;

import com.example.dao.CategoriaDAO;
import com.example.dao.OperacaoDAO;
import com.example.dao.UsuarioDAO;
import com.example.model.Categoria;
import com.example.model.Operacao;
import com.example.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Control {
    
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    OperacaoDAO oDao;
    @Autowired
    CategoriaDAO cDao;
    
    @Autowired
    PasswordEncoder pe;
    
    
    @GetMapping({"/", "/home"})
    public String callHomePage() {
        return "index";
    }
    @GetMapping("/login")
    public String callLoginPage(){         
        return "login";
    }
    
    @GetMapping({"/registrarUsuario"})
    public String callRegistrarUsuarioPage(Model model){
        model.addAttribute("novoUsuario", new Usuario());
        
        return "registrarUsuario";
    }
    
    @Transactional
    @PostMapping("/saveUsuario")
    public String saveUsuario(@ModelAttribute Usuario novoUsuario){
        Usuario u = new Usuario(0, novoUsuario.getLogin(), pe.encode(novoUsuario.getSenha()), novoUsuario.getNome(),
                novoUsuario.getEmail(), 0, null, false);
        
        uDao.save(u);
        
        return "redirect:/login";
    }
}
