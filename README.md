# Data Structures Lab

<p align="left">
  <img src="https://img.shields.io/badge/Status-In%20Progress-yellow" />
  <img src="https://img.shields.io/badge/Java-24-orange" />
  <img src="https://img.shields.io/badge/C%2B%2B-Huffman-blue" />
  <img src="https://img.shields.io/badge/Data%20Structures-Trees-purple" />
  <img src="https://img.shields.io/badge/Algorithms-Search%20%26%20Compression-brightgreen" />
  <img src="https://img.shields.io/badge/Academic%20Lab-Portfolio-black" />
</p>

This repository is a collection of data structures studied and implemented during Computer Science coursework. The goal is to keep the implementations organized by language, with clean source code, build instructions and short documentation for each structure.

The repository currently focuses on Java tree-based structures and includes the previous Huffman compression project in C++. C implementations will be added later as they are recovered and organized.

## Repository Structure

```text
java/
  b-plus-tree/
  binary-digital-tree/
  patricia-tree/
  patricia-trie-alt/
  n-ary-tree-linked-lists/

cpp/
  huffman-tree/

c/
  README.md
```

## Implementations

| Area | Project | Language | Concepts |
|---|---|---|---|
| Trees | [B+ Tree](java/b-plus-tree) | Java | insertion, deletion, split, redistribution, concatenation, linked leaves |
| Trees | [Binary Digital Tree](java/binary-digital-tree) | Java | binary codes, file records, tree compaction, decoding |
| Trees | [Patricia Tree](java/patricia-tree) | Java | trie structure, word insertion, level traversal |
| Trees | [Patricia Trie Alternative](java/patricia-trie-alt) | Java | compressed trie, search, prefix handling, level traversal |
| Trees | [N-ary Tree with Linked Lists](java/n-ary-tree-linked-lists) | Java | node keys, linked lists, child links, level traversal |
| Compression | [Huffman Tree](cpp/huffman-tree) | C++ | binary tree, ordered lists, frequency table, file compression |

## Java Build Check

All Java implementations can be compiled at once:

```powershell
.\scripts\compile-java.ps1
```

On Linux/macOS:

```bash
chmod +x scripts/compile-java.sh
./scripts/compile-java.sh
```

Each Java implementation is also independent and can be compiled separately:

```bash
javac -encoding UTF-8 -d build src/*.java
```

For projects with a `Main.java`, run:

```bash
java -cp build Main
```

The Java implementations were checked with:

```text
javac 24.0.2
```

## Notes

- The original raw folders are kept locally but ignored by Git.
- Generated files such as `.class`, `out/`, `build/`, `.idea/` and `.iml` are intentionally not committed.
- Some implementations are academic exercises and prioritize showing the data structure logic directly.
- C implementations will be added under `c/` in future updates.
