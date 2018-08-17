package com.cooksys.JavaAssessment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Server {

	public static void main(String[] args) {
		// open server and listen for connections
		try (ServerSocket ss = new ServerSocket(8080); Socket server = ss.accept();) {
			JAXBContext context = JAXBContext.newInstance(FileData.class);
			Unmarshaller um = context.createUnmarshaller();
			FileData fd = (FileData) um.unmarshal(server.getInputStream());

			System.out.printf("Server recieved: " + fd.getName() + fd.getDate(), fd.getFile());
			String filecontents = new String(fd.getFile(), "UTF-8");
			System.out.println(filecontents);

			// create directories
			File path = new File(fd.getName());
			System.out.println(path);
			File subdir = new File(path + "\\" + fd.getDate());

			if (!path.isDirectory()) {
				path.mkdir();
			}
			if (!subdir.isDirectory()) {
				subdir.mkdir();
			}
			try {
				File file = new File(subdir + "\\filecontents.txt");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(filecontents);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
