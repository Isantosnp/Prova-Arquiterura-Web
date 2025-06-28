API de Autenticação JWT com Spring Boot e Monitoramento
Esta é uma API RESTful de autenticação e autorização que utiliza JSON Web Tokens (JWT) para proteger endpoints. O projeto inclui funcionalidades de login, autenticação e autorização baseada em papéis (Admin e User), além de configuração para monitoramento com Prometheus e Grafana, e containerização com Docker para facilitar o deploy.

🚀 Tecnologias Utilizadas
Java 17: Linguagem de programação principal.

Spring Boot: Framework para construção de aplicações Java robustas.

Spring Security: Para segurança e autenticação/autorização.

JWT (JSON Web Tokens): Para autenticação sem estado.

Maven: Gerenciador de dependências e automação de build.

H2 Database: Banco de dados em memória para desenvolvimento e testes.

Spring Boot Actuator: Para expor informações de monitoramento e métricas.

Micrometer: Coleta de métricas no formato Prometheus.

Docker: Para containerização da aplicação.

Prometheus: Sistema de monitoramento e alerta de métricas.

Grafana: Plataforma para visualização e criação de dashboards a partir das métricas.

✨ Funcionalidades
Autenticação de Usuários: Login com username e password.

Geração de JWT: Emissão de tokens JWT após login bem-sucedido.

Validação de Tokens JWT: Verificação da validade e integridade dos tokens recebidos.

Endpoints Protegidos: Acesso restrito a usuários autenticados.

Autorização por Papéis: Endpoints que exigem papéis específicos (ex: ADMIN).

Monitoramento de Aplicação: Exposição de métricas de saúde, desempenho e requisições via Spring Boot Actuator.

🛠️ Como Rodar Localmente
Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

Java Development Kit (JDK) 17

Maven

Docker (opcional, se for rodar via contêiner)

1. Clonar o Repositório

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio # Navegue para a pasta do projeto

2.Gerar a chave secreta
3. Executar a Aplicação (Sem Docker)
4. Executar a Aplicação (Com Docker)

📍 Endpoints da API
A API expõe os seguintes endpoints:

POST /auth/login: Autentica um usuário.

Parâmetros: username (string), password (string).

Retorna: Um JWT em caso de sucesso.

GET /api/hello: Endpoint protegido que exige um JWT válido.

GET /api/admin: Endpoint protegido que exige um JWT válido e o papel ADMIN.

GET /actuator/health: Verifica a saúde da aplicação.

GET /actuator/metrics: Lista todas as métricas disponíveis.

GET /actuator/prometheus: Expõe métricas no formato Prometheus para coleta.

📊 Monitoramento com Prometheus e Grafana
Este projeto é configurado para ser monitorado usando Spring Boot Actuator, Prometheus e Grafana.

1. Configurar Prometheus
Baixe o Prometheus: Faça o download do executável do Prometheus em prometheus.io/download/ e extraia-o para uma pasta de sua escolha (fora do seu projeto).

Edite prometheus.yml: Na pasta do Prometheus, edite o arquivo prometheus.yml para adicionar sua aplicação como um alvo de coleta (scrape target).

📊 Monitoramento com Prometheus e Grafana
Este projeto é configurado para ser monitorado usando Spring Boot Actuator, Prometheus e Grafana.

1. Configurar Prometheus
Baixe o Prometheus: Faça o download do executável do Prometheus em prometheus.io/download/ e extraia-o para uma pasta de sua escolha (fora do seu projeto).

Edite prometheus.yml: Na pasta do Prometheus, edite o arquivo prometheus.yml para adicionar sua aplicação como um alvo de coleta (scrape target).

YAML

# ... (outras configurações padrão do prometheus.yml)

scrape_configs:
  # ... (job_name 'prometheus' padrão)

  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080'] # Altere a porta se sua aplicação estiver em outra
Inicie o Prometheus: Na pasta do Prometheus, execute:

Bash

./prometheus --config.file=prometheus.yml
A interface web do Prometheus estará disponível em http://localhost:9090. Verifique em Status > Targets se spring-boot-app está UP.

2. Configurar Grafana
Baixe e Instale o Grafana: Faça o download do Grafana em grafana.com/grafana/download/ e instale-o ou extraia-o.

Inicie o Grafana: Navegue até o diretório bin da sua instalação do Grafana e execute grafana-server (ou grafana-server.exe no Windows).

Bash

cd grafana-10.x.x/bin
./grafana-server
O Grafana estará disponível em http://localhost:3000. Faça login com admin/admin (e altere a senha).

Adicione Prometheus como Data Source:

No Grafana, vá em Configuration (engrenagem) > Data sources.

Clique em Add data source e selecione Prometheus.

Defina o Name (ex: Prometheus Local) e a URL como http://localhost:9090.

Clique em Save & test.

Importe um Dashboard (Recomendado):

No Grafana, vá em Create (+) > Import.

No campo "Import via grafana.com", insira o ID do Dashboard 10287 (para JVM/Micrometer) e clique em Load.

Selecione Prometheus Local (ou o nome que você deu) como a fonte de dados do Prometheus.

Clique em Import.

Explore as métricas da sua aplicação no dashboard importado!

🚀 Deploy do Projeto
Esta aplicação está configurada para fácil deploy usando Docker em plataformas de contêiner.

1. Containerização com Docker
O Dockerfile na raiz do projeto permite construir uma imagem Docker da sua aplicação:

Dockerfile

# ... (conteúdo do Dockerfile conforme mostrado anteriormente) ...
Para construir a imagem (após clonar o projeto):

Bash

docker build -t minha-api-jwt .
2. Hospedagem em Plataformas Gratuitas
Você pode fazer o deploy desta aplicação em plataformas como Render ou Railway, que suportam imagens Docker e integração com GitHub.

Render
Crie uma conta em Render.com.

Conecte sua conta do GitHub.

Crie um novo "Web Service" e selecione o repositório deste projeto.

Crucial: Na seção de Variáveis de Ambiente, adicione JWT_SECRET_KEY e insira sua chave secreta real e segura (nunca use a de desenvolvimento).

Defina a porta interna do contêiner como 8080.

O Render fará o build da imagem Docker e o deploy da sua API.

