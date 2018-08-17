package com.cooksys.JavaAssessment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class FileData {

	@XmlElement
	private String name;
	@XmlElement
	private String date;
	@XmlElement
	private byte[] file;

	public FileData() {
	}

	public FileData(String n, String d, byte[] b) {
		this.name = n;
		this.date = d;
		this.file = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] b) {
		this.file = b;
	}

}
