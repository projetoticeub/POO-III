package br.com.pets.adocao.controllers;

import br.com.pets.adocao.dtos.AnimalDTO;
import br.com.pets.adocao.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AnimalDTO dados,
                                  UriComponentsBuilder builder) {
        var animalDTO = dados.mapToAnimal();
        var animal = animalService.save(animalDTO);
        var uri = builder.path("/animais/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(animal);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalDTO>> findAll(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
        var animal =  animalService.findAll(pageable).map(AnimalDTO::new);
        return ResponseEntity.ok().body(animal);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<AnimalDTO>> findAllAtivos(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
        var animal =  animalService.findAllAtivos(pageable).map(AnimalDTO::new);
        return ResponseEntity.ok().body(animal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        var animal = animalService.findById(id);
        return ResponseEntity.ok().body(new AnimalDTO(animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDados(@PathVariable Long id,
                                            @RequestBody @Valid AnimalDTO dados) {
        var animal = animalService.atualizarDados(id, dados);
        return ResponseEntity.ok().body(new AnimalDTO(animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
