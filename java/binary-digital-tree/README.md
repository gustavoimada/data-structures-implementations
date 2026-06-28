# Binary Digital Tree

<p align="left">
  <img src="https://img.shields.io/badge/Language-Java-orange" />
  <img src="https://img.shields.io/badge/Status-Compiled-brightgreen" />
  <img src="https://img.shields.io/badge/Structure-Binary%20Digital%20Tree-blue" />
  <img src="https://img.shields.io/badge/File%20Handling-RandomAccessFile-purple" />
</p>

Java implementation of a binary digital tree built from records containing strings and binary codes.

## Highlights

- Reads fixed-size records from a binary file.
- Builds a tree by following `0` and `1` paths.
- Supports tree compaction logic.
- Includes a decoding method based on binary sequences.
- Uses `RandomAccessFile` for record-based file access.

## Build

```bash
javac -encoding UTF-8 -d build src/*.java
```

This implementation currently does not include a `Main.java` demo. The source compiles as a reusable structure and can be exercised by creating records with `Registro` and loading them through `Arquivo`.

## Files

- `Arvore.java`: binary digital tree logic.
- `No.java`: tree node.
- `Registro.java`: fixed-size record with text and binary code.
- `Arquivo.java`: file abstraction using `RandomAccessFile`.
- `Pilha.java` and `NoPilha.java`: custom stack used by traversal/compaction routines.
