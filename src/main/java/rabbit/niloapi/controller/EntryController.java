package rabbit.niloapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rabbit.niloapi.dto.entry.EntryRequestDTO;
import rabbit.niloapi.dto.entry.EntryResponseDTO;
import rabbit.niloapi.service.EntryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService service;

    @GetMapping
    public ResponseEntity<List<EntryResponseDTO>> findAll(){
        List<EntryResponseDTO> entriesList = this.service.findAll();
        return ResponseEntity.ok().body(entriesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryResponseDTO> findById(@PathVariable String id){
        EntryResponseDTO entryResponseDTO = this.service.findById(id);
        return ResponseEntity.ok().body(entryResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EntryRequestDTO entryRequestDTO){
        EntryResponseDTO entryResponseDTO = this.service.create(entryRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entryResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody EntryRequestDTO entryRequestDTO, @PathVariable String id){
        this.service.update(entryRequestDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
