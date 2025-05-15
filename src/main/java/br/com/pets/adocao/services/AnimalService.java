package br.com.pets.adocao.services;

import br.com.pets.adocao.models.Animal;
import br.com.pets.adocao.dtos.AnimalDTO;
import br.com.pets.adocao.repositories.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    public Animal save(Animal dados) {
        return animalRepository.save(dados);
    }

    public Page<Animal> findAll(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    public Page<Animal> findAllAtivos(Pageable pageable) {
        return animalRepository.findAllByAtivoTrue(pageable);
    }


    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Animal atualizarDados(Long id, AnimalDTO dados) {
        var animal = animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        animal.atualizarDados(dados);
        return animalRepository.save(animal);
    }

    public String delete(Long id) {
        var animal = animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        animal.delete();
        return "Pet deletado com sucesso!";
    }

}
