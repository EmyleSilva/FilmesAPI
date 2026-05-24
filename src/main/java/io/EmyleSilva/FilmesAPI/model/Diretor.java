package io.EmyleSilva.FilmesAPI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidade que representa um Diretor.
 * Utiliza JPA para realizar o mapeamento dos atributos no Banco de Dados.
 *
 * @author Emyle Silva
 * */
@Entity
@Table(name = "diretor", schema = "public")
@Data
public class Diretor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @OneToMany(mappedBy = "diretor", fetch = FetchType.LAZY)
    private List<Filme> filmes;
}
