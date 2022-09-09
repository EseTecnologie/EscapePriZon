package Udp;

import main.GamePanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class FileCLIENT extends Thread {

   int port;
   String serverRoute;
   GamePanel gp;

   public FileCLIENT(GamePanel gp){
       this.gp=gp;
        port = 10102; // Change this to the desired port
        serverRoute = "C:\\EscapePrizon\\";
    }
    public static DatagramSocket socket;
    @Override
    public void run() {
        try {
            socket= new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(true){
            createFile(port, serverRoute);
        }
    }



    public static void createFile (int port, String serverRoute){
        try{

            byte[] receiveFileName = new byte[1024]; // Where we store the data of datagram of the name
            DatagramPacket receiveFileNamePacket = new DatagramPacket(receiveFileName, receiveFileName.length);
            socket.receive(receiveFileNamePacket); // Receive the datagram with the name of the file


            File f = new File (serverRoute + "\\" +"FromClient.csv" ); // Creating the file
            FileOutputStream outToFile = new FileOutputStream(f); // Creating the stream through which we write the file content

            receiveFile(outToFile, socket); // Receiving the file
        }catch(Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void receiveFile(FileOutputStream outToFile, DatagramSocket socket) throws IOException {

        boolean flag; // Have we reached end of file
        int sequenceNumber = 0; // Order of sequences
        int foundLast = 0; // The las sequence found

        while (true) {
            byte[] message = new byte[1024]; // Where the data from the received datagram is stored
            byte[] fileByteArray = new byte[1021]; // Where we store the data to be writen to the file

            // Receive packet and retrieve the data
            DatagramPacket receivedPacket = new DatagramPacket(message, message.length);
            socket.receive(receivedPacket);
            message = receivedPacket.getData(); // Data to be written to the file

            // Get port and address for sending acknowledgment
            InetAddress address = receivedPacket.getAddress();
            int port = receivedPacket.getPort();

            // Retrieve sequence number
            sequenceNumber = ((message[0] & 0xff) << 8) + (message[1] & 0xff);
            // Check if we reached last datagram (end of file)
            flag = (message[2] & 0xff) == 1;

            // If sequence number is the last seen + 1, then it is correct
            // We get the data from the message and write the ack that it has been received correctly
            if (sequenceNumber == (foundLast + 1)) {

                // set the last sequence number to be the one we just received
                foundLast = sequenceNumber;

                // Retrieve data from message
                System.arraycopy(message, 3, fileByteArray, 0, 1021);

                // Write the retrieved data to the file and print received data sequence number
                outToFile.write(fileByteArray);
                System.out.println("Received: Sequence number:" + foundLast);

                // Send acknowledgement
                sendAck(foundLast, socket, address, port);
            } else {
                System.out.println("Expected sequence number: " + (foundLast + 1) + " but received " + sequenceNumber + ". DISCARDING");
                // Re send the acknowledgement
                sendAck(foundLast, socket, address, port);
            }
            // Check for last datagram
            if (flag) {
                outToFile.close();
                break;
            }
        }
    }

    private static void sendAck(int foundLast, DatagramSocket socket, InetAddress address, int port) throws IOException {
        // send acknowledgement
        byte[] ackPacket = new byte[2];
        ackPacket[0] = (byte) (foundLast >> 8);
        ackPacket[1] = (byte) (foundLast);
        // the datagram packet to be sent
        DatagramPacket acknowledgement = new DatagramPacket(ackPacket, ackPacket.length, address, port);
        socket.send(acknowledgement);
        System.out.println("Sent ack: Sequence Number = " + foundLast);
    }

    public void update(){
        int x=gp.player.worldX/48;
        int y=gp.player.worldY/48;

        try{
            String c=x+";"+y;
            File file=new File("C:\\EscapePrizon\\FromClient.csv");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(c);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
