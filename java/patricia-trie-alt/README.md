# Patricia Trie Alternative

<p align="left">
  <img src="https://img.shields.io/badge/Language-Java-orange" />
  <img src="https://img.shields.io/badge/Status-Compiled-brightgreen" />
  <img src="https://img.shields.io/badge/Structure-Compressed%20Trie-blue" />
  <img src="https://img.shields.io/badge/Operations-Insert%20%7C%20Search-purple" />
</p>

Alternative Java implementation of a Patricia trie with compressed branching, prefix handling and word search.

## Highlights

- Inserts a large set of words.
- Handles shared prefixes and node splitting.
- Searches complete words.
- Displays stored words.
- Displays the tree by level using a custom queue.
- Includes a UML/class diagram image in `docs/`.

## Files

- `TriePatricia.java`: main compressed trie implementation.
- `No.java`: node with letters, child links, stored word and split position.
- `Fila.java` and `NoFila.java`: queue for level traversal.
- `Pilha.java` and `NoPilha.java`: stack for iterative traversal.
- `Main.java`: test scenario with insertion, search and display.
