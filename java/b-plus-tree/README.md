# B+ Tree

<p align="left">
  <img src="https://img.shields.io/badge/Language-Java-orange" />
  <img src="https://img.shields.io/badge/Status-Compiled-brightgreen" />
  <img src="https://img.shields.io/badge/Structure-B%2B%20Tree-blue" />
  <img src="https://img.shields.io/badge/Operations-Insert%20%7C%20Delete-purple" />
</p>

Java implementation of a B+ tree with configurable order, leaf linking and iterative traversal.

## Highlights

- Insertion with leaf navigation and node splitting.
- Deletion with underflow handling.
- Redistribution and concatenation between sibling nodes.
- Linked leaf nodes for ordered sequential display.
- Iterative in-order traversal using a custom stack.

## Files

- `BPlusTree.java`: core B+ tree implementation.
- `No.java`: tree node representation.
- `Pilha.java` and `NoPilha.java`: custom stack used by iterative traversal.
- `Main.java`: test scenarios with different tree orders.
