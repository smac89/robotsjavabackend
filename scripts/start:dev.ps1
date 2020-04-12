Import-Module -Name "$PSScriptRoot/common.psm1" -Function Get-Dockerable -Verbose

if (Get-Dockerable) {
    $isRunning = [System.Convert]::ToBoolean((docker inspect --format='{{.State.Running}}' dev-postgres))

    if ($isRunning) {
        $IP=$(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' dev-postgres)
        Write-Host -ForegroundColor Blue "Postgres DB is running at: " -NoNewline
        Write-Host -ForegroundColor Green "${IP}:5432"
    } else {
        Write-Host -ForegroundColor Red 'The `dev-postgres` container is not running.'
        Write-Host -ForegroundColor Red 'Please run build:dev.ps1 before running this script'
    }
}
