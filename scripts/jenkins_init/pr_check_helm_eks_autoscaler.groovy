multibranchPipelineJob('pullrequest-check-helm-webapp-consumer') {
    displayName('pullrequest-check-helm-webapp-consumer')
    description('Checks status on pull request')
    branchSources {
        github {
            id('pullrequest-check-helm-webapp-consumer')
            apiUri('https://api.github.com')
            repoOwner('csye7125-su24-team14')
            repository('helm-webapp-cve-consumer')
            scanCredentialsId('github-ssh-key') 
            buildForkPRHead(true) 
            includes('*')
        }
    }


    configure {
        def traits = it / 'sources' / 'data' / 'jenkins.branch.BranchSource' / 'source' / 'traits'
        traits << 'org.jenkinsci.plugins.github_branch_source.TagDiscoveryTrait' {}

        // Add the ForkPullRequestDiscoveryTrait
        traits << 'org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait' {
            strategyId(2) 
        }
    }
}