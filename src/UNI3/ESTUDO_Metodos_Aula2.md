# UNI3 — Aula 2: Referência de Métodos

Guia detalhado de **cada método** implementado nas classes `NoMapa<K,T>`, `MapaDispersao<K,T>` e `Veiculo`.

---

## 🔷 Classe `NoMapa<K, T>`

Nó que armazena um par **chave → valor** no mapa de dispersão, agora com **chave genérica**.

### `NoMapa(K chave, T valor)`
**Construtor** — cria um nó com chave genérica e valor.

```java
NoMapa<String, Veiculo> no = new NoMapa<>("AXQ-3041", veiculo);
```

| Parâmetro | Tipo | Descrição |
|:----------|:----:|:----------|
| `chave` | `K` | Identificador genérico (ex: String para placa) |
| `valor` | `T` | Objeto armazenado (ex: Veiculo) |

---

### `K getChave()`
Retorna a chave do nó (agora do tipo genérico K).

```java
String placa = no.getChave(); // "AXQ-3041"
```

---

### `void setChave(K chave)`
Altera a chave do nó.

```java
no.setChave("MSE-7521");
```

---

### `T getValor()`
Retorna o valor (objeto) armazenado no nó.

```java
Veiculo v = no.getValor();
```

---

### `void setValor(T valor)`
Altera o valor armazenado.

```java
no.setValor(outroVeiculo);
```

---

### `boolean equals(Object o)`
Compara dois `NoMapa` **apenas pela chave** (ignora o valor).

Diferença da aula 1: agora usa `chave.equals()` ao invés de `==`, pois a chave é um objeto (ex: String) e não um primitivo (int).

```java
NoMapa<String, Veiculo> busca = new NoMapa<>("AXQ-3041", null);
NoMapa<String, Veiculo> real  = new NoMapa<>("AXQ-3041", veiculo);
busca.equals(real); // true — mesma chave, valor é ignorado
```

**Implementação:**
```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NoMapa<?, ?> noMapa = (NoMapa<?, ?>) o;
    return chave.equals(noMapa.chave);  // ← usa .equals() e não ==
}
```

> ⚠️ **Ponto de prova:** Na aula 1 usava `chave == noMapa.chave` (comparação de int). Na aula 2 usa `chave.equals(noMapa.chave)` (comparação de objetos). Usar `==` com String compararia **referências**, não conteúdo!

---

### `int hashCode()`
Delega o cálculo do hashCode para a chave, mantendo o contrato `equals/hashCode`.

**Implementação:**
```java
@Override
public int hashCode() {
    return chave.hashCode();
}
```

> 💡 **Regra do Java:** Se dois objetos são `equals()`, seus `hashCode()` **devem** ser iguais. Sem essa implementação, o contrato seria violado.

---

## 🔷 Classe `MapaDispersao<K, T>`

Estrutura principal — mapa de dispersão com chaves genéricas e endereçamento separado.

### `MapaDispersao(int tamanho)`
**Construtor** — cria o vetor interno com `tamanho` posições (buckets), cada uma inicializada com uma `ListaEncadeada` vazia.

```java
MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);
```

**O que acontece internamente:**
```
info[0] → ListaEncadeada vazia
info[1] → ListaEncadeada vazia
info[2] → ListaEncadeada vazia
info[3] → ListaEncadeada vazia
info[4] → ListaEncadeada vazia
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

---

### `int calcularHash(K chave)`
Calcula o índice no vetor usando `hashCode()` da chave com valor absoluto.

**Diferença crítica da aula 1:**
- Aula 1: `chave % info.length` (chave era int, sempre positivo)
- Aula 2: `Math.abs(chave.hashCode()) % info.length` (hashCode pode ser negativo!)

```java
int indice = mapa.calcularHash("AXQ-3041");
// Math.abs("AXQ-3041".hashCode()) % 5 → algum valor entre 0 e 4
```

**Implementação:**
```java
public int calcularHash(K chave) {
    return Math.abs(chave.hashCode()) % info.length;
}
```

> ⚠️ **Ponto de prova:** Sem `Math.abs()`, chaves cujo `hashCode()` é negativo causariam `ArrayIndexOutOfBoundsException`. Exemplo: `"LJQ-5931".hashCode()` pode retornar valor negativo.

---

### `void inserir(K chave, T valor)`
Insere um par chave-valor no mapa.

**Passo a passo:**
1. Calcula o hash da chave → obtém o índice
2. Cria um `NoMapa` com a chave e o valor
3. Insere o `NoMapa` na lista encadeada daquele índice

```java
Veiculo ana = new Veiculo("AXQ-3041", "Ana");
mapa.inserir("AXQ-3041", ana);
// hash("AXQ-3041") → índice X → info[X].inserir(NoMapa("AXQ-3041", ana))
```

**Implementação:**
```java
public void inserir(K chave, T valor) {
    int indice = calcularHash(chave);
    NoMapa<K, T> novoNo = new NoMapa<>(chave, valor);
    info[indice].inserir(novoNo);
}
```

---

### `T buscar(K chave)`
Procura no mapa o objeto associado à chave informada e o retorna.

**Passo a passo:**
1. Calcula o hash da chave → obtém o índice
2. Cria um `NoMapa` temporário com a chave e valor `null`
3. Usa `ListaEncadeada.buscar()` que percorre a lista e usa `equals()` (que compara apenas `chave`)
4. Se encontrou, retorna o **valor** do nó; senão, retorna `null`

```java
Veiculo resultado = mapa.buscar("AXQ-3041"); // retorna o objeto ana
Veiculo naoExiste = mapa.buscar("ZZZ-9999"); // retorna null
```

**Implementação:**
```java
public T buscar(K chave) {
    int indice = calcularHash(chave);
    NoMapa<K, T> chaveBusca = new NoMapa<>(chave, null);

    NoLista<NoMapa<K, T>> resultado = info[indice].buscar(chaveBusca);

    if (resultado != null) {
        return resultado.getInfo().getValor();
    }

    return null;
}
```

> 💡 O truque: `NoMapa(chave, null)` funciona como "critério de busca" porque `equals()` compara **apenas a chave**.

---

### `void remover(K chave)`
Remove do mapa o objeto que possui a chave informada.

```java
mapa.remover("AXQ-3041"); // remove o veículo de Ana
```

**Implementação:**
```java
public void remover(K chave) {
    int indice = calcularHash(chave);
    NoMapa<K, T> chaveBusca = new NoMapa<>(chave, null);
    info[indice].retirar(chaveBusca);
}
```

---

### `double calcularFatorCarga()`
Calcula e retorna o **fator de carga** do mapa.

**Fórmula:** `totalDeElementos / tamanhoDovetor`

```java
double fator = mapa.calcularFatorCarga();
// Com 4 veículos e vetor de 5 → 4/5 = 0.8
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

