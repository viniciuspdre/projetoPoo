

CREATE TABLE loja # Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    cnpj VARCHAR(18),
    categoria VARCHAR(50),
    nome VARCHAR (80) NOT NULL, # NOT NULL = garante que a coluna nao tenha valor nulo.
    pais CHAR (3),
    estado CHAR (2),
    cidade VARCHAR(50),
    bairro VARCHAR(50),
    rua VARCHAR(50),
    numero VARCHAR(10),
    cep VARCHAR(9),
    telefone VARCHAR(14),
    email VARCHAR(80),
    PRIMARY KEY(cnpj) #Chave Primaria
    );

CREATE TABLE usuario #Comando DDL (Linguagem de Definição de Dados) para criacao de tabelas
(
    login VARCHAR(80),
    senha VARCHAR(15) NOT NULL, # NOT NULL = garante que a coluna nao tenha valor nulo.
    nome VARCHAR(70) NOT NULL, # NOT NULL = garante que a coluna nao tenha valor nulo.
    cpf VARCHAR(14) NOT NULL, # NOT NULL = garante que a coluna nao tenha valor nulo.
    data_nascimento DATE,
    pais CHAR(3),
    estado CHAR(2),
    cidade VARCHAR(50),
    bairro VARCHAR(50),
    rua VARCHAR(50),
    numero VARCHAR(10),
    cep VARCHAR(9),
    foto_usuario longblob,
    cnpj_loja VARCHAR(18),
    tipo_usuario ENUM("admin","cliente"),
    PRIMARY KEY(login), # Chave Primaria
    FOREIGN KEY (cnpj_loja) REFERENCES loja (cnpj) # Chave Estrangeira
    );

alter table usuario modify column tipo_usuario ENUM("admin","padrão");

CREATE TABLE produto #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    cod VARCHAR(50),
    nome VARCHAR(60) NOT NULL,# NOT NULL = garante que a coluna nao tenha valor nulo.
    preco real CHECK (preco > 0) NOT NULL, # Garante que os valores em uma coluna satisfacam uma condicao especifica
    categoria VARCHAR(50),
    marca VARCHAR(50),
    descricao VARCHAR(1000),
    foto_produto longblob,
    estoque int,
    vendidos int,
    cnpj_loja VARCHAR(18),
    PRIMARY KEY (cod), # Chave Primaria
    FOREIGN KEY (cnpj_loja) REFERENCES loja (cnpj) # Chave Estrangeira
    );

alter table produto add estoque_minimo int;

select * from produto;
select categoria from produto order by categoria ASC;

CREATE TABLE fornecedor #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80) NOT NULL,# NOT NULL = garante que a coluna nao tenha valor nulo.
    cnpj VARCHAR(18),
    data_fornecimento DATE,
    nome VARCHAR(80) NOT NULL,# NOT NULL = garante que a coluna nao tenha valor nulo.
    pais CHAR(3),
    estado CHAR(2),
    cidade VARCHAR(50),
    bairro VARCHAR(50),
    rua VARCHAR(50),
    numero VARCHAR(10),
    cep VARCHAR(9),
    PRIMARY KEY(cnpj), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario(login) # Chave Estrangeira
    );

CREATE TABLE dependente #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80) NOT NULL,# NOT NULL = garante que a coluna nao tenha valor nulo.
    nome VARCHAR(60) NOT NULL,# NOT NULL = garante que a coluna nao tenha valor nulo.
    sexo CHAR(1),
    data_nascimento DATE,
    grau_parentesco VARCHAR(25),
    PRIMARY KEY (data_nascimento,login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario(login) # Chave Estrangeira
    );

CREATE TABLE administrador #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80), # NOT NULL = garante que a coluna nao tenha valor nulo.
    cargo VARCHAR(50),
    PRIMARY KEY (login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario (login) # Chave Estrangeira
    );

CREATE TABLE cliente #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80),
    status VARCHAR(10),
    PRIMARY KEY (login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario (login) # Chave Estrangeira
    );

