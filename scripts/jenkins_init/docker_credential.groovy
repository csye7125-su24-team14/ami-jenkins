
import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope
import java.util.Properties
import java.io.File
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def instance = Jenkins.instance
def store = instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0].getStore()


def props = new Properties()
def envFile = new File('/etc/jenkins.env')
if (envFile.exists()) {
    props.load(envFile.newDataInputStream())
} else {
    throw new RuntimeException("/etc/jenkins.env file not found")
}

def dockerUsername = props.getProperty("DOCKER_USERNAME")
def dockerPassword = props.getProperty("DOCKER_PASSWORD")
def githubUsername = props.getProperty("GIT_USERNAME")
def githubPassword = props.getProperty("GIT_PASSWORD")

def dockerCredentials = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "docker-hub-credentials",
    "Docker Hub Credentials",
    dockerUsername,
    dockerPassword
)


def githubCredentials = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "github-ssh-key",
    "GitHub Credentials",
    githubUsername,
    githubPassword
)

def domain = Domain.global()
store.addCredentials(domain, dockerCredentials)
store.addCredentials(domain, githubCredentials)

// Create the configuration job interface from a jobDSL script
def jobDslScript = new File('/var/lib/jenkins/dsl_scripts/static_site_builder.groovy')
def workspace = new File('.')
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

jobDslScript = new File('/var/lib/jenkins/dsl_scripts/pr_check_helm.groovy')
workspace = new File('.')
jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

jobDslScript = new File('/var/lib/jenkins/dsl_scripts/pr_check_webapp_cve.groovy')
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

jobDslScript = new File('/var/lib/jenkins/dsl_scripts/pr_check_infra_aws.groovy')
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

jobDslScript = new File('/var/lib/jenkins/dsl_scripts/pr_check_webapp_consumer.groovy')
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)

jobDslScript = new File('/var/lib/jenkins/dsl_scripts/pr_check_webapp_producer.groovy')
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)