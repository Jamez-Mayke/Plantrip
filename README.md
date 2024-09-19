# PlanTrip
## Organizador de viagens em grupo

Este projeto é uma aplicação Spring Boot que permite criar eventos de viagem, adicionar roteiros e links relevantes, e enviar convites para os participantes, que podem aceitar ou recusar a participação.

## Visão Geral
Este sistema permite organizar e gerenciar eventos de viagem, incluindo a criação de roteiros e o envio de convites para os participantes. Cada participante pode aceitar ou recusar o convite, o que facilita a gestão e a comunicação dentro do planejamento da viagem.

## Funcionalidades
* Criar eventos de viagem
* Adicionar roteiro e links à viagem (hotéis, pontos turísticos, etc.)
* Enviar convites por e-mail para os convidados
* Aceitação ou recusa de convites pelos participantes
* Visualizar o status de participação dos convidados
* Gerenciamento dos eventos e participantes

## Tecnologias Utilizadas
* Java 17
* Spring Boot 3
* Spring Data JPA - Para persistência de dados
* H2 Database - Banco de dados em memória para desenvolvimento
* Flyway - Controle de versões do banco de dados
* Lombok - Para redução de código boilerplate

## Instalação
### Pré-requisitos
* Java 17+
* Maven 3.6+
* Git

## Passos para clonar o projeto e rodar localmente:
1. Clone o repositório:
    git clone https://github.com/Jamez-Mayke/Plantrip

2. Compile o projeto com Maven:
    mvn clean install

3. Execute a aplicação:
    mvn spring-boot:run
    ou
    Rodar a aplicação via IDE

4. Acesse a aplicação em http://localhost:8080.

## OBS
Flyway é usado para migrações do banco de dados. Ao rodar a aplicação, as migrações serão aplicadas automaticamente. Para ver o histórico de migrações:
    mvn flyway:info

## Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais informações.

