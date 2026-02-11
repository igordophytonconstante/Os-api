/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.kge.eti.OSApiAaplication.controller;

import br.kge.eti.OSApiAaplication.domain.model.Cliente;
import br.kge.eti.OSApiAaplication.domain.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository ClienteRepository;
    
    @GetMapping("/clientes")
    public List<Cliente> listas(){
        
        return ClienteRepository.findByNome("KGe");
          
    }
}

        
      
        
        

        
        
        
        
    

