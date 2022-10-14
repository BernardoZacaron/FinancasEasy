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
import com.example.model.dto.OperacaoCreateDTO;
import com.example.seguranca.UserLogado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/financas")
public class ControlFinancas {
    
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    OperacaoDAO oDao;
    @Autowired
    CategoriaDAO cDao;
    
    
    
    @GetMapping({"/registrarCategoria"})
    public String callRegistrarCategoriaPage(Model model){
        model.addAttribute("novoCategoria", new Categoria());
        
        return "registrarCategoria";
    }
    
    @Transactional
    @PostMapping("/saveCategoria")
    public String saveCategoria(@ModelAttribute Categoria novoCategoria){
        cDao.save(novoCategoria);
        
        return "redirect:/home";
    }
    
    
    @GetMapping({"/registrarOperacao"})
    public String callRegistrarOperacaoPage(Model model){        
        model.addAttribute("categorias", cDao.findAll());
        model.addAttribute("novoOperacao", new OperacaoCreateDTO(0, new Date(), 0));
        
        return "registrarOperacao";
    }
    
    @Transactional
    @PostMapping("/saveOperacao")
    public String saveOperacao(@ModelAttribute OperacaoCreateDTO novoOperacao, Authentication auth){        
        UserLogado logado = (UserLogado) auth.getPrincipal();
        
        Usuario usuarioAtualizado = uDao.findById(logado.getUsuario().getId()).get();
                
        Operacao operacao = new Operacao(0, null, novoOperacao.getValor(), novoOperacao.getData(), null);
        
        /*
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dataCrua = novoOperacao.getData().toString();
        try {
            novoOperacao.setData(dt.parse(dataCrua));
        } catch (ParseException ex) {
            System.out.println("Erro ao converter a data");
        }
        */
        
        operacao.setUsuario(usuarioAtualizado);
        operacao.setCategoria(cDao.findById(novoOperacao.getCategoria()).get());
        
        usuarioAtualizado.computarOperacao(operacao);
        
        uDao.save(usuarioAtualizado);
        oDao.save(operacao);
        
        return "redirect:/home";
    }
    
    
    
    @GetMapping("/minhasFinancas")
    public String callFinancasPage(Model model, Authentication auth) {
        realizarPassagemValores(model, auth);
        
        return "financas";
    }
    
    private void realizarPassagemValores(Model model, Authentication auth){
        UserLogado logado = (UserLogado) auth.getPrincipal();
        Usuario usuario = uDao.findById(logado.getUsuario().getId()).get();
        
        model.addAttribute("saldo", usuario.getSaldo());
        model.addAttribute("quantidadeEntradas", usuario.contadorEntradas());
        model.addAttribute("quantidadeSaidas", usuario.contadorSaidas());
        model.addAttribute("valorEntradas", usuario.totalGanhos());
        model.addAttribute("valorSaidas", usuario.totalGastos());
        
        model.addAttribute("quantiaPositJaneiro", usuario.montarHistoricoPositivo().get(0));
        model.addAttribute("quantiaNegatJaneiro", usuario.montarHistoricoNegativo().get(0));
        model.addAttribute("porcPositJaneiro", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(0)));
        model.addAttribute("porcNegatJaneiro", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(0)));
        
        model.addAttribute("quantiaPositFevereiro", usuario.montarHistoricoPositivo().get(1));
        model.addAttribute("quantiaNegatFevereiro", usuario.montarHistoricoNegativo().get(1));
        model.addAttribute("porcPositFevereiro", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(1)));
        model.addAttribute("porcNegatFevereiro", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(1)));
        
        model.addAttribute("quantiaPositMarco", usuario.montarHistoricoPositivo().get(2));
        model.addAttribute("quantiaNegatMarco", usuario.montarHistoricoNegativo().get(2));
        model.addAttribute("porcPositMarco", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(2)));
        model.addAttribute("porcNegatMarco", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(2)));
        
        model.addAttribute("quantiaPositAbril", usuario.montarHistoricoPositivo().get(3));
        model.addAttribute("quantiaNegatAbril", usuario.montarHistoricoNegativo().get(3));
        model.addAttribute("porcPositAbril", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(3)));
        model.addAttribute("porcNegatAbril", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(3)));
        
        model.addAttribute("quantiaPositMaio", usuario.montarHistoricoPositivo().get(4));
        model.addAttribute("quantiaNegatMaio", usuario.montarHistoricoNegativo().get(4));
        model.addAttribute("porcPositMaio", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(4)));
        model.addAttribute("porcNegatMaio", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(4)));
        
        model.addAttribute("quantiaPositJunho", usuario.montarHistoricoPositivo().get(5));
        model.addAttribute("quantiaNegatJunho", usuario.montarHistoricoNegativo().get(5));
        model.addAttribute("porcPositJunho", String.format("%.2f",usuario.montarHistoricoPorcentagemPositiva().get(5)));
        model.addAttribute("porcNegatJunho", String.format("%.2f",usuario.montarHistoricoPorcentagemNegativa().get(5)));
    }
}
