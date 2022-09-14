import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
*  TCP Client Program
*  Connects to a TCP Server
*  Receives lines of input from the keyboard and sends it to the server
*  Receives a response from the server and displays it.
*
*  @author: Eshaan Vora
*      Email:  EshaanVora@gmail.com
*  @version: 3.2
*/

class TcpClient2 {
  public static void main(String[] argv) throws Exception {
    Socket clientSocket = null;

    try {
      clientSocket = new Socket("localhost", 6789);
    } catch (Exception e) {
      System.out.println("Failed to open socket connection");
      System.exit(0);
    }

    BufferedReader inFromServer =  new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());


    String welcomeMessage = inFromServer.readLine();
    System.out.println("FROM SERVER: " + welcomeMessage);
    String sentence = inFromUser.readLine();
    outToServer.writeBytes(sentence + '\n');

    String modifiedSentence = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence);

    String message2 = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message2);
    String sentence2 = inFromUser.readLine();
    outToServer.writeBytes(sentence2 + '\n');

    String modifiedSentence2 = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence2);


    clientSocket.close();

  }
}
