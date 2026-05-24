package io.EmyleSilva.FilmesAPI.repository;

import io.EmyleSilva.FilmesAPI.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a classe Diretor.
 * */
public interface DiretorRepository extends JpaRepository<Diretor, Integer> {
}
