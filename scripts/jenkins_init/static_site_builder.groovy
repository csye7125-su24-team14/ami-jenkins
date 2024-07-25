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
pipelineJob('semantic-release-helm-cve-processor') {
    description('Pipeline Job to do semantic release on Helm chart for CVE processor.')
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
pipelineJob('semantic-release-helm-cve-consumer') {
    description('Pipeline Job to do semantic release on Helm chart for CVE consumer.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-webapp-cve-consumer.git')
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
//HELM-EKS-AUTOSCALER
pipelineJob('image-mirroring-eks-autoscaler') {
    description('Pipeline Job to mirror autoscaler image to docker hub.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-eks-autoscaler.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile-image-mirroring')
        }
    }
    triggers {
        githubPush()
    }
}
pipelineJob('semantic-release-helm-eks-autoscaler') {
    description('Pipeline Job to do semantic release on Helm chart for EKS Cluster Autoscaler.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-eks-autoscaler.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile-semantic-release')
        }
    }
    triggers {
        githubPush()
    }
}
//HELM-EKS-AUTOSCALER
pipelineJob('webapp-cve-processor-container-builder') {
    description('This job will build the webapp cve processor container and push it to Docker Hub.')
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
pipelineJob('webapp-cve-consumer-container-builder') {
    description('This job will build the webapp cve consumer container and push it to Docker Hub.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/webapp-cve-consumer.git')
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
pipelineJob('webapp-cve-producer-container-builder') {
    description('This job will build the webapp cve producer container and push it to Docker Hub.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/webapp-cve-producer.git')
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
pipelineJob('semantic-release-helm-postgres') {
    description('Pipeline Job to do semantic release on Postgres chart in EKS Cluster.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-postgresql.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile-semantic-release')
        }
    }
    triggers {
        githubPush()
    }
}
pipelineJob('semantic-release-helm-kafka') {
    description('Pipeline Job to do semantic release on Kafka chart in EKS Cluster.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/helm-kafka.git')
                        credentials('github-ssh-key')
                    }
                    branches('main')
                }
            }
            scriptPath('Jenkinsfile-semantic-release')
        }
    }
    triggers {
        githubPush()
    }
}
pipelineJob('cve-operator-container-builder') {
    description('This job will build the  cve operator container and push it to Docker Hub.')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team14/cve-operator.git')
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
