package com.example;

import com.example.dao.CategoriaDAO;
import com.example.dao.OperacaoDAO;
import com.example.dao.UsuarioDAO;
import com.example.model.Categoria;
import com.example.model.Operacao;
import com.example.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BasicApplication implements CommandLineRunner{
    
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    OperacaoDAO oDao;
    @Autowired
    CategoriaDAO cDao;
    
    @Autowired
    PasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
            //Adições básicas:
            List<Operacao> operacoes = new ArrayList<>();
        Usuario user1 = new Usuario(1, "adm", pe.encode("123"), "Admilton da Silva", "admilton@email.com", 0, operacoes, true);
        uDao.save(user1);
        
        Usuario user2 = new Usuario(2, "denis", pe.encode("123"), "Denis Andrade", "denin@email.com", 0, operacoes, false);
        uDao.save(user2);
        
        
        Categoria cat1 = new Categoria(1, "Comida", "Compras de alimentos", false, null);
        cDao.save(cat1);
        
        Categoria cat2 = new Categoria(2, "Vendas", "Vendas dos meus itens usados", true, null);
        cDao.save(cat2);
    }

}
