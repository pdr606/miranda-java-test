# Miranda Java Test

## Descrição

Api REST para gerenciar cadastro de carros efetuado como foram de estudo de teste prático.

## Tecnologias

* [JAVA 17](https://www.java.com/pt-BR/) 
* [Spring](https://spring.io/projects/spring-boot) 
* [Apache Maven 3.8.6](https://maven.apache.org/) 
* [IntelliJ](https://www.jetbrains.com/idea/) 
* [Docker](https://www.docker.com/) 
* [H2 Database](https://www.h2database.com/html/main.html) 

## Instalação

Para executar o projeto você pode utilizar a IDE de sua preferência, executar através do Maven ou através do Docker.

### IDE

Para executar o projeto através da IDE, basta importar o projeto como um projeto Maven e executar a classe JavaTestApplication.

### Maven

Para executar o projeto através do Maven, basta executar o comando abaixo na raiz do projeto:

```bash
mvn spring-boot:run
```

### Docker

Para executar o projeto através do Docker, basta executar o comando abaixo na raiz do projeto:

```bash
docker-compose up -d
```

## Requisitos do sistema

- Possuir o JDK 1.8 instalado
- Possuir o Maven instalado
- Possuir o Docker instalado ( Opcional )
- Uma IDE ou editor de sua preferência ( Opcional )
  
## Endpoints

### Car

| Método |         Endpoint         |               Descrição                        |
|:------:|:------------------------:|:----------------------------------------------:|
|  GET   | "/api/v1/cars"           | Retorna todos os carros de forma paginada      |
|  GET   | "/api/v1/cars/{id}"      | Retorna o carro com o id informado             |
|  GET   | "/api/v1/cars/search"    | Retorna os carros de acordo com a query string |
|  POST  | "/api/v1/cars"           | Cadastra um novo carro                         |
|  PUT   | "/api/v1/cars/{id}"      | Atualiza o carro com o id informado            |
| DELETE | "/api/v1/cars/{id}"      | Deleta o carro com o id informado              |

*Ao atualizar um carro (PUT), os campos que não forem informados serão mantidos.*

## Json de exemplo

Para facilitar o entendimento do projeto, segue abaixo um json de exemplo para cada recurso.

### Carro

```json
{
    "vehicle": "CORSA",
    "brand": "CHEVROLET",
    "year": 2009,
    "description": "Carro extremamente econômico",
    "chassis": "5AKjBZwHE4tAV9830",
    "price": 220000
}
```

* O campo CHASSIS deve ser único

### Banco de dados

O banco de dados utilizado foi o H2, que é um banco de dados em memória. Para acessar o console do banco de dados, basta acessar a url http://localhost:8080/h2-console e inserir as informações abaixo:

|    Campo     |         Valor          |
|:------------:|:----------------------:|
| Driver Class |     org.h2.Driver      |
|   JDBC URL   |     jdbc:h2:mem:car    |   
|  User Name   |           sa           |
|   Password   |      {em branco}       |
