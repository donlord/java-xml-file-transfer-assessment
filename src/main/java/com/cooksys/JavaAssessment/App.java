package com.cooksys.JavaAssessment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Hello world!
 *
 */
public class App {

	public static Marshaller createMarshaller(JAXBContext context) {
		try {
			return context.createMarshaller();
		} catch (JAXBException e) {
			System.out.println("Failed to create marshaller");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FileData fd = new FileData();
		try {
			File file = new File("txt.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int linecounter = 1;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
				if (linecounter == 1) {
					fd.setName(line);
				}
				if (linecounter == 2) {
					fd.setDate(line);
				}
				if (linecounter == 3) {
					fd.setFile(line.getBytes());
				}
				linecounter++;
			}

			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// create jaxb context -> marshall -> file outputstream
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(FileData.class);
		} catch (JAXBException e) {
			System.out.println("Failed to create JAXBContext");
			e.printStackTrace();
		}
		// marshall file for xml output
		Marshaller m = createMarshaller(context);

		try (Socket client = new Socket("localhost", 8080)) {
			m.setProperty(m.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(fd, client.getOutputStream());
		} catch (JAXBException e) {
			System.out.println("Failed to marshall filedata Object:");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found:");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
