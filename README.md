<h1>🐾 API REST - Pet-Adota</h1>

<p>Projeto desenvolvido com <strong>Spring Boot</strong> para gerenciamento de animais disponíveis para adoção. A API permite o cadastro, atualização, listagem e desativação (exclusão lógica) de registros de animais.</p>

<p>Documentação Swagger disponível em: <a href="http://localhost:8080/swagger-ui/index.html#/">http://localhost:8080/swagger-ui/index.html#/</a></p>

<hr/>

<h2>📘 Sumário</h2>
<ul>
  <li><a href="#sobre">1. Sobre o Projeto</a></li>
  <li><a href="#tecnologias">2. Tecnologias Utilizadas</a></li>
  <li><a href="#estrutura">3. Estrutura da Aplicação</a></li>
  <li><a href="#endpoints">4. Endpoints da API</a></li>
  <li><a href="#dados-de-teste">5. Banco de Dados de Teste</a></li>
  <li><a href="#executar">6. Como Executar o Projeto</a></li>
  <li><a href="#colecao-postman">7. Coleção Postman</a></li>
</ul>

<hr/>

<h2 id="sobre">1. 📌 Sobre o Projeto</h2>
<p>O <strong>Pet-Adota</strong> é um sistema de adoção de animais que oferece uma interface RESTful para gerenciar registros de pets. O backend foi desenvolvido utilizando Spring Boot e segue boas práticas com divisão em camadas, uso de DTOs, tratamento de exceções e documentação com Swagger.</p>

<hr/>

<h2 id="tecnologias">2. 🛠️ Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17</li>
  <li>Spring Boot 3.4.5</li>
  <li>Spring Data JPA</li>
  <li>Spring Validation</li>
  <li>Flyway (migrações do banco)</li>
  <li>H2 Database (em memória)</li>
  <li>Springdoc OpenAPI (Swagger UI)</li>
  <li>Lombok</li>
  <li>Maven</li>
</ul>

<hr/>

<h2 id="estrutura">3. 📁 Estrutura da Aplicação</h2>

<h3>3.1 Entidade: <code>Animal</code></h3>
<p>A classe representa o animal disponível para adoção e está mapeada para a tabela <code>animal</code>.</p>

<ul>
  <li><strong>id</strong>: Identificador único do animal (Long)</li>
  <li><strong>nome</strong>: Nome do animal</li>
  <li><strong>tipoAnimal</strong>: Espécie (ex: Gato, Cachorro)</li>
  <li><strong>raca</strong>: Raça do animal</li>
  <li><strong>idade</strong>: Idade aproximada</li>
  <li><strong>statusAdocao</strong>: DISPONIVEL | ADOTADO</li>
  <li><strong>imagem</strong>: URL de uma imagem do animal</li>
  <li><strong>descricao</strong>: Características e observações</li>
  <li><strong>ativo</strong>: Controle de exclusão lógica (Boolean)</li>
</ul>

<h3>3.2 Camadas</h3>
<ul>
  <li><strong>Controller</strong>: Camada responsável por expor os endpoints HTTP</li>
  <li><strong>Service</strong>: Camada de regras de negócio</li>
  <li><strong>Repository</strong>: Interface de comunicação com o banco de dados (extends JpaRepository)</li>
  <li><strong>DTO</strong>: Transferência de dados e mapeamentos de entrada/saída</li>
</ul>

<hr/>

<h2 id="endpoints">4. 📡 Endpoints da API</h2>

<h3>Base URL:</h3>
<p><code>http://localhost:8080/animais</code></p>

<table border="1" cellpadding="6" cellspacing="0">
  <thead>
    <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  </thead>
  <tbody>
    <tr><td>POST</td><td>/animais</td><td>Cadastrar um novo animal</td></tr>
    <tr><td>GET</td><td>/animais</td><td>Listar todos os animais cadastrados (paginado)</td></tr>
    <tr><td>GET</td><td>/animais/ativos</td><td>Listar apenas os animais ativos</td></tr>
    <tr><td>GET</td><td>/animais/{id}</td><td>Buscar um animal por ID</td></tr>
    <tr><td>PUT</td><td>/animais/{id}</td><td>Atualizar dados de um animal existente</td></tr>
    <tr><td>DELETE</td><td>/animais/{id}</td><td>Realizar exclusão lógica do animal</td></tr>
  </tbody>
</table>

<h3>4.1 Exemplo de Payload - POST</h3>
<pre>
POST /animais
Content-Type: application/json

{
  "nome": "Mel",
  "tipoAnimal": "Gato",
  "raca": "Persa",
  "idade": 1,
  "statusAdocao": "DISPONIVEL",
  "imagem": "https://example.com/imagens/mel.jpg",
  "descricao": "Gatinha curiosa, muito carinhosa e silenciosa. Ideal para apartamentos."
}
</pre>

