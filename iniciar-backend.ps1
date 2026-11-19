
$ErrorActionPreference = "Stop"

$raiz = $PSScriptRoot
$arquivoEnv = Join-Path $raiz ".env.local"

if (-not (Test-Path $arquivoEnv)) {
    throw "Arquivo .env.local nao encontrado."
}

Get-Content $arquivoEnv | ForEach-Object {
    $linha = $_.Trim()

    if ($linha -and -not $linha.StartsWith("#")) {
        $partes = $linha -split "=", 2

        if ($partes.Count -eq 2) {
            [Environment]::SetEnvironmentVariable(
                $partes[0].Trim(),
                $partes[1].Trim(),
                "Process"
            )
        }
    }
}

Set-Location (Join-Path $raiz "backend")

.\mvnw.cmd spring-boot:run
