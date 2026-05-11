# UNI3 — Aula 1: Referência de Métodos

Guia detalhado de **cada método** implementado nas classes `NoMapa`, `MapaDispersao` e `Aluno`.

---

## 🔷 Classe `NoMapa<T>`

Nó que armazena um par **chave → valor** no mapa de dispersão.

### `NoMapa(int chave, T valor)`
**Construtor** — cria um nó com chave e valor.

```java
NoMapa<Aluno> no = new NoMapa<>(12000, aluno);
```

| Parâmetro | Tipo | Descrição |
|:----------|:----:|:----------|
| `chave` | `int` | Identificador numérico para calcular o hash |
| `valor` | `T` | Objeto armazenado (ex: Aluno) |

---

### `int getChave()`
Retorna a chave do nó.

```java
int c = no.getChave(); // 12000
```

---

### `void setChave(int chave)`
Altera a chave do nó.

```java
no.setChave(15000);
```

---

### `T getValor()`
Retorna o valor (objeto) armazenado no nó.

```java
Aluno a = no.getValor();
```

---

### `void setValor(T valor)`
Altera o valor armazenado.

```java
no.setValor(outroAluno);
```

---

### `boolean equals(Object o)`
Compara dois `NoMapa` **apenas pela chave** (ignora o valor).

Isso é essencial para que a `ListaEncadeada.buscar()` funcione: criamos um `NoMapa` temporário com a chave desejada e valor `null`, e o `equals()` encontra o nó correto na lista.

```java
NoMapa<Aluno> busca = new NoMapa<>(12000, null);
NoMapa<Aluno> real  = new NoMapa<>(12000, aluno);
busca.equals(real); // true — mesma chave, valor é ignorado
```

**Implementação:**
```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NoMapa<?> noMapa = (NoMapa<?>) o;
    return chave == noMapa.chave;
}
```

> ⚠️ **Ponto de prova:** o `equals` usa **apenas** `chave`. Se comparasse o `valor` também, a busca não funcionaria (o valor de busca é `null`).

---

## 🔷 Classe `MapaDispersao<T>`

Estrutura principal — mapa de dispersão com endereçamento separado.

### `MapaDispersao(int tamanho)`
**Construtor** — cria o vetor interno com `tamanho` posições, cada uma inicializada com uma `ListaEncadeada` vazia.

```java
MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);
```

**O que acontece internamente:**
```
info[0]  → ListaEncadeada vazia
info[1]  → ListaEncadeada vazia
...
info[52] → ListaEncadeada vazia
```

**Implementação:**
```java
@SuppressWarnings("unchecked")
public MapaDispersao(int tamanho) {
    info = new ListaEncadeada[tamanho];
    for (int i = 0; i < tamanho; i++) {
        info[i] = new ListaEncadeada<>();
    }
}
```

> 💡 O `@SuppressWarnings("unchecked")` é necessário porque Java não permite criar arrays genéricos diretamente (`new ListaEncadeada<NoMapa<T>>[tamanho]` daria erro).

---

### `int calcularHash(int chave)`
Calcula o índice no vetor usando o **método da divisão** (resto).

```java
int indice = mapa.calcularHash(12000); // 12000 % 53 = 24
```

**Implementação:**
```java
public int calcularHash(int chave) {
    return chave % info.length;
}
```

**Exemplos com vetor de tamanho 53:**

| Chave | Hash (chave % 53) |
|:-----:|:-----------------:|
| 12000 | 24 |
| 14000 | 6 |
| 14226 | 24 ← colide com 12000 |
| 17180 | 6 ← colide com 14000 |
| 12500 | 42 |
| 13000 | 16 |

---

### `void inserir(int chave, T dado)`
Insere um objeto no mapa, associado à chave informada.

**Passo a passo:**
1. Calcula o hash da chave → obtém o índice
2. Cria um `NoMapa` com a chave e o dado
3. Insere o `NoMapa` na lista encadeada daquele índice

```java
Aluno jean = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
mapa.inserir(12000, jean);
// hash(12000) = 24 → info[24].inserir(NoMapa(12000, jean))
```

**Implementação:**
```java
public void inserir(int chave, T dado) {
    int indice = calcularHash(chave);
    NoMapa<T> novoNo = new NoMapa<>(chave, dado);
    info[indice].inserir(novoNo);
}
```

**Visualização após inserir Jean(12000) e Marta(14226):**
```
info[24] → [14226|Marta] → [12000|Jean] → null
```
> A lista insere **no início**, então Marta fica antes de Jean.

---

### `T buscar(int chave)`
Procura no mapa o objeto com a chave informada e o retorna.

**Passo a passo:**
1. Calcula o hash da chave → obtém o índice
2. Cria um `NoMapa` temporário com a chave e valor `null`
3. Usa `ListaEncadeada.buscar()` que percorre a lista e usa `equals()` (que compara apenas `chave`)
4. Se encontrou, retorna o **valor** do nó; senão, retorna `null`

