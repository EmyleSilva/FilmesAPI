# Filmes API 🎬

Esta é uma API REST para o gerenciamento de diretores e filmes, desenvolvida com o objetivo de aplicar na prática a metodologia TDD (Test-Driven Development) e a containerização de ambientes com Docker

## 🛠 Tecnologias e Ferramentas
- **Linguagem:** Java.
- **Framework:** Spring Boot (Spring Data JPA, Web).
- **Banco de Dados:** PostgreSQL 16.3
- **Containerização:** Docker
- **Testes:** JUnit 5 & Assertions
- **Documentação:** Javadoc (Código) e README (Projeto)
- **Produtividade:** Lombok (Annotations @Data, @RequiredArgsConstructor).

## 🧪 Metodologia TDD (Test-Driven Development)
O desenvolvimento seguiu rigorosamente o ciclo Red-Green-Refactor:

1. Red (Vermelho): Criação de testes que falham por não existir a implementação. Exemplo: validar que um diretor não pode ser salvo com nome nulo

2. Green (Verde): Implementação do código mínimo necessário para o teste passar

3. Refactor (Refatoração): Melhoria da estrutura do código mantendo a integridade dos testes

### Exemplo de Teste Unitário
Abaixo, um fragmento do teste utilizado para validar a regra de negócio no DiretorService:

```java
@Test
public void salvarValidarNomeNuloTest() {
    Diretor diretorInvalido = new Diretor();
    diretorInvalido.setNacionalidade("Brasileiro");
    diretorInvalido.setDataNascimento(LocalDate.of(1969, 11, 2));

    // Valida se a exceção correta é lançada ao tentar salvar um nome nulo
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
       service.salvar(diretorInvalido);
    });
}
```

## 📦 Estrutura de Entidades
A API trabalha com as seguintes entidades principais (mapeadas via JPA):

- **Diretor:** Gerencia informações de nome, data de nascimento e nacionalidade.
- **Filme:** Contém título, data de lançamento e o vínculo com um Diretor (Relacionamento Many-to-One).

## ⚙️ Configuração do Banco de Dados
A conexão com o banco de dados é realizada via PostgreSQL. Abaixo estão as configurações definidas no application.yaml:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/filmes
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: none # As tabelas são gerenciadas externamente ou via migrações
    properties:
      hibernate.format_sql: true
```

## 🐳 Ambiente Docker

Para preparar o ambiente de desenvolvimento, foram utilizados os seguintes comandos para criar a rede e os containers necessários

1. Criar a rede do projeto
    ```bash
    docker network create filmes-network
    ```

2. Subir o container do PostgreSQL
    ```bash
    docker run --name filmesdb \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=filmes \
    -p 5432:5432 \
    --network filmes-network \
    -d postgres:16.3
    ```
3. Subir o PgAdmin 4 para gerenciamento visual
    ```bash
    docker run --name pgadmin4_f \
    -p 15432:80 \
    -e PGADMIN_DEFAULT_EMAIL=admin@admin.com \
    -e PGADMIN_DEFAULT_PASSWORD=admin \
    --network filmes-network \
    -d dpage/pgadmin4:8.9
    ```

## 📚 Documentação do Código
O projeto utiliza Javadoc para detalhamento técnico das classes de modelo e serviços.

Para visualizar a documentação, acesse a pasta Docs na raíz do projeto e abra o arquivo index.html 

---

Projeto desenvolvido para fins acadêmicos. 
