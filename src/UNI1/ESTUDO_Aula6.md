# Estudo: UNI1 — Aula 6 (Fila)

Revisão dos **métodos** e classes da aula sobre **fila (queue)** — estrutura **FIFO** (First In, First Out): o primeiro a entrar é o primeiro a sair.

---

## Interface `Fila<T>` (`exercicio1_2`)

| Método | Descrição |
|--------|-----------|
| `void inserir(T valor)` | Enfileira no **fim** (cauda). |
| `boolean estaVazia()` | `true` se não houver elementos. |
| `T peek()` | Consulta o elemento da **frente** (início) sem remover. |
| `T retirar()` | Remove e devolve o da **frente**. |
| `void liberar()` | Esvazia a estrutura. |

---

## `FilaVetor<T>` (`exercicio1_2`)

Fila em **vetor circular**: índices `inicio` (cabeça) e `tamanho`; inserção na posição `(inicio + tamanho) % limite`.

| Método | Descrição |
|--------|-----------|
| `FilaVetor(int limite)` | Aloca `Object[limite]`, `tamanho = 0`, `inicio = 0`. |
| `int getLimite()` | Capacidade máxima do vetor. |
| `void inserir(Object valor)` | Se `tamanho == limite`, `FilaCheiaException`; senão grava em `(inicio + tamanho) % limite` e incrementa `tamanho`. |
| `boolean estaVazia()` | `tamanho == 0`. |
| `Object peek()` | Se vazia, `FilaVaziaException`; senão devolve `info[inicio]`. |
| `Object retirar()` | Usa `peek()` para o valor; depois `inicio = (inicio + 1) % limite` e decrementa `tamanho`. |
| `void liberar()` | Substitui `info` por um novo array do mesmo `limite`. |
| `FilaVetor<T> criarFilaConcatenada(FilaVetor f2)` | Cria nova fila com capacidade `limite + f2.getLimite()`, copia **esta** fila na ordem FIFO e em seguida `f2`, sem alterar `f1` nem `f2`. |
| `String toString()` | Percorre os `tamanho` elementos a partir de `inicio` com aritmética modular, separando por vírgula. |

---

## Exceções (`exercicio1_2`)

| Classe | Construtores | Uso típico |
|--------|--------------|------------|
| `FilaVaziaException` | sem argumentos; `(String mensagem)` | `peek`/`retirar` quando a fila está vazia (mensagem padrão no código atual coincide com “cheia” — vale conferir o texto desejado). |
| `FilaCheiaException` | sem argumentos; `(String mensagem)` | `inserir` quando `tamanho == limite`. |

---

## `ListaEncadeada<T>` (`exercicio3_4` — cópia estendida para a fila)

Lista com ponteiros **`primeiro`** e **`ultimo`** para permitir inserção no fim em O(1).

| Método | Descrição |
|--------|-----------|
| `ListaEncadeada()` | `primeiro` e `ultimo` nulos. |
| `boolean estaVazia()` | `primeiro == null`. |
| `void inserir(T valor)` | Insere no **início** (comportamento “pilha na lista”); ajusta `primeiro`/`ultimo` se necessário. |
| `void inserirNoFinal(T valor)` | Insere no **fim**; atualiza `ultimo`. |
| `void retirar(T valor)` | Remove o nó cujo `info` é igual a `valor` (`equals`); trata remoção no início/meio/fim e atualiza `ultimo` quando o último nó some. |
| `int obterComprimento()` | Conta nós percorrendo a partir de `primeiro`. |
| `String toString()` | Valores do primeiro ao último, separados por vírgula. |
| `NoLista<T> getPrimeiro()` / `void setPrimeiro(...)` | Acesso ao primeiro nó. |
| `NoLista<T> getUltimo()` / `void setUltimo(...)` | Acesso ao último nó. |

**Nota:** Esta classe está em `aula6/exercicio3_4` e estende a ideia da lista da aula 3 com ponteiro para o último elemento.

---

## `FilaLista<T>` (`exercicio3_4`)

Implementa `Fila` com `ListaEncadeada`: **enfileira no final**, **desenfileira pela frente** (remove o primeiro valor).

| Método | Descrição |
|--------|-----------|
| `FilaLista()` | Cria `new ListaEncadeada<>()` interna. |
| `void inserir(Object valor)` | `lista.inserirNoFinal((T) valor)`. |
| `boolean estaVazia()` | `lista.estaVazia()`. |
| `Object peek()` | Se vazia, `RuntimeException("Fila vazia")`; senão `lista.getPrimeiro().getInfo()`. |
| `Object retirar()` | Se vazia, `RuntimeException`; senão `peek()` e `lista.retirar(valor)`. |
| `void liberar()` | `lista = new ListaEncadeada<>()`. |
| `String toString()` | `lista.toString()`. |

---

## Classes de demonstração

| Classe | Método | Descrição |
|--------|--------|-----------|
| `TesteFilaVetor` | `main` | Casos: vazia, inserir, sequência de `retirar`, fila cheia, `retirar` em vazia, `peek`, `liberar`, `criarFilaConcatenada` + `toString` das filas originais. |
| `TesteFilaLista` | `main` | Casos análogos com `FilaLista` (sem exceção de fila cheia na lista dinâmica). |

---

## Resumo

```text
inserir  → entra no FIM da fila
retirar  → sai da FRENTE (o mais antigo)
peek     → vê a frente sem remover

FilaVetor: vetor circular (inicio + tamanho)
FilaLista: inserirNoFinal + retirar pelo primeiro nó
```
