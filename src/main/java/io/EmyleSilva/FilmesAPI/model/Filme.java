package io.EmyleSilva.FilmesAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entidade que representa um Filme.
 * Utiliza JPA para realizar o mapeamento dos atributos no Banco de Dados.
 *
 * @author Emyle Silva
 * */
@Entity
@Table(name = "filme", schema = "public")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;
}
