# Iast, Sast e Dast

**`falsos positivos`**: significa algo que parece estar tudo bem, ta passando no teste, e quando chega em ambiente de produção, não tá funcionando como esperado

## SAST - Static Application Security Test

Atua no código-fonte (que pode ser tanto o código normal, o bytecode compilado ou o binário) do produto, verificando erros de sintaxe e coisas do tipo, sem rodar o app

### Vantagens

- Descobrem falhas de segurança complexas em pouco tempo
- Simples de fazer (é literalmente debuggar, ler cada linha e raciocinar se aquela lógica encaminha até a solução)

### Desvantagens

- Em grandes projetos, é bem complicado porque tem muito código
- Como não é testado em ambiente de produção, muitas regras de negócio não são testadas

### Ferramentas

- ESLint e Biome (JS / TS)
- SonarQube
- Checkmarx
- Bandit (Python)

## DAST - Dinamic Application Security Test

Verifica como as informações são contidas dentro da rotina da aplicação (input, output, tempo de resposta, latência, entre outros).
Diferente do SAST, que o bom é que seja feito frequentemente, o DAST não é a melhor alternativa para isso
Simula ataques reais ao sistema (caixa preta), como:
 - XSS
 - CSRF
 - SQL Injection
 - Problemas de autenticação / autorização

### Vantagens

- DAST geralmente é feito com o sistema rodando
- Detecta falhas de rede, performance, problemas de autenticação, 
- Tem um custo benefício maior 
- Apresenta poucos falsos positivos

### Desvantagens

- Não fornece informações sobre as causas dessas vulnerabilidades
- Poder apresentar problemas para manter um padrão de codificação nos relatórios
- E o primeiro ponto das vantagens do DAST também pode ser uma desvantagem, caso estejamos falando de sistemas em estágios iniciais 

### Ferramentas 

A principal atualmente é o **Burp Suite**

## IAST - Interactive Application Security Test

Combina o SAST com o DAST, analisando o app em tempo de execução com acesso interno ao código e contexto

### Como funciona

**"Instalando um agente na aplicação durante os testes funcionais ou QA, Ele observa como o código se comporta em tempo real e verifica vulnerabilidades com maior precisão"**

### Vantagens 

- Bem menos falsos positivos, pois tem acesso a execução real + código
- Detecta vulnerabilidades precisas com o contexto
- Útil em ambientes de teste e integração contínua (CI/CD).

### Desvantagens

- Requer instrumentação da aplicação (pode impactar performance).
- Pode ser mais complexo de configurar.
- Menor quantidade de ferramentas disponíveis comparado ao SAST e DAST.

### Ferramentas

- Contrast Security
- Seeker (Synopsys)
- AppScan IAST
- Hdiv Security