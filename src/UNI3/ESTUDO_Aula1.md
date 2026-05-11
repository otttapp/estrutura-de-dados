# UNI3 — Aula 1: Mapa de Dispersão (Hash Map)

## 📌 Contexto Geral

Esta aula introduz o conceito de **Mapa de Dispersão** (Hash Map / Tabela Hash), uma estrutura de dados fundamental que permite **inserção, busca e remoção em tempo médio O(1)**.

### O que é um Mapa de Dispersão?

É uma estrutura que armazena pares **chave → valor** e usa uma **função hash** para calcular o índice onde cada par será armazenado em um vetor interno. A grande vantagem é que, ao invés de percorrer todos os elementos para encontrar algo (como numa lista), a função hash calcula diretamente *onde* o elemento está.

### Tratamento de Colisões por Endereçamento Separado

Quando duas chaves diferentes produzem o **mesmo hash** (colisão), ambos os elementos precisam ser armazenados no mesmo índice. A estratégia usada nesta aula é o **endereçamento separado** (separate chaining): cada posição do vetor contém uma **lista encadeada** que armazena todos os elementos que colidiram naquele índice.

```
Vetor (info[]):
┌────────────────────────────────────────┐
│ [0] → ListaEncadeada → vazia           │
│ [1] → ListaEncadeada → vazia           │
│ ...                                    │
│ [6] → ListaEncadeada → [14000|Pedro] → [17180|Lucas] → null   │
│ ...                                    │
│ [24] → ListaEncadeada → [12000|Jean] → [14226|Marta] → null   │
│ ...                                    │
│ [52] → ListaEncadeada → vazia          │
└────────────────────────────────────────┘
```

### Função Hash — Método da Divisão

A função hash utilizada é o **resto da divisão** (módulo):

```
hash(chave) = chave % tamanho_do_vetor
```

Exemplo com vetor de tamanho 53:
- `12000 % 53 = 24`
- `14226 % 53 = 24` → **colisão com 12000!**
- `14000 % 53 = 6`
- `17180 % 53 = 6`  → **colisão com 14000!**

### Fator de Carga (Load Factor)

O fator de carga mede quão "cheia" está a tabela:

```
fatorCarga = totalDeElementos / tamanhoDovetor
```

- **Fator baixo** (ex: 0.1) → poucas colisões, mas desperdício de memória
- **Fator alto** (ex: 2.0) → muitas colisões, desempenho degrada para O(n)
- **Ideal**: entre 0.5 e 0.75

---

## 🏗️ Diagrama de Classes

```
┌───────────────────────────────────────┐     ┌──────────────────────────┐
│          MapaDispersao<T>             │     │       NoMapa<T>          │
├───────────────────────────────────────┤     ├──────────────────────────┤
│ - info[] : ListaEncadeada<NoMapa<T>>  │────▶│ - chave : int            │
├───────────────────────────────────────┤     │ - valor : T              │
│ + MapaDispersao(tamanho : int)        │     ├──────────────────────────┤
│ + calcularHash(chave : int) : int     │     │ + getChave() : int       │
│ + inserir(chave : int, dado : T)      │     │ + setChave(int) : void   │
│ + remover(chave : int) : void         │     │ + getValor() : T         │
│ + buscar(chave : int) : T             │     │ + setValor(T) : void     │
│ + calcularFatorCarga() : double       │     │ + equals(Object):boolean │
└───────────────────────────────────────┘     └──────────────────────────┘

┌──────────────────────────────────────────────────┐
│                   Aluno                          │
├──────────────────────────────────────────────────┤
│ - matricula : int                                │
│ - nome : String                                  │
│ - dataNascimento : LocalDate                     │
├──────────────────────────────────────────────────┤
│ + Aluno(int, String, LocalDate)                  │
│ + getMatricula() / setMatricula()                │
│ + getNome() / setNome()                          │
│ + getDataNascimento() / setDataNascimento()      │
└──────────────────────────────────────────────────┘
```

---

## 🔑 Conceitos-Chave para a Prova

### 1. Por que a identidade do NoMapa usa apenas `chave`?
O `equals()` compara apenas a `chave` e ignora o `valor`. Isso permite que o método `buscar()` da `ListaEncadeada` encontre um nó usando apenas uma chave de busca (com valor `null`), sem precisar saber o valor armazenado.

### 2. Por que o tamanho do vetor costuma ser um número primo (ex: 53)?
Números primos distribuem melhor os hashes, reduzindo colisões. Se o tamanho fosse 50, todas as chaves múltiplas de 50 iriam para o índice 0.

### 3. Complexidade de tempo

| Operação       | Caso médio | Pior caso (todas as chaves colidem) |
|:---------------|:----------:|:-----------------------------------:|
| `inserir`      | O(1)       | O(n)                                |
| `buscar`       | O(1)       | O(n)                                |
| `remover`      | O(1)       | O(n)                                |
| `calcularHash` | O(1)       | O(1)                                |
| `fatorCarga`   | O(m)       | O(m) — onde m = tamanho do vetor    |

### 4. Reuso da `ListaEncadeada` de UNI1.aula3
A implementação reutiliza a `ListaEncadeada<T>` e `NoLista<T>` da aula 3 da UNI1. Os métodos usados são:
- `inserir(T)` → insere no início da lista
- `buscar(T)` → percorre a lista usando `.equals()` para encontrar o nó
- `retirar(T)` → remove o nó cujo info é `.equals()` ao argumento
- `obterComprimento()` → conta os elementos da lista

---

## 📋 Plano de Testes (PL01)

| Caso | Objetivo | Chaves | Colisões? | Resultado Esperado |
|:----:|:---------|:-------|:---------:|:-------------------|
| 1 | Inserir e buscar 1 aluno | 12000 | Não | Mesmo objeto (referência) retornado |
| 2 | Inserir e buscar 4 alunos | 12000, 14000, 12500, 13000 | Não | Todos encontrados com mesma referência |
| 3 | Inserir com colisão e buscar | 12000, 14000, 14226, 17180 | 14226↔12000, 17180↔14000 | Todos encontrados com mesma referência |

### Verificação das colisões (Caso 3):
```
12000 % 53 = 24    ←┐
14226 % 53 = 24    ←┘  Colidem no índice 24

14000 % 53 = 6     ←┐
17180 % 53 = 6     ←┘  Colidem no índice 6
```

---

## 📁 Arquivos Criados

```
UNI3/
└── aula1/
    ├── NoMapa.java            → Nó do mapa (chave + valor)
    ├── MapaDispersao.java     → Estrutura principal (hash map)
    ├── Aluno.java             → Classe de domínio para testes
    └── MapaDispersaoTest.java → 8 testes unitários (JUnit 5)
```
