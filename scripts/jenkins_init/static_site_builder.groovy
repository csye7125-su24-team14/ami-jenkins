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
    triggers {
        githubPush()
    }
}
pipelineJob('semantic-release-helm') {
    description('Pipeline Job to do semantic release on Healm')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-webapp-cve-processor.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile.semantic-release')
        }
    }
    triggers {
        githubPush()
    }
}
pipelineJob('webapp-container-builder') {
    description('This job will build the webapp container and push it to Docker Hub.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/webapp-cve-processor.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile-containerize')
        }
    }
    triggers {
        githubPush()
    }
}