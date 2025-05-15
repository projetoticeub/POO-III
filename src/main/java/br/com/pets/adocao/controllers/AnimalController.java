package br.com.pets.adocao.controllers;

import br.com.pets.adocao.dtos.AnimalDTO;
import br.com.pets.adocao.services.AnimalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animais")
@Tag(name = "Animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AnimalDTO dados,
                                  UriComponentsBuilder builder) {
        try {
            var animalDTO = dados.mapToAnimal();
            var animal = animalService.save(animalDTO);
            var uri = builder.path("/animais/{id}").buildAndExpand(animal.getId()).toUri();
            return ResponseEntity.created(uri).body(animal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
        try {
            var animal = animalService.findById(id);
            return ResponseEntity.ok().body(new AnimalDTO(animal));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDados(@PathVariable Long id,
                                            @RequestBody @Valid AnimalDTO dados) {
        try {
            var animal = animalService.atualizarDados(id, dados);
            return ResponseEntity.ok().body(new AnimalDTO(animal));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