CREATE TABLE dados_bancarios_usuario #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80),
    nome_titular VARCHAR(100) NOT NULL, -- Nome do titular da conta
    banco VARCHAR(50) NOT NULL, -- Nome do banco
    agencia VARCHAR(20) NOT NULL, -- Número da agência
    numeroConta VARCHAR(30) NOT NULL, -- Número da conta corrente
    tipo_conta ENUM('Corrente', 'Poupança') NOT NULL, -- Tipo da conta
    chave_pix VARCHAR(100) DEFAULT NULL, -- Chave PIX (opcional)
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP, -- Data de criação do registro
    atualizado_em DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Data da última atualização
    PRIMARY KEY (login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario (login) # Chave Estrangeira
    );

CREATE TABLE emails_usuario #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80),
    emails VARCHAR(80),
    PRIMARY KEY (emails,login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario(login) # Chave Estrangeira
    );

CREATE TABLE telefones_usuario #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80),
    telefones VARCHAR(80),
    PRIMARY KEY(telefones,login_usuario), # Chave Primaria
    FOREIGN KEY (login_usuario) REFERENCES usuario(login) # Chave Estrangeira
    );

CREATE TABLE usuario_compra_produto #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario VARCHAR(80),
    cod_produto VARCHAR(50),
    data_compra DATE,
    forma_pagamento ENUM('Cartão de Crédito', 'Cartão de Débito', 'Pix', 'Boleto'),
    PRIMARY KEY(login_usuario,cod_produto), # Chave Primaria
    FOREIGN KEY(login_usuario) REFERENCES usuario (login), # Chave Estrangeira
    FOREIGN KEY(cod_produto) REFERENCES produto (cod) # Chave Estrangeira
    );

CREATE TABLE loja_detem_fornecedor #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    cnpj_loja VARCHAR(18),
    cnpj_fornecedor VARCHAR(18),
    PRIMARY KEY (cnpj_loja,cnpj_fornecedor), # Chave Primaria
    FOREIGN KEY (cnpj_loja) REFERENCES loja (cnpj), # Chave Estrangeira
    FOREIGN KEY (cnpj_fornecedor) REFERENCES fornecedor (cnpj) # Chave Estrangeira
    );

CREATE TABLE administrador_gerencia_administrador #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    login_usuario_administrador_gerencia VARCHAR(80),
    login_usuario_administrador_gerenciado VARCHAR(80),
    PRIMARY KEY(login_usuario_administrador_gerencia,login_usuario_administrador_gerenciado), # Chave Primaria
    FOREIGN KEY(login_usuario_administrador_gerencia) REFERENCES administrador (login_usuario), # Chave Estrangeira
    FOREIGN KEY(login_usuario_administrador_gerenciado) REFERENCES administrador (login_usuario) # Chave Estrangeira
    );

CREATE TABLE vendas (
                        id_venda INT AUTO_INCREMENT PRIMARY KEY,  -- ID único para cada venda
                        cnpj_loja VARCHAR(18) NOT NULL,           -- FK para a loja
                        login_usuario VARCHAR(80) NOT NULL,       -- FK para o usuário (cliente)
                        data_venda DATE NOT NULL,                 -- Data da venda
                        horario TIME NOT NULL,                     -- Horário da venda
                        valor_total DECIMAL(10,2) NOT NULL,       -- Valor da venda (corrigido FLOAT para DECIMAL)
                        forma_pagamento ENUM('Cartão de Crédito', 'Cartão de Débito', 'Pix', 'Boleto') NOT NULL,  -- Forma de pagamento padronizada
                        data_vencimento DATE,                     -- Data de vencimento do pagamento (se aplicável)

    -- Definição de chaves estrangeiras
                        FOREIGN KEY (cnpj_loja) REFERENCES loja (cnpj),
                        FOREIGN KEY (login_usuario) REFERENCES usuario (login)
);

alter table vendas modify column forma_pagamento enum('Cartão de Crédito', 'Cartão de Débito', 'Pix', 'Boleto', 'Dinheiro')  not null;

