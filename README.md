# HelpDesk Lab

Laboratório de estudos para desenvolvimento de uma aplicação de gerenciamento de chamados de suporte técnico.

O projeto tem como objetivo aprofundar conhecimentos em **Java, Spring Boot, Angular, PostgreSQL, Docker e Git**, utilizando uma abordagem prática e incremental.

> **Projeto educacional:** este repositório é destinado ao aprendizado e à experimentação. Não representa uma aplicação pronta para produção.

## Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 25 | Linguagem de programação do backend |
| Spring Boot | Framework para desenvolvimento da API REST |
| Spring Data JPA / Hibernate | Persistência de dados |
| PostgreSQL 17 | Banco de dados relacional |
| Flyway | Versionamento do esquema do banco |
| Jakarta Bean Validation | Validação dos dados recebidos |
| Maven Wrapper | Gerenciamento de dependências e build |
| Docker Compose | Execução do PostgreSQL |
| Git | Controle de versão |
| Angular | Frontend planejado para as próximas etapas |

## Funcionalidades implementadas

- Cadastro de chamados
- Listagem de chamados
- Consulta de chamado por ID
- Atualização de chamados
- Exclusão de chamados
- Persistência dos dados no PostgreSQL
- Validação dos campos recebidos pela API
- Tratamento centralizado de erros de validação
- Migrações de banco de dados com Flyway

## Estrutura do projeto

```text
helpdesk-lab/
├── backend/             # API REST Spring Boot
├── infra/               # Infraestrutura Docker
├── .env.example         # Modelo de configuração
├── .gitignore
└── README.md
```

## Requisitos

Para executar o projeto localmente:

- JDK 25
- Docker com Docker Compose
- Git
- PowerShell (para os exemplos no Windows)

O projeto utiliza Maven Wrapper, portanto não exige uma instalação global do Maven.

## Configuração do ambiente

### 1. Clonar o repositório

```powershell
git clone URL_DO_REPOSITORIO
cd helpdesk-lab
```

Substitua `URL_DO_REPOSITORIO` pela URL do GitHub após a publicação.

### 2. Configurar as variáveis de ambiente

Crie um arquivo `.env.local` na raiz, baseado no `.env.example`.

```powershell
Copy-Item .env.example .env.local
```

Defina uma senha local para `DB_PASSWORD`.

O arquivo `.env.local` contém configurações privadas e não deve ser versionado.

### 3. Iniciar o PostgreSQL

```powershell
docker compose --env-file .env.local -f infra/compose.yaml up -d
```

Verifique o estado do container:

```powershell
docker ps
```

### 4. Iniciar o backend

No PowerShell, na raiz do projeto, carregue as variáveis do `.env.local`:

```powershell
Get-Content .env.local | ForEach-Object {
    $linha = $_.Trim()

    if ($linha -and -not $linha.StartsWith("#")) {
        $partes = $linha -split "=", 2

        if ($partes.Count -eq 2) {
            [Environment]::SetEnvironmentVariable(
                $partes[0].Trim(),
                $partes[1].Trim(),
                "Process"
            )
        }
    }
}
```

Em seguida:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

A API estará disponível em:

`http://localhost:8080`

**Observação:** se o volume do PostgreSQL já existir, alterar `DB_PASSWORD` no arquivo de ambiente não modifica automaticamente a senha do usuário no banco.

## Endpoints da API

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/chamados` | Lista os chamados |
| GET | `/api/chamados/{id}` | Consulta um chamado |
| POST | `/api/chamados` | Cadastra um chamado |
| PUT | `/api/chamados/{id}` | Atualiza um chamado |
| DELETE | `/api/chamados/{id}` | Exclui um chamado |

### Exemplo de cadastro

Requisição:

```http
POST /api/chamados
Content-Type: application/json
```

Corpo:

```json
{
  "titulo": "Computador não inicializa",
  "descricao": "Equipamento apresenta falha ao ligar"
}
```

O status inicial do chamado é definido automaticamente como `ABERTO`.

### Status utilizados

- `ABERTO`
- `EM_ANDAMENTO`
- `RESOLVIDO`

## Evolução do laboratório

| Aula | Conteúdo | Situação |
|---|---|---|
| 01 | Preparação do ambiente | Concluída |
| 02 | Primeira API REST com Spring Boot | Concluída |
| 03 | API inicial de chamados | Concluída |
| 04 | PostgreSQL, JPA e Flyway | Concluída |
| 05 | CRUD completo, validação e tratamento de erros | Concluída |
| 06 | Testes automatizados | Planejada |

## Próximas etapas

A evolução do laboratório poderá incluir:

- Testes automatizados com JUnit e Mockito
- Melhorias na arquitetura da API
- Paginação, filtros e ordenação
- Autenticação e autorização
- Desenvolvimento do frontend com Angular
- Integração entre frontend e backend

## Finalidade

O HelpDesk Lab é um ambiente de aprendizado contínuo. Seu objetivo é compreender os conceitos e as boas práticas de desenvolvimento de software por meio da implementação progressiva de funcionalidades.

O código pode ser modificado e refatorado conforme novos conhecimentos forem adquiridos.