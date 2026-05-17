# UNI3 — Aula 2: Mapa de Dispersão com Chaves Genéricas

## 📌 Contexto Geral

Esta aula evolui o Mapa de Dispersão da aula 1, tornando-o capaz de usar **qualquer tipo de objeto como chave** (String, Integer, etc.), em vez de apenas `int`. Essa generalização reflete como as `HashMap` funcionam no mundo real do Java.

### O que mudou em relação à Aula 1?

| Aspecto | Aula 1 | Aula 2 |
|:--------|:------:|:------:|
| Tipo da chave | `int` fixo | Genérico `K` |
| Cálculo do hash | `chave % tamanho` | `Math.abs(chave.hashCode()) % tamanho` |
| Tipo do NoMapa | `NoMapa<T>` | `NoMapa<K, T>` |
| Assinatura do MapaDispersao | `MapaDispersao<T>` | `MapaDispersao<K, T>` |
| Classe de domínio | `Aluno` (matrícula int) | `Veiculo` (placa String) |

### Por que usar `hashCode()`?

Todo objeto em Java herda o método `hashCode()` de `Object`. Cada tipo implementa sua própria versão:
- **String**: calcula o hash baseado nos caracteres (`s[0]*31^(n-1) + s[1]*31^(n-2) + ...`)
- **Integer**: retorna o próprio valor inteiro
- **Objetos customizados**: podem sobrescrever `hashCode()` para distribuição adequada

### ⚠️ Observação Importante — hashCode() negativo

O valor de `hashCode()` do Java **pode retornar um número negativo**. Por exemplo:
```java
"LJQ-5931".hashCode()  // pode retornar -1156012345 (valor negativo!)
```

Usar esse valor diretamente como índice de array causaria `ArrayIndexOutOfBoundsException`. Por isso, é necessário aplicar `Math.abs()` para obter **somente o valor absoluto (sem sinal)**:

```java
int indice = Math.abs(chave.hashCode()) % info.length;
//           ^^^^^^^^ garante valor positivo
```

---

## 🏗️ Diagrama de Classes

```
┌──────────────────────────────────────────┐     ┌──────────────────────────────┐
│          MapaDispersao<K, T>             │     │        NoMapa<K, T>          │
├──────────────────────────────────────────┤     ├──────────────────────────────┤
│ - info[] : ListaEncadeada<NoMapa<K,T>>   │────▶│ - chave : K                  │
├──────────────────────────────────────────┤     │ - valor : T                  │
│ + MapaDispersao(tamanho : int)           │     ├──────────────────────────────┤
│ + calcularHash(chave : K) : int          │     │ + getChave() : K             │
│ + inserir(chave : K, valor : T) : void   │     │ + setChave(chave : K) : void │
│ + remover(chave : K) : void              │     │ + getValor() : T             │
│ + buscar(chave : K) : T                  │     │ + setValor(valor : T) : void │
│ + calcularFatorCarga() : double          │     │ + equals(o : Object):boolean │
└──────────────────────────────────────────┘     │ + hashCode() : int           │
                                                 └──────────────────────────────┘

┌──────────────────────────────────────────────────┐
│                   Veiculo                        │
├──────────────────────────────────────────────────┤
│ - placa : String                                 │
│ - proprietario : String                          │
├──────────────────────────────────────────────────┤
│ + Veiculo(placa : String, proprietario : String) │
│ + getPlaca() : String                            │
│ + setPlaca(placa : String) : void                │
│ + getProprietario() : String                     │
│ + setProprietario(proprietario : String) : void  │
└──────────────────────────────────────────────────┘
```

---

## 🔑 Conceitos-Chave para a Prova

### 1. Contrato equals/hashCode
Quando dois objetos são `equals()`, eles **devem** ter o mesmo `hashCode()`. O `NoMapa<K,T>` garante isso: `equals()` compara apenas `chave`, e `hashCode()` delega para `chave.hashCode()`.

### 2. Math.abs() no cálculo do hash
`String.hashCode()` pode retornar **valores negativos**. Sem `Math.abs()`, o índice do vetor seria negativo, causando `ArrayIndexOutOfBoundsException`.

### 3. Genericidade (K, T) vs (T)
- **Aula 1**: `MapaDispersao<T>` → chave sempre `int`, inflexível
- **Aula 2**: `MapaDispersao<K, T>` → chave pode ser qualquer tipo, mais versátil

Isso espelha a `HashMap<K, V>` do Java Collections Framework.

### 4. Fator de carga com vetor pequeno
Com vetor de tamanho 5 e 4 veículos: `fatorCarga = 4/5 = 0.8`. Esse fator é alto, indicando maior probabilidade de colisões. Na prática, redimensionar o vetor seria recomendável.

### 5. Complexidade de tempo (mesma da aula 1)

| Operação | Caso médio | Pior caso (todas as chaves colidem) |
|:---------|:----------:|:-----------------------------------:|
| `inserir` | O(1) | O(n) |
| `buscar` | O(1) | O(n) |
| `remover` | O(1) | O(n) |
| `calcularHash` | O(1) | O(1) |
| `fatorCarga` | O(m) | O(m) — onde m = tamanho do vetor |

---

## 📋 Plano de Testes (PL01)

| Caso | Objetivo | Veículos | Resultado Esperado |
|:----:|:---------|:---------|:-------------------|
| 1 | Inserir 4 veículos e buscar todos pela placa | AXQ-3041/Ana, MSE-7521/Pedro, LJQ-5931/Marta, MQD-2241/Lucas | Mesmos objetos (referência) retornados |

### Exemplo de execução:

```
Mapa com 5 buckets:

inserir("AXQ-3041", ana)   → hash("AXQ-3041") % 5 → índice X
inserir("MSE-7521", pedro) → hash("MSE-7521") % 5 → índice Y
inserir("LJQ-5931", marta) → hash("LJQ-5931") % 5 → índice Z
inserir("MQD-2241", lucas) → hash("MQD-2241") % 5 → índice W

buscar("AXQ-3041") → retorna referência de ana  ✓
buscar("MSE-7521") → retorna referência de pedro ✓
buscar("LJQ-5931") → retorna referência de marta ✓
buscar("MQD-2241") → retorna referência de lucas ✓
```

---

## 📁 Arquivos Criados

```
UNI3/
└── aula2/
    ├── NoMapa.java            → Nó genérico (chave K + valor T)
    ├── MapaDispersao.java     → Mapa de dispersão genérico
    ├── Veiculo.java           → Classe de domínio (placa + proprietário)
    └── MapaDispersaoTest.java → 7 testes unitários (JUnit 5)
```
