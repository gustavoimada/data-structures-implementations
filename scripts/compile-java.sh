#!/usr/bin/env bash
set -euo pipefail

projects=(
  "java/b-plus-tree"
  "java/binary-digital-tree"
  "java/patricia-tree"
  "java/patricia-trie-alt"
  "java/n-ary-tree-linked-lists"
)

for project in "${projects[@]}"; do
  build="$project/build"
  rm -rf "$build"
  mkdir -p "$build"
  echo "Compiling $project"
  javac -encoding UTF-8 -d "$build" "$project"/src/*.java
done

echo "All Java projects compiled successfully."
