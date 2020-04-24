<#
.DESCRIPTION
    Use this script to set up the dev database
#>
param(
    [Parameter(Mandatory = $false,
            Position = 0,
            HelpMessage="Supply the path to an environment file")]
    [String]$EnvFile = "$($PWD.Path)/.env"
)

Import-Module -Name "$PSScriptRoot/common.psm1" -Verbose -Function TestDockerPerm,TestContainerRunning

if (TestDockerPerm) {
    $ImageName = 'postgres:12-alpine'
    docker pull $ImageName

    if (TestContainerRunning) {
        Write-Host -ForegroundColor Green -Message "The container is already running..."
        Write-Host -ForegroundColor Green -Message "Run the script 'show:dev.ps1' for more information"
    } else {
        docker run --detach --interactive --tty `
            --mount "type=bind,src=$PSScriptRoot/db,dst=/docker-entrypoint-initdb.d/" `
            --name dev-postgres --publish 5432:5432 --env-file $EnvFile --rm $ImageName
    }
}