CREATE TABLE cliente #Comando DDL (Linguagem de Definicao de Dados) para criacao de tabelas
(
    nome VARCHAR(70),
    cpf VARCHAR(14),
    estado ENUM ( 'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB',
                  'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO') NOT NULL,
    sexo ENUM('MASCULINO', 'FEMININO', 'OUTROS') NOT NULL,
    data_registro DATE,
    data_nascimento DATE,
    status_cliente VARCHAR(10),
    PRIMARY KEY (cpf)
    );

select * from vendas;
SELECT * FROM produto_vendas;
select * from produto;

insert into cliente values("Ivanilson", "98756432115", "PE", "MASCULINO", "2025-02-08", "2005-12-04", "Ativo");
select * from cliente;

drop table cliente;

alter table vendas add cpf_cliente VARCHAR(14);
ALTER TABLE vendas
    ADD CONSTRAINT fk_vendas_cliente
        FOREIGN KEY (cpf_cliente)
            REFERENCES cliente(cpf);


create table produto_vendas (
                                id_venda int,
                                cod_produto varchar(50),
                                quantidade int,
                                preco_unitario float,

                                primary key (id_venda, cod_produto),
                                foreign key (id_venda) references vendas(id_venda),
                                foreign key (cod_produto) references produto(cod)
);

alter table vendas add estado_venda enum("concluída", "em andamento");

insert into loja (cnpj, categoria, nome, pais, estado, cidade, bairro, rua, numero, cep, telefone, email)
values('23.456.789/0001-95', 'Loja de Informática', 'HelliotTech', 'BR', 'PE', 'Belo Jardim','São Pedro', 'Virando a Esquina', '15', '12345-678', '(11)91234-5678', 'loja@loja.com');

