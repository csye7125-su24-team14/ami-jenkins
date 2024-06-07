pipelineJob('caddy-docker-job') {
    description('An example pipeline job created using Job DSL that uses a Jenkinsfile from a Git repository')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/static-site.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
    
}