package br.com.pets.adocao.dtos;

import br.com.pets.adocao.Models.Animal;
import br.com.pets.adocao.enums.StatusAdocao;

public record AnimalDTO(Long id,
                        String nome,
                        String tipoAnimal,
                        String raca,
                        Integer idade,
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
