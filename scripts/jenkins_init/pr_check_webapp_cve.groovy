multibranchPipelineJob('pullrequest-check-webapp-cve') {
    displayName('pullrequest-check-webapp-cve')
    description('Checks status on pull request')
    branchSources {
        github {
            id('pullrequest-check-webapp-cve')
            apiUri('https://api.github.com')
            repoOwner('csye7125-su24-team14')
            repository('webapp-cve-processor')
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