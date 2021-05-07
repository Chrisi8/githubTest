package src.githubTest;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MyJFrame extends JFrame{
	JMenuBar menue;
	Path path;
	
	public MyJFrame (String str) {
		super(str);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menuDatei;
		menuBar.add(menuDatei = new JMenu("Datei"));
		JMenuItem speichern, laden, trenner, beenden;
		menuDatei.add(speichern = new JMenuItem("Speichern"));
		menuDatei.add(laden = new JMenuItem("Laden"));
		menuDatei.add(trenner = new JMenuItem("------"));
		menuDatei.add(beenden = new JMenuItem("Beenden"));
		
		JPanel leftPanel = new JPanel();
		this.add(leftPanel, java.awt.BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout());
		JButton button1 = new JButton("Menü1");
		JButton button2 = new JButton("Menü2");
		JButton button3 = new JButton("Menü3");
		JButton button4 = new JButton("Menü4");
		leftPanel.add(button1);
		leftPanel.add(button2);
		leftPanel.add(button3);
		leftPanel.add(button4);
		
		Menu[] menu = new Menu[4];
		menu[0] = new Menu(1); 
		menu[1] = new Menu(2); 
		menu[2] = new Menu(3); 
		menu[3] = new Menu(4); 
		
		JList<Menu> list = new  JList<Menu>(menu);
		this.add(list, java.awt.BorderLayout.CENTER);
		
		Border emptey;
		emptey = BorderFactory.createEmptyBorder();
		TitledBorder title = BorderFactory.createTitledBorder(emptey, "Gewählte Menüs:");
		list.setBorder(title);
		
		JLabel label = new JLabel();
		label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
		this.add(label, java.awt.BorderLayout.SOUTH);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					menu[0].add();
					list.repaint();
					label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
				}catch(Exception ex) {};
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					menu[1].add();
					list.repaint();
					label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
				}catch(Exception ex) {};
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					menu[2].add();
					list.repaint();
					label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
				}catch(Exception ex) {};
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					menu[3].add();
					list.repaint();
					label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
				}catch(Exception ex) {};
			}
		});
		
		Path path = Paths.get("C:","Temp","Menu.txt");
		File file = path.toFile();
		
		/*Hier wird eine neue Datei Menu erzeugt, fals es vorher nicht existiert hat
		try{FileWriter fw = new FileWriter(file);}
		catch(Exception exc) {};
		*/
		
		speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file.exists()==true) {
					JOptionPane.showMessageDialog(MyJFrame.this,  "Überschreibe existierende Datei.");
				}
				//else {
				//wenn Datei vorher nicht existiert hat, wird sie neu erzeugt.
				
					try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
						for(int i=0; i<menu.length; i++) {
							bw.write((i+1) + ";"+ menu[i].getCounter());
							bw.newLine();
						}
						bw.flush();
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
				//}
				
			}
		});
		
		laden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(path.toFile().exists()==false) {
					JOptionPane.showMessageDialog(MyJFrame.this,  "Die Datei "+ file + " existiert nicht.");
				}
				
				try(BufferedReader br = new BufferedReader(new FileReader(file))){
					for (int i=0; i<menu.length; i++) {
						String str=br.readLine();
						if(str!=null) {
							String [] strs= str.split(";");
							Menu temp = new Menu(i+1);
							temp.setCounter(Integer.parseInt(strs[1]));
							menu[i]=temp;
						}
					}
					list.repaint();
					label.setText("Menüs insgesamt: " + (menu[0].getCounter()+menu[1].getCounter()+menu[2].getCounter()+menu[3].getCounter()));
				}
				catch(Exception ex) {};
				
			}
		});
		
		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	public static void main(String[] args)throws Exception{
		new MyJFrame("Betriebsfeier Menüs");
	}
}
