$ErrorActionPreference = "Stop"

$projects = @(
    "java\b-plus-tree",
    "java\binary-digital-tree",
    "java\patricia-tree",
    "java\patricia-trie-alt",
    "java\n-ary-tree-linked-lists"
)

foreach ($project in $projects) {
    $build = Join-Path $project "build"
    if (Test-Path $build) {
        Remove-Item -LiteralPath $build -Recurse -Force
    }

    New-Item -ItemType Directory -Force -Path $build | Out-Null
    Write-Host "Compiling $project"

    $sources = Get-ChildItem -Path (Join-Path $project "src") -Filter *.java
    javac -encoding UTF-8 -d $build $sources.FullName
    if ($LASTEXITCODE -ne 0) {
        exit $LASTEXITCODE
    }
}

Write-Host "All Java projects compiled successfully."
