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
Detecta falhas de rede, performance, problemas de autenticação, tem um custo benefício maior e apresenta poucos falsos positivos



### Vantagens

### Desvantagens