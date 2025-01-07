package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import clases.*;
import utilidades.Utilidades;

public class FicherosMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		File fich=new File("personas.obj");
		
		Persona p1 = new Persona("123123w","Igor",19);
		Persona p2 = new Persona("123123w","katia",18);
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(fich));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			ois=new ObjectInputStream(new FileInputStream(fich));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			
			for(int i=0;i<Utilidades.calculoFichero(fich);i++)
			{
				Persona nueva1=(Persona)ois.readObject();
				System.out.println(nueva1.toString());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
