package main;

import java.io.EOFException;
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

		Persona p1 = new Persona("1234", "Leire", 25);
		Persona p2 = new Persona("4321", "Begona", 26);

		File fich = new File("personas.dat");
		File fichAux = new File("personasAux.dat");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(fich));
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { ois=new ObjectInputStream(new FileInputStream(fich)); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * 
		 * try { for (int i=0;i<Utilidades.calculoFichero(fich);i++) { Persona
		 * nueva=(Persona)ois.readObject(); System.out.println(nueva.toString()); }
		 * ois.close(); } catch (ClassNotFoundException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		boolean finArchivo = false;
		try {
			ois = new ObjectInputStream(new FileInputStream(fich));

			// Leer mientras no se alcance el fin del archivo
			while (!finArchivo) {
				try {
					Persona aux = (Persona) ois.readObject();
					System.out.println(aux.toString());
				} catch (EOFException e) {
					// Fin del archivo alcanzado
					finArchivo = true;
				}
			}
			ois.close();

		} catch (Exception e) {
			System.out.println("Fatal error");
		}

		// Preguntamos al usuario que Persona quiere modificar.
		// Pedimos du DNI
		// Buscamos y eliminamos

		finArchivo = false;
		boolean modificado = false;
		System.out.println("Introduce tu dni: ");
		String dni = Utilidades.introducirCadena();
		try {
			ois = new ObjectInputStream(new FileInputStream(fich));
			oos = new ObjectOutputStream(new FileOutputStream(fichAux));
			// Leer mientras no se alcance el fin del archivo
			while (!finArchivo) {
				try {
					Persona aux = (Persona) ois.readObject();
					if (!aux.getDni().equals(dni)) {
						oos.writeObject(aux);
					} else {
						modificado = true;
					}
				} catch (EOFException e) {
					// Fin del archivo alcanzado
					finArchivo = true;
				}
			}
			oos.close();
			ois.close();
			if (modificado) {
				if (fich.delete()) {
					fichAux.renameTo(fich);
				}
			}

		} catch (Exception e) {
			System.out.println("Fatal error");
		}

		finArchivo = false;
		try {
			ois = new ObjectInputStream(new FileInputStream(fich));

			// Leer mientras no se alcance el fin del archivo
			while (!finArchivo) {
				try {
					Persona aux = (Persona) ois.readObject();
					System.out.println(aux.toString());
				} catch (EOFException e) {
					// Fin del archivo alcanzado
					finArchivo = true;
				}
			}
			ois.close();

		} catch (Exception e) {
			System.out.println("Fatal error");
		}
	}
}