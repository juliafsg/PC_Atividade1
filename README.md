# PC_Atividade1

## Autores:
- Gabriel Estevam Narciso
- Júlia Ferreira de Souza Glória


## Objetivos
- Implementar solução concorrente e sequencial para a multiplicação de matrizes
- Realizar uma comparação entre soluções concorrentes e soluções sequenciais quanto ao tempo de execução

## Metodologia
- Foram utilizadas como amostra matrizes quadradas com dimensões de 4x4 até 2048x2048 todas como potências de base 2.
- Para gerar os tempos de execução executamos o programa 20x em cada método.

## Execução
O programa possui dois métodos main, um está no MainThread.java e outro no MainExperimento.java

### MainThread.java
- **Descrição:** Esse programa irá apenas fazer a multiplicação entre duas matrizes.

- **Entrada:** Dois argumentos D e M, D = dimensão da matriz {4,8,16,32...} e M = método de execução {'C' = concorrente, 'S' = sequencial }. 

- **ex:** $ java MainThread 4 C

- **Saida:** Arquivo .txt contento o resultado da multiplicação entre as matrizes. 

### MainExperimento.java
- **Descrição:** Esse programa irá fazer a multiplicação das matrizes de todas as dimensões 20x no método sequencial e 20x no método concorrente.

- **Entrada:** Não recebe nenhum argumento.

- **ex:** $ java MainExperimento

- **Saida:** Vários aqruivos .txt contento o resultado da multiplicação entre as matrizes e um arquivo .csv para cada dimensão contendo os tempos de execução. 
