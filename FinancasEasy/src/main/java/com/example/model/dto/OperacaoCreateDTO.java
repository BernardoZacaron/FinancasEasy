/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model.dto;

import com.example.model.Categoria;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Windows 10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoCreateDTO {
    private double valor;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    
    private int categoria;
    
    
}
