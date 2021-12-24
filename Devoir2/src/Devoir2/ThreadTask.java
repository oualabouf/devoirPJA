package Devoir2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadTask implements Runnable {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ServerSocket soc;

	public ThreadTask(ServerSocket socket) {
		this.soc = socket;
	}

	@Override
	public void run() {

		while (true) {

			try {
				String command = "";
				Socket s = soc.accept();
				out = new ObjectOutputStream(s.getOutputStream());
				in = new ObjectInputStream(s.getInputStream());

				while (!command.equals("QUIT")) {

					command = (String) in.readObject();
					System.out.println(command);

					// path = "Get d:/test/filename.txt";

					String[] result = command.split(" ");

					if (result[0].equals("List")) {

						File file = new File(result[1]);

						if (file.exists()) {

							if (file.isDirectory()) {
								String[] directory = file.list();
								out.writeObject("Liste des fichiers contenus :");
								for (String directoryName : directory)
									out.writeObject(directoryName);
							}
						} else {
							out.writeObject("ERROR : " + result[1] + " does not exist");
						}

					} else {
						if (result[0].equals("Get")) {

							String strFileDirectoryPath = result[1];
							try {
								FileInputStream fstream = new FileInputStream(strFileDirectoryPath);
								DataInputStream i = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(i));
								String strLine;
								// Read File Line By Line

								while ((strLine = br.readLine()) != null) {
									out.writeObject(strLine);
								}

								i.close();
							} catch (Exception e) {// Catch exception if any
								out.writeObject("ERROR : " + result[1] + " does not exist");
							}

						} else {
							out.writeObject("ERROR : " + command + " is not a valable request");
						}
					}

					out.writeObject("<!--STOP-->");

				}
			} catch (Exception e) {
				System.out.println("Server Exception :" + e.getMessage());
			}
		}

	}

}
