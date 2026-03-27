/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.kge.eti.OSApiAaplication.Controller;

import br.kge.eti.OSApiAaplication.domain.Service.OrdemServicoService;
import br.kge.eti.OSApiAaplication.domain.model.AtualizaStatusDTO;
import br.kge.eti.OSApiAaplication.domain.model.OrdemServico;
import br.kge.eti.OSApiAaplication.domain.repository.OrdemServicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private OrdemServicoService ordemServicoService;
    
    
    
    

    @GetMapping("/ordem-servico")
    public List<OrdemServico> listas() {
        return ordemServicoRepository.findAll();
    }
    


@Operation(summary = "Busca uma ordem de serviço por ID", description = "Retorna os detalhes de uma ordem de serviço específica baseada no ID fornecido")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ordem de serviço encontrada com sucesso"),
    @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada")
})
@GetMapping("/ordem-servico/{ordem_servicoID}")
public ResponseEntity<OrdemServico> buscar(
    
        @Parameter(name = "ordem_servicoID", description = "ID da ordem de serviço", example = "1") 
        @PathVariable Long ordem_servicoID) {
    
    Optional<OrdemServico> os = ordemServicoRepository.findById(ordem_servicoID);
    if (os.isPresent()) {
        return ResponseEntity.ok(os.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}




    @PostMapping("/ordem-servico")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }


    @PutMapping("/ordem-servico/{ordem_servicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordem_servicoID,
            @RequestBody OrdemServico ordemServico) {

        Optional<OrdemServico> osAtual = ordemServicoRepository.findById(ordem_servicoID);

        if (osAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrdemServico ordemServicoExistente = osAtual.get();

        ordemServicoExistente.setDescricao(ordemServico.getDescricao());
        ordemServicoExistente.setCliente(ordemServico.getCliente());
        ordemServicoExistente.setPreco(ordemServico.getPreco());
        
        if (ordemServico.getStatus() != null) {
            ordemServicoExistente.setStatus(ordemServico.getStatus());
        }
        
        ordemServicoExistente = ordemServicoService.criar(ordemServicoExistente);
        return ResponseEntity.ok(ordemServico);
    }
    @PutMapping("/ordem-servico/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(@PathVariable Long ordemServicoID,@Valid @RequestBody AtualizaStatusDTO statusDTO){
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(ordemServicoID, statusDTO.status());
        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping("/ordem-servico/{ordem_servicoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemservicoID) {
        if (!ordemServicoRepository.existsById(ordemservicoID)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoService.excluir(ordemservicoID);
        return ResponseEntity.noContent().build();
        
        
        
        
        
    }
}
