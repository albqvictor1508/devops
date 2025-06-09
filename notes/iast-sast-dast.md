# Iast, Sast e Dast

**`falsos positivos`**: significa algo que parece estar tudo bem, ta passando no teste, e quando chega em ambiente de produção, não tá funcionando como esperado

## SAST - Static Application Security Test

Atua no código-fonte do produto, verificando erros de sintaxe e coisas do tipo, sem rodar o app

### Vantagens

- Descobrem falhas de segurança complexas em pouco tempo
- Simples de fazer (é literalmente debuggar, ler cada linha e raciocinar se aquela lógica encaminha até a solução)

### Desvantagens

- Em grandes projetos, é bem complicado porque tem muito código
- Como não é testado em ambiente de produção, muitas regras de negócio não são testadas

## DAST - Dinamic Application Security Test

Verifica como as informações são contidas dentro da rotina da aplicação (input, output, tempo de resposta, latência, entre outros).
Além de que, diferente do SAST, que o bom é que seja feito frequentemente, o DAST não é a melhor alternativa para isso

### Vantagens

- DAST geralmente é feito com o sistema rodando
- Detecta falhas de rede, performance, problemas de autenticação, 
- Tem um custo benefício maior 
- Apresenta poucos falsos positivos

### Desvantagens

- Não fornece informações sobre as causas dessas vulnerabilidades
- Poder apresentar problemas para manter um padrão de codificação nos relatórios
- E o primeiro ponto das vantagens do DAST também pode ser uma desvantagem, caso estejamos falando de sistemas em estágios iniciais 