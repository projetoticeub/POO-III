package br.com.pets.adocao.dtos;

import br.com.pets.adocao.Models.Animal;
import br.com.pets.adocao.enums.StatusAdocao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalDTO(Long id,
                        @NotBlank
                        String nome,
                        @NotBlank
                        String tipoAnimal,
                        @NotBlank
                        String raca,
                        @NotNull
                        Integer idade,
                        @NotNull
                        StatusAdocao statusAdocao,
                        String imagem,
                        String descricao) {

    public AnimalDTO(Animal animal) {
        this(animal.getId(),
                animal.getNome(),
                animal.getTipoAnimal(),
                animal.getRaca(),
                animal.getIdade(),
                animal.getStatusAdocao(),
                animal.getImagem(),
                animal.getDescricao());
    }

    public Animal mapToAnimal() {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setNome(nome);
        animal.setTipoAnimal(tipoAnimal);
        animal.setRaca(raca);
        animal.setIdade(idade);
        animal.setStatusAdocao(statusAdocao);
        animal.setImagem(imagem);
        animal.setDescricao(descricao);
        return animal;
    }

}
