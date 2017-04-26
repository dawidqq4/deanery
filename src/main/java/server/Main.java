package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import classes.JDBC.DziekanatDAO;
import classes.deanery.*;


public class Main {

	private static ServerSocket serverSocket;
	private static Socket socket;

	public static void main(String[] args) throws IOException {
		DziekanatDAO database = new DziekanatDAO();
		serverSocket = new ServerSocket(54321);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		while (true) {
			socket = serverSocket.accept();
			Runnable connection = new Runnable() {
				@Override
				public void run() {
					Dziekan dz = null;
					Wydzial w = null;
					Kierunek k = null;
					Rok r = null;
					Grupa g = null;
					Student s = null;
					Katedra kat = null;
					Przedmiot prz = null;
					Wykladowca wyk = null;
					Sala sala = null;
					Ocena ocena = null;
					Zajecia zajecia = null;
					User user = null;
					String tmp = "";
					try {
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						while (true) {
							tmp = (String) ois.readObject();
							switch (tmp) {
							case "adddz":
								dz = (Dziekan) ois.readObject();
								database.insertDziekan(dz);
								continue;
							case "addwy":
								w = (Wydzial) ois.readObject();
								database.insertWydzial(w);
								continue;
							case "addkier":
								k = (Kierunek) ois.readObject();
								database.insertKierunek(k);
								continue;
							case "addrok":
								r = (Rok) ois.readObject();
								database.insertRok(r);
								continue;
							case "addgrupa":
								g = (Grupa) ois.readObject();
								database.insertGrupa(g);
								continue;
							case "addstudent":
								s = (Student) ois.readObject();
								database.insertStudent(s);
								continue;		
							case "addkatedra":
								kat = (Katedra) ois.readObject();
								database.insertKatedra(kat);
								continue;	
							case "addprzedmiot":
								prz = (Przedmiot) ois.readObject();
								database.insertPrzedmiot(prz);
								continue;	
							case "addwykladowca":
								wyk = (Wykladowca) ois.readObject();
								database.insertWykladowca(wyk);
								continue;
							case "addsala":
								sala = (Sala) ois.readObject();
								database.insertSala(sala);
								continue;
							case "addocena":
								ocena = (Ocena) ois.readObject();
								database.insertOcena(ocena);
								continue;
							case "addzajecia":
								zajecia = (Zajecia) ois.readObject();
								database.insertZajecia(zajecia);
								continue;
							case "adduser":
								user = (User) ois.readObject();
								database.insertUser(user);
								continue;
							case "showdz":
								LinkedList<Dziekan> dziekani = database.selectDziekan();
								for (Dziekan x : dziekani) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showwy":
								LinkedList<Wydzial> wydzialy = database.selectWydzial();
								for (Wydzial x : wydzialy) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showkier":
								LinkedList<Kierunek> kierunki = database.selectKierunek();
								for (Kierunek x : kierunki) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showrok":
								LinkedList<Rok> roczniki = database.selectRok();
								for (Rok x : roczniki) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showgrupa":
								LinkedList<Grupa> grupy = database.selectGrupa();
								for (Grupa x : grupy) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showstudent":
								LinkedList<Student> studenci = database.selectStudent();
								for (Student x : studenci) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showkatedra":
								LinkedList<Katedra> katedry = database.selectKatedra();
								for (Katedra x : katedry) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showprzedmiot":
								LinkedList<Przedmiot> przedmioty = database.selectPrzedmiot();
								for (Przedmiot x : przedmioty) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showwykladowca":
								LinkedList<Wykladowca> wykladowcy = database.selectWykladowca();
								for (Wykladowca x : wykladowcy) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showsala":
								LinkedList<Sala> sale = database.selectSala();
								for (Sala x : sale) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showocena":
								LinkedList<Ocena> oceny = database.selectOcena();
								for (Ocena x : oceny) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "showocenastudent":
								s = (Student) ois.readObject();
								LinkedList<Ocena> selectOceny = database.selectOcena();
								for (Ocena x : selectOceny) {
									if (s.getidStudent() == x.getIdStudent()) {
										System.out.println("TU?");
										oos.writeObject(x);
									}
									else
										continue;
								}
								oos.writeObject(null);
								continue;
							case "showzajecia":
								LinkedList<Zajecia> zajeciaList = database.selectZajecia();
								for (Zajecia x : zajeciaList) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "editdz":
								dz = (Dziekan) ois.readObject();
								database.editDziekan(dz);
								continue;
							case "editwy":
								w = (Wydzial) ois.readObject();
								database.editWydzial(w);
								continue;
							case "editkier":
								k = (Kierunek) ois.readObject();
								database.editKierunek(k);
								continue;
							case "editrok":
								r = (Rok) ois.readObject();
								database.editRok(r);
								continue;
							case "editgrupa":
								g = (Grupa) ois.readObject();
								database.editGrupa(g);
								continue;
							case "editstudent":
								s = (Student) ois.readObject();
								database.editStudent(s);
								continue;
							case "editkatedra":
								kat = (Katedra) ois.readObject();
								database.editKatedra(kat);
								continue;
							case "editprzedmiot":
								prz = (Przedmiot) ois.readObject();
								database.editPrzedmiot(prz);
								continue;
							case "editwykladowca":
								wyk = (Wykladowca) ois.readObject();
								database.editWykladowca(wyk);
								continue;
							case "editsala":
								sala = (Sala) ois.readObject();
								database.editSala(sala);
								continue;
							case "editocena":
								ocena = (Ocena) ois.readObject();
								database.editOcena(ocena);
								continue;
							case "editzajecia":
								zajecia = (Zajecia) ois.readObject();
								database.editZajecia(zajecia);
								continue;
							case "deletedz":
								dz = (Dziekan) ois.readObject();
								database.deleteDziekan(dz);
								continue;
							case "deletewy":
								w = (Wydzial) ois.readObject();
								database.deleteWydzial(w);
								continue;
							case "deletekier":
								k = (Kierunek) ois.readObject();
								database.deleteKierunek(k);
								continue;
							case "deleterok":
								r = (Rok) ois.readObject();
								database.deleteRok(r);
								continue;
							case "deletegrupa":
								g = (Grupa) ois.readObject();
								database.deleteGrupa(g);
								continue;
							case "deletestudent":
								s = (Student) ois.readObject();
								database.deleteStudent(s);
								continue;
							case "deletekatedra":
								kat = (Katedra) ois.readObject();
								database.deleteKatedra(kat);
								continue;
							case "deleteprzedmiot":
								prz = (Przedmiot) ois.readObject();
								database.deletePrzedmiot(prz);
								continue;
							case "deletewykladowca":
								wyk = (Wykladowca) ois.readObject();
								database.deleteWykladowca(wyk);
								continue;
							case "deletesala":
								sala = (Sala) ois.readObject();
								database.deleteSala(sala);
								continue;
							case "deleteocena":
								ocena = (Ocena) ois.readObject();
								database.deleteOcena(ocena);
								continue;
							case "deletezajecia":
								zajecia = (Zajecia) ois.readObject();
								database.deleteZajecia(zajecia);
								continue;
							case "showusers":
								LinkedList<User> users = database.selectUsers();
								for (User x : users) {
									oos.writeObject(x);
								}
								oos.writeObject(null);
								continue;
							case "confrimuser":
								user = (User) ois.readObject();
								database.confrimUser(user);
								continue;
							case "deleteuser":
								user = (User) ois.readObject();
								database.deleteUser(user);
								continue;
							case "exitserver":
								System.exit(1);
							}
						}
					} catch (IOException e) {
						System.out.println("B³¹d w odbiorze danych");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			};
			executorService.submit(connection);
		}
	}
}
