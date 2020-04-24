$ContainerName = 'dev-postgres'

function TestDockerPerm {
    if ($PSVersionTable.Platform -eq 'Unix') {
        if (("$(id --user)" -ne '0') -and (" $(id --groups --name) " -notlike '* docker *')) {
            Write-Error -Message "Script must be run as root"
            Write-Error -Message "Try: 'sudo $PSCommandPath'"
            return $false
        }
    }
    return $true
}

function TestContainerRunning {
    $isRunning = Invoke-Command -ScriptBlock { docker inspect --format='{{.State.Running}}' $ContainerName }
    return  [System.Convert]::ToBoolean($isRunning)
}

function GetContainerIP {
    $FormatString = '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}'
    $IP = Invoke-Command -ScriptBlock { docker inspect --format=$FormatString $ContainerName }
    return $IP
}
