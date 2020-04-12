param(
    [String]$EnvFile = "$env:JavaBackendSecrets"
)

Import-Module -Name "$PSScriptRoot/common.psm1" -Function Get-Dockerable -Verbose

if (Get-Dockerable) {
    $ImageName = 'postgres:12-alpine'
    docker pull $ImageName
    docker run --detach --interactive --tty --mount "type=bind,src=$PSScriptRoot/db,dst=/docker-entrypoint-initdb.d/" `
        --name dev-postgres --publish 5432:5432 --env-file $EnvFile --rm $ImageName
}
