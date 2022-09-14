import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
*  TCP Server Program.
*  Listens on a TCP port
*  Receives two lines of input from a TCP client
*  Returns an upper case version of each line to the client
*
*  @author: Eshaan Vora
*      Email:  EshaanVora@gmail.com
*  @version: 3.2
*/

class TcpServer2 {

  public static void main(String[] argv) throws Exception {
    String welcomeMessage = "Welcome. Please type a sentence.\n";
    String clientSentence;
    String clientSentence2;
    String capitalizedSentence;
    String capitalizedSentence2;
    String message2 = "Please type another sentence.\n";

    ServerSocket welcomeSocket = null;

    try {
      welcomeSocket = new ServerSocket(6789);
    } catch (Exception e) {
      System.out.println("Failed to open socket connection");
      System.exit(0);
    }

    while (true) {
      System.out.println("Waiting for Client to connect.");
      Socket connectionSocket = welcomeSocket.accept();
      BufferedReader inFromClient = new BufferedReader(
          new InputStreamReader(connectionSocket.getInputStream()));
      DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());

      outToClient.writeBytes(welcomeMessage);
      System.out.println("Welcome message sent.");
      clientSentence = inFromClient.readLine();
      System.out.println(clientSentence);
      capitalizedSentence = clientSentence.toUpperCase() + '\n';
      System.out.println(capitalizedSentence);
      outToClient.writeBytes(capitalizedSentence);

      outToClient.writeBytes(message2);
      System.out.println("Message 2 sent.");
      clientSentence2 = inFromClient.readLine();
      System.out.println(clientSentence2);
      capitalizedSentence2 = clientSentence2.toUpperCase() + '\n';
      System.out.println(capitalizedSentence2);
      outToClient.writeBytes(capitalizedSentence2);

      connectionSocket.close();
    }
  }
}
