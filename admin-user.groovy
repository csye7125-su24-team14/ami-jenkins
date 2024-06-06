/*
 * Create an admin user.
 */
import jenkins.model.*
import hudson.security.*
import hudson.util.*;
import jenkins.install.*;
import jenkins.model.*
import java.util.Properties

println "--> creating admin user"

def props = new Properties()
def envFile = new File('/etc/jenkins.env')
if (envFile.exists()) {
    props.load(envFile.newDataInputStream())
} else {
    throw new RuntimeException("/etc/jenkins.env file not found")
}

def adminUsername = props.getProperty('JENKINS_ADMIN_USERNAME')
def adminPassword = props.getProperty('JENKINS_ADMIN_USER_PASSWORD')

// def adminUsername = "admin"
// def adminPassword = "admin123"

assert adminUsername != null : "No ADMIN_USERNAME env var provided, but required"
assert adminPassword != null : "No ADMIN_PASSWORD env var provided, but required"

def instance = Jenkins.getInstance()

instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(adminUsername, adminPassword)
Jenkins.instance.setSecurityRealm(hudsonRealm)
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
Jenkins.instance.setAuthorizationStrategy(strategy)

Jenkins.instance.save()