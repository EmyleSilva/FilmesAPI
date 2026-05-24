package io.EmyleSilva.FilmesAPI.service;

import io.EmyleSilva.FilmesAPI.model.Diretor;
import io.EmyleSilva.FilmesAPI.repository.DiretorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe de testes unitários para o DiretorService.
 * @see DiretorService
 *
 * @author Emyle Silva
 * */
@SpringBootTest
class DiretorServiceTest {

    @Autowired
    DiretorRepository repository;

    @Autowired
    DiretorService service;

    /**
     * Teste para validar a função "salvar" do Service.
     * O teste deve falhar quando o id do objeto retornado pelo service for nulo (significa que o objeto não foi persistido),
     * ou quando os dados enviados são diferentes dos dados recebidos do service.
     * */
    @Test
    public void salvarTest() {
        Diretor diretor = new Diretor();
        diretor.setNome("Denis Villeneuve");
        diretor.setNacionalidade("Canadense");
        diretor.setDataNascimento(LocalDate.of(1967, 10, 3));

        var diretorSalvo = service.salvar(diretor);

        Assertions.assertNotNull(diretorSalvo.getId());
        Assertions.assertEquals(diretor.getNome(), diretorSalvo.getNome());
        Assertions.assertEquals(diretor.getNacionalidade(), diretorSalvo.getNacionalidade());
        Assertions.assertEquals(diretor.getDataNascimento(), diretorSalvo.getDataNascimento());
    }

    /**
     * Teste para validar o método de salvar do service,
     * garantindo que o nome do diretor não deve ser nulo.
     *
     * O teste deve falhar quando um diretor inválido (com nome nulo) é salvo,
     * de modo que a excessão IllegalArgumentException não é lançada.
     * */
    @Test
    public void salvarValidarNomeNuloTest() {
        Diretor diretorInvalido = new Diretor();
        diretorInvalido.setNacionalidade("Brasileiro");
        diretorInvalido.setDataNascimento(LocalDate.of(1969, 11, 2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           service.salvar(diretorInvalido);
        });
    }

    /**
     * Teste para validar o método "atualizar" do Service.
     * Deve falhar quando os dados atualizados retornados pelo service são diferentes dos dados
     * que foram enviados para atualização.
     * */
    @Test
    public void atualizarTeste() {
        Optional<Diretor> diretorOptional = repository.findById(1);
        if (diretorOptional.isPresent()) {
            Diretor diretorOriginal = diretorOptional.get();
            Diretor diretorAtualizar = new Diretor();
            diretorAtualizar.setId(diretorOriginal.getId());
            diretorAtualizar.setNome(diretorOriginal.getNome());
            diretorAtualizar.setDataNascimento(diretorOriginal.getDataNascimento());
            diretorAtualizar.setNacionalidade("NacionalidadeTestUpdate");

            Diretor resultado = service.atualizar(diretorAtualizar);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(diretorOriginal.getId(), resultado.getId());
            Assertions.assertEquals("NacionalidadeTestUpdate", resultado.getNacionalidade());
            Assertions.assertEquals(diretorOriginal.getNome(), resultado.getNome());
            Assertions.assertEquals(diretorOriginal.getDataNascimento(), resultado.getDataNascimento());
        }
    }

    /**
     * Teste para validar o método "buscarTodos" do Service.
     * O teste deve falhar se a lista for nula.
     * */
    @Test
    public void listarTest() {
        List<Diretor> lista = service.buscarTodos();
        Assertions.assertNotNull(lista);
    }

    /**
     * Teste para o método deletarPorId do Service.
     * Deve falhar quando o objeto ainda existe no banco de dados após
     * o retorno do service.
     * */
    @Test
    public void deletarPorIdTest() {
        Optional<Diretor> diretorOptional = repository.findById(1);

        if (diretorOptional.isPresent()) {
            Diretor diretorOriginal = diretorOptional.get();

            service.deletarPorId(diretorOriginal.getId());
            Assertions.assertNull(repository.findById(diretorOriginal.getId()));
        }
    }
}