INSERT INTO produto (cod, nome, preco, estoque, vendidos, categoria, marca, descricao, cnpj_loja)
VALUES
    ('CPV-2025-00001', 'Notebook Dell Inspiron 15', 3999.90, 50, 5, 'Notebooks', 'Dell', 'Notebook com processador Intel Core i5, 8GB RAM, 256GB SSD.', '23.456.789/0001-95'),
    ('CPV-2025-00002', 'Mouse Logitech MX Master 3', 499.90, 100, 20, 'Acessórios', 'Logitech', 'Mouse ergonômico sem fio com alta precisão.', '23.456.789/0001-95'),
    ('CPV-2025-00003', 'Monitor LG UltraWide 29"', 1599.90, 30, 10, 'Monitores', 'LG', 'Monitor ultrawide com resolução Full HD e painel IPS.', '23.456.789/0001-95'),
    ('CPV-2025-00004', 'Teclado Mecânico HyperX Alloy FPS', 599.90, 40, 15, 'Acessórios', 'HyperX', 'Teclado mecânico com switches Cherry MX Red.', '23.456.789/0001-95'),
    ('CPV-2025-00005', 'SSD Kingston NV2 1TB', 449.90, 70, 25, 'Armazenamento', 'Kingston', 'SSD NVMe PCIe 4.0 com alta velocidade de leitura e gravação.', '23.456.789/0001-95'),
    ('CPV-2025-00006', 'Processador AMD Ryzen 7 5800X', 1999.90, 25, 10, 'Processadores', 'AMD', 'Processador com 8 núcleos e 16 threads para alto desempenho.', '23.456.789/0001-95'),
    ('CPV-2025-00007', 'Placa-Mãe ASUS TUF Gaming B550M', 1299.90, 20, 5, 'Placas-Mãe', 'ASUS', 'Placa-mãe compatível com processadores Ryzen e suporte a PCIe 4.0.', '23.456.789/0001-95'),
    ('CPV-2025-00008', 'Memória RAM Corsair Vengeance 16GB', 399.90, 80, 35, 'Memórias', 'Corsair', 'Memória DDR4 de 3200MHz para alto desempenho.', '23.456.789/0001-95'),
    ('CPV-2025-00009', 'Gabinete Gamer NZXT H510', 749.90, 20, 7, 'Gabinetes', 'NZXT', 'Gabinete compacto com design minimalista e excelente ventilação.', '23.456.789/0001-95'),
    ('CPV-2025-00010', 'Fonte Corsair RM750', 699.90, 30, 8, 'Fontes', 'Corsair', 'Fonte modular com certificação 80 Plus Gold.', '23.456.789/0001-95'),
    ('CPV-2025-00011', 'Placa de Vídeo NVIDIA RTX 3060 Ti', 2999.90, 10, 3, 'Placas de Vídeo', 'NVIDIA', 'Placa de vídeo para jogos e criação de conteúdo.', '23.456.789/0001-95'),
    ('CPV-2025-00012', 'Impressora Epson EcoTank L3250', 1499.90, 15, 5, 'Impressoras', 'Epson', 'Impressora multifuncional com tanque de tinta recarregável.', '23.456.789/0001-95'),
    ('CPV-2025-00013', 'Roteador TP-Link Archer AX50', 699.90, 25, 10, 'Redes', 'TP-Link', 'Roteador Wi-Fi 6 com alta velocidade de conexão.', '23.456.789/0001-95'),
    ('CPV-2025-00014', 'Webcam Logitech C920', 499.90, 50, 20, 'Acessórios', 'Logitech', 'Webcam Full HD com microfone embutido.', '23.456.789/0001-95'),
    ('CPV-2025-00015', 'HD Externo Seagate 2TB', 599.90, 40, 15, 'Armazenamento', 'Seagate', 'HD externo portátil com alta capacidade.', '23.456.789/0001-95'),
    ('CPV-2025-00016', 'Headset Gamer HyperX Cloud II', 799.90, 30, 12, 'Acessórios', 'HyperX', 'Headset com som surround 7.1 e conforto extremo.', '23.456.789/0001-95'),
    ('CPV-2025-00017', 'Smartphone Samsung Galaxy S21', 4299.90, 20, 8, 'Celulares', 'Samsung', 'Smartphone com tela AMOLED de 120Hz e câmera tripla.', '23.456.789/0001-95'),
    ('CPV-2025-00018', 'Tablet Apple iPad 9ª Geração', 3699.90, 25, 9, 'Tablets', 'Apple', 'Tablet com chip A13 Bionic e suporte para Apple Pencil.', '23.456.789/0001-95'),
    ('CPV-2025-00019', 'Notebook Lenovo Ideapad 3', 3299.90, 35, 10, 'Notebooks', 'Lenovo', 'Notebook com AMD Ryzen 5, 8GB RAM e 256GB SSD.', '23.456.789/0001-95'),
    ('CPV-2025-00020', 'Cadeira Gamer DXRacer', 1499.90, 15, 4, 'Móveis', 'DXRacer', 'Cadeira ergonômica com design gamer e ajustes.', '23.456.789/0001-95'),
    ('CPV-2025-00021', 'Switch Gigabit TP-Link SG105', 199.90, 50, 20, 'Redes', 'TP-Link', 'Switch de 5 portas com suporte a Gigabit Ethernet.', '23.456.789/0001-95'),
    ('CPV-2025-00022', 'Power Bank Xiaomi Mi 20.000mAh', 249.90, 60, 25, 'Acessórios', 'Xiaomi', 'Bateria portátil de alta capacidade com suporte a carregamento rápido.', '23.456.789/0001-95'),
    ('CPV-2025-00023', 'Monitor Samsung Odyssey G5', 1999.90, 20, 6, 'Monitores', 'Samsung', 'Monitor curvo com resolução WQHD e taxa de 144Hz.', '23.456.789/0001-95'),
    ('CPV-2025-00024', 'Controle Xbox Series X', 499.90, 40, 18, 'Acessórios', 'Microsoft', 'Controle sem fio para Xbox e PC.', '23.456.789/0001-95'),
    ('CPV-2025-00025', 'Placa Captura Elgato HD60 S', 1299.90, 10, 3, 'Acessórios', 'Elgato', 'Placa de captura para streaming de jogos.', '23.456.789/0001-95'),
    ('CPV-2025-00026', 'Microfone Condensador Blue Yeti', 899.90, 20, 5, 'Acessórios', 'Blue', 'Microfone condensador para gravações e transmissões ao vivo.', '23.456.789/0001-95');

SELECT COD FROM PRODUTO ORDER BY COD DESC LIMIT 1;


