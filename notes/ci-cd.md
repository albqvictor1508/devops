# Pipeline (CI/CD)

É uma sequência de etapas que precisam ser realizadas para pôr a aplicação em produção

Ex.:
    Build do código, executar testes automatizados, implantação em ambiente de teste e de dev

## Continuos Integration - CI

Automatização de build e teste de código quando um dev commita algo no repo

## Continuos Delivery - CD

Entregar novos recursos aos usuários da forma mais rápida e eficiente possível

- Permite um fluxo constante de atualizações disponibilizadas em produção, por uma espécie de **linha de produção de software automatizada**, ou seja, uma esteira

Geralmente ligado à metodologia Agile

### Resumo

CI = cuida no tratamento do código, onde faz o build e executa testes e só passa se os testes derem certo
CD = garante a entrega do código tratado ao destino final, como por exemplo, a máquina virtual onde a aplicação está rodando, fazendo deploy em produção, deploy em staging (homolog), entre outros