```java
Aluno resultado = mapa.buscar(12000); // retorna o objeto Jean
Aluno naoExiste = mapa.buscar(99999); // retorna null
```

**Implementação:**
```java
public T buscar(int chave) {
    int indice = calcularHash(chave);
    NoMapa<T> chaveBusca = new NoMapa<>(chave, null);

    NoLista<NoMapa<T>> resultado = info[indice].buscar(chaveBusca);

    if (resultado != null) {
        return resultado.getInfo().getValor();
    }

    return null;
}
```

> 💡 O truque: `NoMapa(chave, null)` funciona como "critério de busca" porque `equals()` compara **apenas a chave**.

---

### `void remover(int chave)`
Remove do mapa o objeto que possui a chave informada.

**Passo a passo:**
1. Calcula o hash da chave → obtém o índice
2. Cria um `NoMapa` temporário com a chave (valor `null`)
3. Usa `ListaEncadeada.retirar()` que encontra e remove o nó com `.equals()` correspondente

```java
mapa.remover(12000); // remove Jean do mapa
```

**Implementação:**
```java
public void remover(int chave) {
    int indice = calcularHash(chave);
    NoMapa<T> chaveBusca = new NoMapa<>(chave, null);
    info[indice].retirar(chaveBusca);
}
```

**Visualização — antes e depois de remover 12000:**
```
ANTES: info[24] → [14226|Marta] → [12000|Jean] → null
DEPOIS: info[24] → [14226|Marta] → null
```

---

### `double calcularFatorCarga()`
Calcula e retorna o **fator de carga** do mapa.

**Fórmula:** `totalDeElementos / tamanhoDovetor`

```java
double fator = mapa.calcularFatorCarga();
// Com 2 elementos e vetor de 53 → 2/53 ≈ 0.0377
```

**Implementação:**
```java
public double calcularFatorCarga() {
    int totalElementos = 0;
    for (int i = 0; i < info.length; i++) {
        totalElementos += info[i].obterComprimento();
    }
    return (double) totalElementos / info.length;
}
```

> 💡 Percorre **todas** as listas do vetor e soma seus comprimentos. Complexidade: O(m), onde m é o tamanho do vetor.

---

## 🔷 Classe `Aluno`

Classe de domínio usada como tipo `T` nos testes.

### `Aluno(int matricula, String nome, LocalDate dataNascimento)`
**Construtor** — cria um aluno com todos os atributos.

```java
Aluno a = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
```

### Getters e Setters

| Método | Retorno | Descrição |
|:-------|:-------:|:----------|
| `getMatricula()` | `int` | Retorna a matrícula |
| `setMatricula(int)` | `void` | Altera a matrícula |
| `getNome()` | `String` | Retorna o nome |
| `setNome(String)` | `void` | Altera o nome |
| `getDataNascimento()` | `LocalDate` | Retorna a data de nascimento |
| `setDataNascimento(LocalDate)` | `void` | Altera a data de nascimento |

### `String toString()`
Retorna representação textual do aluno para debug.

```java
System.out.println(aluno);
// Aluno{matricula=12000, nome='Jean', dataNascimento=2000-01-01}
```

---

## 🔷 Testes (`MapaDispersaoTest`)

### Testes do Plano PL01

| Teste | Método | O que valida |
|:-----:|:-------|:-------------|
| Caso 1 | `testeCaso1_inserirEBuscarUmAluno()` | Inserir 1 aluno e buscar — `assertSame` garante mesma referência |
| Caso 2 | `testeCaso2_inserirVariosAlunosSemColisao()` | Inserir 4 alunos sem colisão e buscar todos |
| Caso 3 | `testeCaso3_inserirComColisao()` | Inserir com colisões (14226↔12000, 17180↔14000) e buscar todos |

### Testes Complementares

| Teste | Método | O que valida |
|:-----:|:-------|:-------------|
| 4 | `testeBuscarChaveInexistente()` | Busca por chave que não existe retorna `null` |
| 5 | `testeRemoverAluno()` | Inserir, verificar, remover, verificar que sumiu |
| 6 | `testeRemoverComColisao()` | Remover 12000 sem afetar 14226 (que colide) |
| 7 | `testeCalcularFatorCarga()` | Mapa vazio = 0.0; com 2 elementos = 2/53 |
| 8 | `testeCalcularHash()` | Verifica cálculos de hash e confirma colisões esperadas |

---

## 🎯 Resumo Rápido — Como Tudo se Conecta

```
mapa.inserir(12000, jean)
    │
    ▼
calcularHash(12000)  →  24
    │
    ▼
info[24].inserir(NoMapa(12000, jean))
    │
    ▼
ListaEncadeada adiciona ao início


mapa.buscar(12000)
    │
    ▼
calcularHash(12000)  →  24
    │
    ▼
info[24].buscar(NoMapa(12000, null))
    │                        ▲
    ▼                        │
Percorre lista, usa equals() que compara só chave
    │
    ▼
Encontra NoMapa(12000, jean) → retorna jean
```
