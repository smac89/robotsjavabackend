Import-Module -Name "$PSScriptRoot/common.psm1" -Verbose -Function TestDockerPerm,TestContainerRunning,GetContainerIP

if (TestDockerPerm) {
    if (TestContainerRunning) {
        $IP = $(GetContainerIP)
        Write-Host -ForegroundColor Blue "Postgres DB is running at: " -NoNewline
        Write-Host -ForegroundColor Green "${IP}:5432"
    } else {
        Write-Host -ForegroundColor Red 'The `dev-postgres` container is not running.'
        Write-Host -ForegroundColor Red 'Please run build:dev.ps1 before running this script'
    }
}
