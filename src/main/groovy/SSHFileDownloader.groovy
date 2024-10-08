import org.hidetake.groovy.ssh.Ssh
import org.hidetake.groovy.ssh.connection.AllowAnyHosts

class SSHFileDownloader {
    private final String myhost
    private final String myuser
    private final String mypassword
    private final String myremoteFilePath
    private final String mylocalFilePath

    SSHFileDownloader(String host, String user, String password, String remoteFilePath, String localFilePath) {
        this.myhost = host
        this.myuser = user
        this.mypassword = password
        this.myremoteFilePath = remoteFilePath
        this.mylocalFilePath = localFilePath
    }

    void execute() {
        def ssh = Ssh.newService()
        ssh.remotes {
            "remoteHost" {
                host = this.myhost
                user = this.myuser
                password = this.mypassword
                knownHosts = AllowAnyHosts.instance

                ignoreError = true                                  // https://github.com/int128/groovy-ssh/blob/2.10.1/docs/src/docs/asciidoc/user-guide.adoc
            }
        }

        ssh.run {
            session(ssh.remotes."remoteHost") {
                execute 'exit 1'                                    // ignored
                get from: myremoteFilePath, into: mylocalFilePath   // if an error (for example, a file is missing on the remote host) occurs, it is not ignored
                println "File downloaded from $myremoteFilePath to $mylocalFilePath"
            }
        }
    }
}