#Comando DDL (Linguagem de Definicao de dados) usado para destruir tabelas

DROP TABLE telefones_usuario;

#Comando DDL (Linguagem de Definicao de dados) usado para alterar tabelas

ALTER TABLE fornecedor DROP data_fornecimento; # apagar coluna
ALTER TABLE fornecedor ADD data_fornecimento DATE; # adicionar coluna

ALTER TABLE vendas ADD num_parcelas int;

#Comando DML (Linguagem de Manipulacao de dados) usado para visualizar dados da tabela


SELECT * FROM loja; # Lista todas as informacoes sobre as lojas
SELECT * FROM telefones_usuario; # Lista os telefones dos usuarios
SELECT cod FROM produto; #Lista os codigos dos produtos
SELECT * FROM fornecedor; #Lista os fornecedores e suas informacoes pessoais
SELECT * FROM dependente; #Lista informacoes sobre cada dependente.
SELECT * FROM usuario; # Lista todas a informacoes dos usuarios
SELECT * FROM produto; #Lista informacoes sobre um produto e sobre o(s) fabricante(s) do mesmo.
SELECT * FROM loja; #Listar informacoes sobre a loja.

# SELECT - OPERADORES DE COMPARACAO

SELECT * FROM usuario WHERE login = 'Thiago'; #Lista informações pessoais sobre um determinado usuário.
SELECT * FROM loja WHERE pais <> 'BR'; #Lista as loja que não são do Brasil
SELECT * FROM usuario WHERE cidade != 'Belo Jardim';#Lista os usuários que não são da cidade de Belo Jardim
SELECT nome,categoria,descricao,preco FROM produto WHERE preco > 200; #Lista os produtos com o preco maior que 200
SELECT * FROM produto WHERE data_entrega < '2022/01/20'; #Lista produtos com a data de entrega maior que a informada
SELECT login_usuario,cod_produto,data_compra FROM usuario_compra_produto WHERE cod_produto >= 3; #Lista os produtos comprados com o codigo maior ou igual a 3
SELECT nome,categoria,descricao,preco FROM produto WHERE preco <= 300;#Lista produtos comprados com o preco menor ou igual a 300

# SELECT - OPERADORES LOGICOS

SELECT * FROM cliente WHERE NOT status = 'Vip'; #Lista os clientes que não são vips
SELECT * FROM loja WHERE bairro = 'Morumbi' AND cidade = 'São Paulo';#Lista as lojas que são da cidade de SP e do bairro Morumbi
SELECT * FROM fornecedor WHERE bairro = 'Morumbi' OR bairro = 'Vila Sônia';#Lista as lojas que são do Bairro Morumbi ou do Bairro Vila Sônia

# SELECT - BETWEEN & NOT BETWEEN

SELECT * FROM produto WHERE preco BETWEEN (200) AND (500); #Lista os produtos que os preços estão no intervalo de 200 e 500
SELECT * FROM produto WHERE preco NOT BETWEEN (200) AND (500);#Lista os produtos que os preços não estão no intervalo de 200 e 500

# SELECT - LIKE & NOT LIKE

SELECT * FROM emails_usuario WHERE emails LIKE '%@hotmail.com'; #Lista os emails que possuem terminacao "@hotmail.com"
SELECT * FROM produto WHERE descricao LIKE 'memória%';#Lista os produtos que possuem descrição,inciada com a palavra memoria
SELECT * FROM fornecedor WHERE data_fornecimento LIKE '%12%';#Lista as informações dos fornecedores do mês de Dezembro
SELECT * FROM emails_usuario WHERE emails NOT LIKE '%@hotmail.com'; #Lista os emails não possuem terminacao "@hotmail.com"
SELECT * FROM produto WHERE descricao NOT LIKE 'memória%'; #Lista os produtos que não possuem descrição,inciada com a palavra memoria
SELECT * FROM fornecedor WHERE data_fornecimento NOT LIKE '%12%';#Lista as informações dos fornecedores que não são do mês de Dezembro

# SELECT - ORDER BY (ASC / DESC)

