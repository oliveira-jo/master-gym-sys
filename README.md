
# Mastergymsys (Monorepo)

> Status: Developing V1

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Vscode](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37.svg?style=for-the-badge&logo=Postman&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) 
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)


Master Gym Sys é um sistema completo para gerenciamento de academias desenvolvido como um monorepo, contendo backend em Spring Boot e frontend em Angular. O projeto foi criado com foco em boas práticas de arquitetura, organização de código, escalabilidade e aplicações reais utilizadas no mercado.

---

## Sumário

- Destaques
- Tecnologias
- Roadmap
- Funcionalidades
- Arquitetura
- Estrutura
- Instalação
- Banco de Dados
- API
- Roadmap
- Licença
- Autor

---

## Destaques

- Arquitetura em Camadas
- Monorepo (Spring Boot + Angular)
- JWT Authentication
- Flyway Migrations
- OpenAPI (Swagger)
- Global Exception Handler
- DTO Pattern
- Mapper Pattern
- Repository Pattern
- Clean Code
- SOLID
- RESTful API

---

## Tecnologias

- Java 21
- Spring Boot
<!-- - Spring Security JWT -->
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- OpenAPI (Swagger)
- Angular 20
- TypeScript
- RxJS
- Angular Material
- Bootstrap
<!-- - Docker -->
- Git
- Maven
- Projections 

---

## Roadmap

- [x] CRUD de alunos
- [x] CRUD de planos
- [x] CRUD de modalidades
- [x] Matrículas
- [x] Autenticação JWT
- [x] Gestão de usuários (ADMIN, RECEPCIONISTA)
- [ ] Financeiro
- [ ] Dashboard
- [ ] Relatórios
- [ ] Notificações
- [ ] Auditoria e Logs
- [ ] Docker Compose

---

## Funcionalidades

- Autenticação JWT
- Gestão de usuários
- Gestão de alunos
- Gestão de planos
- Gestão de modalidades
- Gestão de graduações
<!-- - Matrículas -->
<!-- - Controle financeiro -->
<!-- - Pagamento de mensalidades -->
<!-- - Relatórios -->
- Documentação da API
- Tratamento global de exceções

---

## Arquitetura 

O projeto segue uma arquitetura em camadas:

![diagram](../mastergymsys/frontend/public/leyers-diagran.jpg)

---

## Banco de Dados

- O projeto utiliza PostgreSQL.
- As tabelas são criadas automaticamente através do Flyway.
- Configuração padrão:
- Host: localhost
- Porta: 5432
- Database: mastergym
- Username: postgres
- Password: password

---

<!-- ## Variáveis de Ambiente

- application.properties
- DB_HOST
- DB_PORT
- DB_NAME
- DB_USER
- DB_PASSWORD
- JWT_SECRET

--- -->

## Pré-requisitos

- Java 21+
- Maven 3.9+
- Node.js 22+
- npm 10+
- Angular CLI 20+
- Git

---

## Documentação da API

````
http://localhost:8080/api/v1/swagger-ui/index.html
````

---

## Instalação

1. Clone o repositório

````
git clone https://github.com/oliveira-jo/master-gym-sys.git
````

Entre na pasta do projeto:

````
cd master-gym-sys
````

2. Executando o Backend (Spring Boot)

Acesse a pasta do backend:

````
cd backend
````

Instale as dependências e execute o projeto:

````
./mvnw spring-boot:run
````

Ou, se estiver utilizando o Maven instalado na máquina:


````
mvn spring-boot:run
````

Também é possível abrir o projeto em sua IDE favorita 
(IntelliJ IDEA, Eclipse ou VS Code) e executar a classe principal da aplicação.

Após iniciar, a API estará disponível em:

````
http://localhost:8080

````

3. Executando o Frontend (Angular)

Em um novo terminal, acesse a pasta do frontend:

````
cd frontend
````

Instale as dependências:

````
npm install
````

Execute a aplicação:

````
ng serve
````

Ou:

````
npm start
````

A aplicação estará disponível em:

````
http://localhost:4200
````

---

## Como executar

### Rodando localmente

```bash
./mvnw spring-boot:run
```

---

##  Estrutura Monorepo

````
├── backend
│   └── spring-boot
├── frontend
│   └── angular
├── docs
└── docker
````

##  Estrutura do Backend

````
src
 ├── controller
 ├── service
 ├── repository
 ├── dto
 ├── entity
 ├── config
 └── security
````

##  Estrutura do Frontend

````
frontend/
│
├── src/
│   ├── assets/
│   ├── environments/
│   ├── styles.css
│   └── app/
│       ├── core/
│       │   ├── guards/
│       │   ├── interceptors/
│       │   ├── models/
│       │   ├── services/
│       │   └── constants/
│       │
│       ├── shared/
│       │   ├── components/
│       │   ├── pipes/
│       │   ├── directives/
│       │   └── interfaces/
│       │
│       ├── layout/
│       │   ├── home/
│       │   ├── navbar/
│       │   ├── footer/
│       │   └── sidebar/
│       │
│       ├── features/
│       │   ├── auth/
│       │   │   └── login/
│       │   ├── usuarios-user/
│       │   ├── alunos-student/
│       │   ├── modalidades-modality/
│       │   ├── planos-subscription/
│       │   ├── matriculas-enrollment/
│       │   ├── faturamento-/
│       │   ├── pagamentos-payment/
│       │   └── relatorios-report/
│       │
│       ├── app.component.ts
│       ├── app.routes.ts
│       └── app.config.ts
│
├── angular.json
├── package.json
└── tsconfig.json
````

---

## Licença

Este projeto está sob licença MIT.


### Contribuições são bem-vindas.

Faça um Fork

Crie uma branch

Abra um Pull Request

---

## 👨‍💻 Autor

Jonathan Oliveira

LinkedIn:
[LinkedIn](https://www.linkedin.com/in/oliveira-jo)

GitHub:
[GitHub](https://github.com/oliveira-jo)










