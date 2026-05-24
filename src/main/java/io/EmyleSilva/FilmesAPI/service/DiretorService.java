package io.EmyleSilva.FilmesAPI.service;

import io.EmyleSilva.FilmesAPI.model.Diretor;
import io.EmyleSilva.FilmesAPI.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe Service do Diretor.
 *
 * Responsável por validar os dados a serem persisitidos,
 * funcionando como intermediário entre a futura camada controladora e o
 * banco de dados (repository).
 *
 * @author Emyle Silva
 * */
@Service
@RequiredArgsConstructor
public class DiretorService {

    private final DiretorRepository repository;

    public boolean nomeNulo (Diretor diretor) {
        return (diretor.getNome() == null);
    }

    /**
     * Salva um novo diretor no banco de dados.
     *
     * @param diretor Objeto Diretor que será persistido no banco.
     * @return O objeto persistido.
     * */
    public Diretor salvar(Diretor diretor) {
        if (nomeNulo(diretor)) {
            throw new IllegalArgumentException("O nome do diretor não pode ser nulo.");
        }
        return repository.save(diretor);
    }

    /**
     * Atualiza uma instância que já existe no banco de dados.
     *
     * @param diretorAtualizado O objeto com os dados atualizados.
     * @return O objeto atualizado persistido no banco.
     * */
    public Diretor atualizar(Diretor diretorAtualizado) {
        return repository.save(diretorAtualizado);
    }

    /**
     * Busca no banco de dados todos os diretores persistidos.
     *
     * @return Uma lista com os diretores encontrados.
     * */
    public List<Diretor> buscarTodos() {
        return repository.findAll();
    }

    /**
     * Deleta um diretor do banco de dados.
     *
     * @param id O id do diretor que deve ser excluído.
     * */
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }
}

