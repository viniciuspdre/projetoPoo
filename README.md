# Projeto POO
# Loja de Informática - Aplicativo Java

## Descrição
Este é um aplicativo desenvolvido em Java para gerenciar uma loja de informática, integrando-se a um banco de dados MySQL. A aplicação oferece funcionalidades essenciais para administração de clientes, produtos e vendas, garantindo um controle eficiente do estoque e análises detalhadas das transações.

## Funcionalidades
- **CRUD de Clientes**: Cadastro, edição, remoção e listagem de clientes.
- **CRUD de Produtos**: Gerenciamento de produtos com informações detalhadas.
- **Alertas de Estoque**: Notificação de produtos com estoque baixo.
- **Compra de Produtos**: Registra a compra de produtos pelos clientes.
- **Análise de Vendas**: Relatórios e estatísticas sobre vendas realizadas.
- **Geração de Faturas**: Emissão de faturas para compras efetuadas.

## Tecnologias Utilizadas
- **Linguagem**: Java (JavaFX para interface gráfica)
- **Banco de Dados**: MySQL
- **Frameworks e Bibliotecas**:
  - JDBC para conexão com MySQL
  - OpenPDF (para geração de faturas)

## Requisitos
- Java 17 ou superior
- MySQL 8.0 ou superior
- IDE recomendada: VS Code, IntelliJ IDEA ou Eclipse
- Driver JDBC para MySQL

## Configuração do Banco de Dados
1. Instale o MySQL e crie um banco de dados:
   ```sql
   CREATE DATABASE loja_informatica;
   ```
2. Execute o arquivo SQL presente no repositório (database.sql) para criar as tabelas necessárias.
4. Atualize as configurações de conexão no arquivo `src/dao/conexao/ConexaoDB.java`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/loja_informatica
   db.user=root
   db.password=senha
   ```

## Execução
1. Clone o repositório:
   ```bash
   git clone https://github.com/viniciuspdre/projetoPoo.git
   ```
2. Importe o projeto na sua IDE.
3. Configure o banco de dados conforme indicado acima.
4. Execute a aplicação a partir da classe principal.

### Tecnologias e Ferramentas:
 <div style="display=inline-block">
    <img height=40 title="MySql" alt="MySql" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original-wordmark.svg"/>&nbsp;
    <img height=40 title="Java" alt="Java" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg"/>&nbsp;
    <img height=40 title="IntelliJ" alt="IntelliJ" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg"/>&nbsp;
    <img height=40 title="Git" alt="Git" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/git/git-original.svg"/>&nbsp;
    <img height=40 title="CSS" alt="CSS" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/css3/css3-original.svg"/>&nbsp;
 </div>
