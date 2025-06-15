Projeto uSafe - Controle de Usuários
Este é um projeto Spring Boot para o controle e autenticação de usuários. A aplicação utiliza Spring Security com JSON Web Tokens (JWT) para garantir a segurança dos endpoints.

🚀 Começando
Para executar este projeto localmente, você precisará ter o seguinte instalado:

Java 17 ou superior
Maven
Passos para Execução
Clone o repositório:

Bash
```
git clone <URL_DO_SEU_REPOSITORIO>
cd <NOME_DO_DIRETORIO>
```
Execute o projeto:
Você pode iniciar a aplicação usando o Maven Wrapper incluído:

Bash

./mvnw spring-boot:run
Ou, se preferir, pode executar a classe principal USafeApplication.java a partir da sua IDE.

Acesse a aplicação:
Após a inicialização, a aplicação estará disponível em http://localhost:8081.

🗂️ Hierarquia de Pastas
A estrutura do projeto segue as convenções do Maven e do Spring Boot para uma organização clara e modular.
```
.
├── .mvn/wrapper/            # Contém os arquivos do Maven Wrapper
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/br/uSafe
│   │   │       ├── DTO             # Data Transfer Objects para requisições e respostas
│   │   │       ├── config          # Configurações, como o Enum de UserRole
│   │   │       ├── controller      # Controladores REST que definem os endpoints da API
│   │   │       ├── infra           # Infraestrutura, como as classes de segurança (JWT, Security Filter)
│   │   │       ├── model           # Entidades JPA (ex: User)
│   │   │       ├── repositories    # Repositórios Spring Data JPA para acesso ao banco
│   │   │       └── service         # Lógica de negócios (ex: UserService)
│   │   └── resources
│   │       └── application.properties # Arquivo de configuração da aplicação
│   └── test                     # Testes unitários e de integração
├── .gitignore                   # Arquivos e pastas ignorados pelo Git
└── pom.xml                      # Arquivo de configuração do projeto Maven
```

Principais Componentes:

controller: Define os endpoints da API. AuthenticationController lida com login e registro, enquanto UserController gerencia as operações de CRUD para usuários.
service: Contém a lógica de negócio. UserService implementa os métodos para manipular os dados dos usuários.
model: Classes que representam as tabelas do banco de dados. A entidade User também implementa UserDetails para integração com o Spring Security.
repositories: Interfaces que estendem JpaRepository para interagir com o banco de dados de forma simplificada.
infra/security: Onde toda a configuração de segurança reside.
SecurityConfiguration: Configura as permissões de acesso aos endpoints (quais são públicos e quais são protegidos).
SecurityFilter: Um filtro que intercepta todas as requisições para validar o token JWT.
TokenService: Responsável por gerar e validar os tokens JWT.
DTO: Objetos simples para transferir dados entre o cliente e o servidor, como AuthenticationDTO, RegisterDTO e LoginResponseDTO.
resources/application.properties: Configura a porta do servidor (8081), a conexão com o banco de dados em memória H2 e as configurações do JPA.

🌐 Rotas da Aplicação (Endpoints)
A API expõe os seguintes endpoints:

Autenticação
POST /auth/login

Descrição: Autentica um usuário e retorna um token JWT.
Acesso: Público.
Corpo da Requisição:
JSON
```
{
  "email": "user@email.com",
  "password": "user_password"
}
```
Resposta de Sucesso (200 OK):
JSON
```
{
  "token": "seu.jwt.token.aqui"
}
```
POST /auth/register

Descrição: Registra um novo usuário no sistema.
Acesso: Público.
Corpo da Requisição:
JSON
```
{
  "name": "Nome do Usuário",
  "email": "user@email.com",
  "password": "user_password",
  "role": "USER" 
}
```
```
{
  "name": "Nome do Usuário",
  "email": "user@email.com",
  "password": "user_password",
  "role": "ADMIN" 
}
```

Resposta de Sucesso (201 Created): Retorna o objeto do usuário criado.
Usuários
Todos os endpoints abaixo requerem autenticação e, em alguns casos, permissão de administrador.

GET /users

Descrição: Retorna uma lista de todos os usuários.
Acesso: Requer permissão de ADMIN.
POST /users/create (Semelhante a /auth/register, mas pode estar em um fluxo diferente)

Descrição: Cria um novo usuário.
Acesso: Autenticado.
Corpo da Requisição: Objeto User.
PUT /users/update

Descrição: Atualiza os dados de um usuário existente.
Acesso: Autenticado.
Corpo da Requisição: Objeto User com o id e os campos a serem atualizados.
DELETE /users/delete/{id}

Descrição: Deleta um usuário pelo seu id.
Acesso: Autenticado.
Parâmetro de URL: id (Long) do usuário a ser deletado.
💾 Banco de Dados
O projeto está configurado para usar um banco de dados em memória H2.

URL do Console H2: http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:dbusafe
Username: user
Password: 123Mudar@
