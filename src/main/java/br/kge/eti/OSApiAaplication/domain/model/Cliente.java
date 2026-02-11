/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.kge.eti.OSApiAaplication.domain.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */
@RestController
public class Cliente {
    
    @PersistenceContext
            private EntityManager manager;
    
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        
        return manager.createQuery("from Cliente", Cliente.class).getResultList();
    }
}