SELECT * FROM loja ORDER BY nome ASC; #Lista as lojas em ordem alfabetica de acordo com o nome
SELECT * FROM dependente WHERE sexo = 'm' ORDER BY nome ASC; #Lista os dependentes do sexo masculino,em ordem alfabetica
SELECT * FROM administrador ORDER BY cargo DESC; #Lista os administradores ordenados de trás pra frente,de acordo com o cargo
SELECT * FROM produto WHERE preco >= '500' ORDER BY nome DESC;#Lista os produtos com preco igual ou inferior a 500, ordenados de trás pra frente de acordo com o nome

# SELECT - IN & NOT IN

SELECT nome,login,cpf,cidade FROM usuario WHERE cidade IN ('Belo Jardim','Xucuru');#Lista os usuarios que pertencem a Belo jardim ou Xucuru
SELECT nome,login,cpf,cidade,bairro FROM usuario WHERE bairro NOT IN  ('Cohab 1','Igrejinha');#Lista os usuarios que não pertencem a Cohab 1 ou Igrejinha

# SELECT - FUNÇÕES DE GRUPO (max,min,avg,count,sum)

SELECT avg(preco) AS Media_dos_Precos FROM produto; #Retorna a media do precos dos produtos
SELECT max(preco) AS Maior_Preco FROM produto; #Retorna o maior preco dos produtos
SELECT min(preco) AS Menor_preco FROM produto; #Retorna o menor preco dos produtos
SELECT sum(preco) AS Soma_preco FROM produto; #Retorna a soma das precos dos produtos
SELECT count(*) AS total_Produtos FROM produto; #Retorna o total de produtos
SELECT count(*) AS quatidade_produtos FROM produto WHERE categoria LIKE '%dados%'; #Lista a quantidade de produtos de uma determinada categoria disponiveis a compra

# SELECT - CONSULTA ENCADEADA

SELECT nome,login,cpf,data_nascimento,idade FROM usuario
WHERE data_nascimento = (SELECT min(data_nascimento) FROM usuario); #retorna o usuário mais velho

SELECT nome,login,cpf,data_nascimento,idade FROM usuario
WHERE data_nascimento = (SELECT max(data_nascimento) FROM usuario); #retorna o usuário mais novo

SELECT nome,preco FROM produto WHERE preco =(SELECT min(preco) FROM produto); #nome produto com menor preco
SELECT nome,preco FROM produto WHERE preco =(SELECT max(preco) FROM produto); #nome produto com maior preco

# SELECT - CLAUSULA (DISTINCT)

SELECT DISTINCT pais FROM loja; #retorna todos os paises das lojas com restricao de repeticao
SELECT pais FROM loja;#retorna todos os paises de loja sem restrição de repeticao

# SELECT - JUNÇÕES

SELECT usuario_compra_produto.login_usuario,usuario_compra_produto.cod_produto,produto.nome,usuario_compra_produto.forma_pagamento,usuario_compra_produto.data_compra
FROM produto,usuario_compra_produto
WHERE produto.cod = usuario_compra_produto.cod_produto ORDER BY login_usuario ASC; #retorna os dados de compra do produto em ordem alfabetica do login

SELECT loja.cnpj,loja.nome,produto.cod,produto.nome
FROM produto,loja
WHERE produto.cnpj_loja = loja.cnpj ORDER BY cnpj DESC; #retorna os produtos de cada loja,ordenados de forma decrescente em relação ao cnpj

# Comando DML (Linguagem de Manipulacao de dados) para apagar dados da tabela

SELECT * FROM dados_bancarios_usuario;
DELETE FROM dados_bancarios_usuario;

SELECT * FROM emails_usuario;
DELETE FROM emails_usuario;

SELECT * FROM telefones_usuario;
DELETE FROM telefones_usuario;

SELECT * FROM dependente;
DELETE FROM dependente WHERE grau_parentesco = 'amigo';

#Comando DML (Linguagem de Manipulacao de dados) para atualizar dados da tabela

UPDATE produto SET preco = 1500 WHERE cod = 10;
SELECT * FROM produto WHERE cod = 10;

ALTER TABLE usuario
    MODIFY COLUMN tipo_usuario ENUM("admin", "padrao");

select * from usuario;