# Huffman Tree

<p align="left">
  <img src="https://img.shields.io/badge/Language-C%2B%2B-blue" />
  <img src="https://img.shields.io/badge/Status-Imported-lightgrey" />
  <img src="https://img.shields.io/badge/Algorithm-Huffman-brightgreen" />
  <img src="https://img.shields.io/badge/Structure-Binary%20Tree-purple" />
  <img src="https://img.shields.io/badge/Data%20Structure-Linked%20List-orange" />
</p>

C++ implementation of Huffman encoding and decoding using binary trees, ordered linked lists and file manipulation.

This project was imported from the previous standalone `huffman-tree` repository so it can live together with the broader data structures collection.

## Highlights

- Reads text files.
- Counts symbol/word frequencies.
- Builds a Huffman tree.
- Generates binary codes.
- Compresses data.
- Decompresses data using the generated tree/table.
- Uses custom stack/list structures.

## Source Files

- `Programa1.cpp`: compression flow.
- `Programa2.cpp`: decompression flow.
- `pilha.h`: custom stack used by tree traversal.
- `data/`: sample text files.

## Build Note

This code uses console headers such as `conio.h`/`conio2.h`, so it is best treated as a Dev-C++/Windows-oriented academic implementation unless the console dependencies are refactored.
