import org.hidetake.groovy.ssh.Ssh
import org.hidetake.groovy.ssh.connection.AllowAnyHosts
import SSHFileDownloader

class Main {

    public static void main(String[] args) {

        SSHFileDownloader ssh = new SSHFileDownloader(
                                '192.168.3.44',
                                'username',
                                'password',
                                '/home/username/file.txt',
                                './file.txt'
                                )
        def downloader = ssh.execute()

        print("Stdout message after all ssh actions\n")

    }

}