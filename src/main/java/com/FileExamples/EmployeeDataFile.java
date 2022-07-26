/*empl app 
	id 
	name
	salary
	
5000 
	backup -> file  
	
	read 
	
1,ram,15000
2,laxman,20000
3,raja,5000
4,hero,15000*/


package com.FileExamples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EmployeeDataFile {
	public static void main(String[] args) throws IOException {

		File f = new File("C:\\JAVA\\java\\Employee.txt");

		Scanner sc = new Scanner(System.in);

		PrintWriter pw = new PrintWriter(f);
		for (int i = 0; i <= 1; i++) {
			System.out.println("enter your id");
			int Id = sc.nextInt();
			System.out.println("enter name:");
			String Name = sc.next();
			System.out.println("enter your salary");
			int Salary = sc.nextInt();
			pw.println(Id + " " + Name + " " + Salary);
			
		}
		pw.close();

		BufferedReader br = new BufferedReader(new FileReader(f));
		
		while (true) {

			int i = br.read();
			while (i != -1) {
				System.out.print((char) i);

				// Reads next byte from the file
				i = br.read();

			}

		}
	}

		/*
		 * String s = br.readLine();
		 * 
		 * if (s == null) { break; }
		 * 
		 * if (s != null) {
		 * 
		 * System.out.println(s);
		 * 
		 * } } br.close();
		 */ 

	class string {
		int Id;
		String Name;
		int Salary;

		@Override
		public String toString() {

			return Id + Name + Salary;
		}

	}
}
