import jenkins.model.*
import hudson.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts

def jobName = "seed-job"
def jobDescription = "This is the seed job that will create other jobs using DSL scripts"

def instance = Jenkins.getInstance()

// Check if the job already exists
def existingJob = instance.getItem(jobName)
if (existingJob != null) {
    println "Job '${jobName}' already exists. Skipping creation."
    return
}

// Create a new job
def job = instance.createProject(FreeStyleProject, jobName)
job.setDescription(jobDescription)

// Configure the DSL script
def dslScript = """
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
"""

def dslBuildStep = new ExecuteDslScripts()
dslBuildStep.setScriptText(dslScript)
dslBuildStep.setUseScriptText(true)

// Add the DSL build step to the job
job.buildersList.add(dslBuildStep)

// Save the job configuration
job.save()

println "Job '${jobName}' created successfully."