<h3>4.2 Exemplo de Payload - PUT</h3>
<pre>
PUT /animais/4
Content-Type: application/json

{
  "nome": "Mavie",
  "tipoAnimal": "Gato",
  "raca": "Persa",
  "idade": 1,
  "statusAdocao": "DISPONIVEL",
  "imagem": "https://example.com/imagens/mel.jpg",
  "descricao": "Atualização da descrição."
}
</pre>

<h3>4.3 Exclusão Lógica</h3>
<p>Ao executar um DELETE em <code>/animais/{id}</code>, o campo <code>ativo</code> do animal será alterado para <code>false</code>, preservando o registro no banco de dados.</p>

<hr/>

<h2 id="dados-de-teste">5. 🧪 Dados de Teste e Banco de Dados</h2>

<h3>5.1 Configuração do banco H2</h3>
<ul>
  <li>URL: <code>jdbc:h2:mem:petadota</code></li>
  <li>Usuário: <code>sa</code></li>
  <li>Senha: (vazio)</li>
  <li>Console: <code>http://localhost:8080/h2-console</code></li>
</ul>

<h3>5.2 Script Flyway (V1__create_animal_table.sql)</h3>
<pre>
CREATE TABLE animal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo_animal VARCHAR(50),
    raca VARCHAR(100),
    idade INT,
    status_adocao VARCHAR(50),
    imagem VARCHAR(255),
    descricao TEXT,
    ativo Boolean
);

INSERT INTO animal (nome, tipo_animal, raca, idade, status_adocao, imagem, descricao, ativo) VALUES
('Luna', 'Cachorro', 'Labrador', 3, 'DISPONIVEL', 'https://example.com/imagens/luna.jpg', 'Cachorra dócil e brincalhona.', true),
('Milo', 'Gato', 'Siamês', 2, 'ADOTADO', 'https://example.com/imagens/milo.jpg', 'Gato calmo, adora colo e carinho.', true),
('Thor', 'Cachorro', 'Husky Siberiano', 4, 'DISPONIVEL', 'https://example.com/imagens/thor.jpg', 'Husky enérgico, ideal para famílias ativas.', true),
('Bella', 'Gato', 'Persa', 5, 'DISPONIVEL', 'https://example.com/imagens/bella.jpg', 'Gata tranquila e silenciosa.', true),
('Max', 'Cachorro', 'Beagle', 3, 'ADOTADO', 'https://example.com/imagens/max.jpg', 'Beagle curioso e amigável.', true),
('Nina', 'Cachorro', 'Poodle', 6, 'DISPONIVEL', 'https://example.com/imagens/nina.jpg', 'Poodle muito inteligente e dócil.', true),
('Simba', 'Gato', 'Maine Coon', 2, 'DISPONIVEL', 'https://example.com/imagens/simba.jpg', 'Grande e carinhoso, ideal para lares com crianças.', false),
('Bob', 'Cachorro', 'Golden Retriever', 4, 'ADOTADO', 'https://example.com/imagens/bob.jpg', 'Muito companheiro e obediente.', true),
('Lili', 'Gato', 'Angorá', 1, 'DISPONIVEL', 'https://example.com/imagens/lili.jpg', 'Pequena e muito brincalhona.', false),
('Toby', 'Cachorro', 'Bulldog', 5, 'ADOTADO', 'https://example.com/imagens/toby.jpg', 'Bulldog calmo e carinhoso.', true);
</pre>

<hr/>

<h2 id="executar">6. ▶️ Como Executar o Projeto</h2>

<ol>
  <li>Clone o repositório: <code>git clone https://github.com/seuusuario/pet-adota.git</code></li>
  <li>Acesse o projeto: <code>cd pet-adota</code></li>
  <li>Execute com Maven: <code>./mvnw spring-boot:run</code></li>
  <li>Acesse:
    <ul>
      <li>Swagger: <code>http://localhost:8080/swagger-ui/index.html</code></li>
      <li>Console H2: <code>http://localhost:8080/h2-console</code></li>
    </ul>
  </li>
</ol>

<hr/>

<h2 id="colecao-postman">7. 🔁 Coleção Postman</h2>
<p>Você pode importar a seguinte coleção no Postman:</p>

<pre>
{{ _.url }} = http://localhost:8080

POST: {{ _.url }}/animais
GET:  {{ _.url }}/animais
GET:  {{ _.url }}/animais/ativos
GET:  {{ _.url }}/animais/{id}
PUT:  {{ _.url }}/animais/{id}
DELETE: {{ _.url }}/animais/{id}
</pre>

<hr/>
