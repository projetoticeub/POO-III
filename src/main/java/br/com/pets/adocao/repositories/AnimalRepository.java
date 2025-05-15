package br.com.pets.adocao.repositories;

import br.com.pets.adocao.Models.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findAllByAtivoTrue(Pageable pageable);
}
