# N-ary Tree with Linked Lists

<p align="left">
  <img src="https://img.shields.io/badge/Language-Java-orange" />
  <img src="https://img.shields.io/badge/Status-Compiled-brightgreen" />
  <img src="https://img.shields.io/badge/Structure-N--ary%20Tree-blue" />
  <img src="https://img.shields.io/badge/Data%20Structure-Linked%20Lists-purple" />
</p>

Java implementation of an N-ary tree represented with custom linked lists for node values and child references.

## Highlights

- Tree insertion with ordered position selection.
- Custom linked list for node keys.
- Custom linked list for child links.
- Level-order display using a queue.
- Manual pointer-style structure modeling in Java.

## Run

```bash
javac -encoding UTF-8 -d build src/*.java
java -cp build Main
```

## Files

- `Arvore.java`: tree insertion and display logic.
- `No.java`: tree node.
- `ListaInfo.java` and `NoListaInfo.java`: linked list for stored values.
- `ListaLig.java` and `NoListaLig.java`: linked list for child references.
- `Fila.java` and `NoFila.java`: custom queue for level traversal.
- `Main.java`: demo scenario.