---

## 🔷 Classe `Veiculo`

Classe de domínio usada como tipo `T` nos testes.

### `Veiculo(String placa, String proprietario)`
**Construtor** — cria um veículo com placa e proprietário.

```java
Veiculo v = new Veiculo("AXQ-3041", "Ana");
```

### Getters e Setters

| Método | Retorno | Descrição |
|:-------|:-------:|:----------|
| `getPlaca()` | `String` | Retorna a placa |
| `setPlaca(String)` | `void` | Altera a placa |
| `getProprietario()` | `String` | Retorna o proprietário |
| `setProprietario(String)` | `void` | Altera o proprietário |

### `String toString()`
Retorna representação textual do veículo para debug.

```java
System.out.println(veiculo);
// Veiculo{placa='AXQ-3041', proprietario='Ana'}
```

---

## 🔷 Testes (`MapaDispersaoTest`)

### Teste do Plano PL01

| Teste | Método | O que valida |
|:-----:|:-------|:-------------|
| PL01 | `testePL01_inserirEBuscarVeiculosPorPlaca()` | Inserir 4 veículos com placa String e buscar todos — `assertSame` garante mesma referência |

### Testes Complementares

| Teste | Método | O que valida |
|:-----:|:-------|:-------------|
| 2 | `testeCalcularHashComChaveString()` | Hash com String sempre gera índice no intervalo [0, tamanho) |
| 3 | `testeBuscarPlacaInexistente()` | Busca por placa que não existe retorna `null` |
| 4 | `testeRemoverVeiculo()` | Inserir, verificar, remover, verificar que sumiu |
| 5 | `testeRemoverComColisaoDeStrings()` | Remover um veículo sem afetar os demais |
| 6 | `testeCalcularFatorCarga()` | Mapa vazio = 0.0; com 4 veículos e vetor 5 = 0.8 |
| 7 | `testeMapaComChaveInteger()` | Mapa funciona com Integer como chave (genericidade real) |

---

## 🎯 Resumo Rápido — Como Tudo se Conecta

```
mapa.inserir("AXQ-3041", ana)
    │
    ▼
calcularHash("AXQ-3041")
    │
    ▼
Math.abs("AXQ-3041".hashCode()) % 5  →  índice X
    │
    ▼
info[X].inserir(NoMapa("AXQ-3041", ana))
    │
    ▼
ListaEncadeada adiciona ao início


mapa.buscar("AXQ-3041")
    │
    ▼
calcularHash("AXQ-3041")  →  índice X
    │
    ▼
info[X].buscar(NoMapa("AXQ-3041", null))
    │                        ▲
    ▼                        │
Percorre lista, usa equals() que compara só chave (String.equals)
    │
    ▼
Encontra NoMapa("AXQ-3041", ana) → retorna ana
```

---

## 🔄 Comparação Aula 1 vs Aula 2

| Aspecto | Aula 1 (`int`) | Aula 2 (Genérico `K`) |
|:--------|:---------------|:-----------------------|
| Declaração | `NoMapa<T>` | `NoMapa<K, T>` |
| Tipo da chave | `int` (primitivo) | `K` (qualquer objeto) |
| Comparação no equals | `chave == noMapa.chave` | `chave.equals(noMapa.chave)` |
| Hash | `chave % info.length` | `Math.abs(chave.hashCode()) % info.length` |
| Classe de teste | `Aluno` (matrícula int) | `Veiculo` (placa String) |
| hashCode() no NoMapa | Não necessário (int) | Necessário (contrato) |
