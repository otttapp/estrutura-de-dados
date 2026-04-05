# Estudo: UNI1 — Aula 5 (Pilha)

Revisão dos **métodos** e classes da aula sobre **pilha (stack)** — estrutura **LIFO** (Last In, First Out).

---

## Interface `Pilha<T>` (`exercicio1_2`)

| Método | Descrição |
|--------|-----------|
| `void push(T info)` | Empilha um elemento no **topo**. |
| `T pop()` | Remove e devolve o elemento do **topo** (na implementação vetor, lança exceção se vazia). |
| `T peek()` | Apenas **lê** o topo sem remover (vetor: exceção se vazia). |
| `boolean estaVazia()` | `true` se não houver elementos. |
| `void liberar()` | Esvazia ou reinicia a estrutura. |

---

## `PilhaVetor<T>` (`exercicio1_2`)

Pilha com **array** de tamanho fixo (`limite`). O topo está no índice `tamanho - 1`.

| Método | Descrição |
|--------|-----------|
| `PilhaVetor(int limite)` | Aloca `Object[limite]`, zera `tamanho`. |
| `void push(Object info)` | Se `tamanho == limite`, lança `PilhaCheiaException`; senão grava em `info[tamanho]` e incrementa `tamanho`. |
| `Object pop()` | Se vazia, `PilhaVaziaException`; senão devolve `info[tamanho - 1]` e decrementa `tamanho`. |
| `Object peek()` | Se vazia, `PilhaVaziaException`; senão devolve `info[tamanho - 1]` sem alterar `tamanho`. |
| `boolean estaVazia()` | `tamanho == 0`. |
| `void liberar()` | Recria o array e zera `tamanho`. |
| `String toString()` | Do **topo à base** (`tamanho-1` até `0`), valores separados por vírgula. |
| `void concatenar(PilhaVetor<T> p)` | Empilha na pilha atual os elementos de `p` na ordem correta (usa pilha auxiliar e restaura `p`); se não couber, `PilhaCheiaException("Sem capacidade para concatenar")`. |
| `Object getInfo()` / `void setInfo(Object[] info)` | Acesso ao vetor interno. |
| `int getLimite()` / `void setLimite(int limite)` | Capacidade máxima. |
| `int getTamanho()` / `void setTamanho(int tamanho)` | Quantidade de elementos. |

---

## Exceções (`exercicio1_2`)

| Classe | Construtores | Uso |
|--------|--------------|-----|
| `PilhaVaziaException` | sem argumentos; `(String mensagem)` | `pop`/`peek` com pilha vazia; `PilhaLista.pop()` se lista vazia. |
| `PilhaCheiaException` | sem argumentos; `(String mensagem)` | `push` com vetor cheio; `concatenar` sem capacidade. |

---

## `PilhaLista<T>` (`exercicio3_4`)

Implementa `Pilha` usando `ListaEncadeada<T>` da **aula 3**: o **topo** da pilha é o **primeiro** nó da lista.

| Método | Descrição |
|--------|-----------|
| `PilhaLista(ListaEncadeada<T> lista)` | Recebe a lista usada como armazenamento. |
| `void push(Object info)` | `lista.inserir((T) info)` — insere no início. |
| `Object pop()` | Se vazia, `PilhaVaziaException`; senão obtém topo com `obterNo(0)`, remove com `retirar` e devolve. |
| `Object peek()` | `lista.obterNo(0)` (nó do índice 0). |
| `boolean estaVazia()` | Delega para `lista.estaVazia()`. |
| `void liberar()` | `lista = new ListaEncadeada<>()`. |
| `String toString()` | Delega para `lista.toString()`. |

**Aula 3 (referência):** `ListaEncadeada` usa aqui `estaVazia`, `inserir`, `obterNo`, `retirar`, `toString`.

---

## `VerificadorDelimitadores` (`exercicio5`)

| Método | Descrição |
|--------|-----------|
| `static boolean validar(String expressao)` | Percorre a string; abre `( [ {` com `push`; fecha `) ] }` comparando com o topo (`combina`); retorna `false` se fechar sem par ou tipo errado; no fim exige pilha vazia. |
| `private static boolean combina(char abre, char fecha)` | Verifica os pares `()`, `[]`, `{}`. |
| `static void main(String[] args)` | Lê expressão pelo `Scanner` e imprime se os delimitadores estão corretos. |

---

## Classes de teste / demonstração

| Classe | Método | Descrição |
|--------|--------|-----------|
| `PilhaVetorTest` | `main` | Casos: vazia, push, sequência de pops, pilha cheia, pop em vazia, peek, liberar, concatenar, `toString`. |
| `PilhaListaTest` | `main` | Casos análogos com `PilhaLista` (sem pilha cheia ilimitada). |

---

## Resumo

```text
push  → empilha no topo
pop   → desempilha (remove o topo)
peek  → só olha o topo
PilhaVetor: topo = último índice ocupado (tamanho - 1)
PilhaLista: topo = início da lista encadeada
```
