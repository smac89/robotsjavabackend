function Get-Dockerable {
    if ($PSVersionTable.Platform -eq 'Unix') {
        if (("$(id --user)" -ne '0') -and (" $(id --groups --name) " -notlike '* docker *')) {
            Write-Error -Message "Script must be run as root"
            Write-Error -Message "Try: 'sudo $PSCommandPath'"
            return $false
        }
    }
    return $true
}
