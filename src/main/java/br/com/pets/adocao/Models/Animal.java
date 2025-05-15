package br.com.pets.adocao.Models;

import br.com.pets.adocao.dtos.AnimalDTO;
import br.com.pets.adocao.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipoAnimal;
    private String raca;
    private Integer idade;
    @Enumerated(EnumType.STRING)
    private StatusAdocao statusAdocao;
    private String imagem;
    private String descricao;
    private Boolean ativo;

    public Animal(AnimalDTO animalDTO) {
        this.id = animalDTO.id();
        this.nome = animalDTO.nome();
        this.tipoAnimal = animalDTO.tipoAnimal();
        this.raca = animalDTO.raca();
        this.idade = animalDTO.idade();
        this.statusAdocao = animalDTO.statusAdocao();
        this.imagem = animalDTO.imagem();
        this.descricao = animalDTO.descricao();
        this.ativo = true;
    }

    public void atualizarDados(AnimalDTO animal) {
        if (animal.nome() != null) this.nome = animal.nome();
        if (animal.tipoAnimal() != null) this.tipoAnimal = animal.tipoAnimal();
        if (animal.raca() != null) this.raca = animal.raca();
        if (animal.idade() != null) this.idade = animal.idade();
        if (animal.statusAdocao() != null) this.statusAdocao = animal.statusAdocao();
        if (animal.imagem() != null) this.imagem = animal.imagem();
        if (animal.descricao() != null) this.descricao = animal.descricao();
    }

    public void delete() {
        this.ativo = false;
    }

}
