package com.cooksys.JavaAssessment;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket s;

	public ClientHandler(Socket s) {
		this.s = s;
	}
	
	public static void main(String[]args) {
		BufferedInputStream bis = new BufferedInputStream(new DataInputStream(s.getInputStream()));
			
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
