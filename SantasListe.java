package src.githubTest;
import java.nio.file.Paths;
import java.nio.file.*;
import java.io.*;

public class SantasListe{
	
	private final String CSTR_TRENN_ZEICHEN= ";";
	
	private class ListenEintrag{
		String _nameKind;
		String _geschenkGross;
		int _anzahlNuesse;
		int _anzahlOrangen;
		
		public ListenEintrag(String nameKind, String geschenkGross, int anzahlNuesse,int anzahlOrangen){
			if (nameKind.contains(CSTR_TRENN_ZEICHEN)) throw new RuntimeException ("Kindname enthält '"+CSTR_TRENN_ZEICHEN+"'!"); // Auch bei setter-Methoden machen, wenn vorhanden!
			if (geschenkGross.contains(CSTR_TRENN_ZEICHEN)) throw new RuntimeException ("Variable geschenkGross enthält ';'!");// Auch bei setter-Methoden machen, wenn vorhanden!
			_nameKind=nameKind;
			_geschenkGross=geschenkGross;
			_anzahlNuesse=anzahlNuesse;
			_anzahlOrangen=anzahlOrangen;
		}
			
	    public String toString () {
			return _nameKind+ ": "+_geschenkGross+"+"+_anzahlNuesse+" Nuesse"+"+"+_anzahlOrangen+" Orangen";
		}	
		
		public String getKindName(){
			return _nameKind;
		}
		
		public String getGeschenkGross(){
			return _geschenkGross;
		}
		
		public int getAnzahlNuesse(){
			return _anzahlNuesse;
		}
		
		public int getAnzahlOrangen(){
			return _anzahlOrangen;
		}
	}
	
	ListenEintrag[] _liste= new ListenEintrag[0];
	
	private void addToListe(ListenEintrag le){
		ListenEintrag[] l= new ListenEintrag[_liste.length+1];
		
		for(int i=0; i<_liste.length; i++){
			l[i]= _liste[i];
		}
		l[l.length-1]= le;
		
		_liste= l;
	}
	
	
	
	private void gibListeAus(){
		for(int i=0; i<_liste.length; i++){
			System.out.println(_liste[i]);
		}
	}
	
	/*ab hier Dateibearbeitung*/
	//wenn es vorher keine SantasListe.csv Datei gab, nach dieser Methode gibt es die, wenn es vorher schon einen Temp Ordner gab.
	private static File getFileToSantasListe() {
		
		Path path= Paths.get("C:","Temp","SantasListe.csv");
		//Path path=Paths.get(".","SantasListe.csv");
		return path.toFile();
	}
	
	private void schreibeInDatei(){
		File file= getFileToSantasListe() ;
		schreibeInDatei(file);
	}
	
	private void schreibeInDatei(File file){
		
		if(file.exists()==true){
			System.out.println("Ueberschreibe existierende Datei "+file.toString());
		}
		
		BufferedWriter bw=null;
		try { 
			 FileWriter fw = new FileWriter(file); 
			 bw = new BufferedWriter(fw); 

			 for(int i=0; i<_liste.length; i++){
				ListenEintrag le=_liste[i];
				bw.write(le.getKindName()+CSTR_TRENN_ZEICHEN+le.getGeschenkGross()+CSTR_TRENN_ZEICHEN+le.getAnzahlNuesse()+CSTR_TRENN_ZEICHEN+le.getAnzahlOrangen());
				bw.newLine(); // Neue Zeile einfügen
			 }
			 bw.flush(); // Schreibt alles sicher weg
		 } 
		catch (Exception ex) { 
			ex.printStackTrace(); 
		} 
		finally{
			try{
				if(bw!=null){bw.close();} 
			} 
			catch(IOException ioex){} //Tue nichts, wenn das eine Exception wirft!
		}
	}
	
	private void verarbeiteZeile(String strZeile){
	    String [] strs= strZeile.split(CSTR_TRENN_ZEICHEN);	  //String splitten mittels vereinbartem Trennzeichen
		if(strs.length!=4){
			throw new RuntimeException("Folgende Zeile kann nicht geparst werden, weil nicht vier Strings entstehen:" + strZeile);
		}
		
		int iNuesse=0,iOrangen=0;
		try{
			iNuesse=Integer.parseInt(strs[2].trim());
			iOrangen=Integer.parseInt(strs[3].trim());
		}catch(Exception ex){
			throw new RuntimeException("Folgende Zeile kann nicht geparst werden, weil wohl ein String kein Zahlwert ist wie verlangt:" + strZeile);
		}
		
		ListenEintrag le= new ListenEintrag(strs[0].trim(),strs[1].trim(),iNuesse,iOrangen);
		this.addToListe(le);
	}

	
	private void liesListeAusDatei()throws IOException{
		File file= getFileToSantasListe() ;
		liesListeAusDatei(file);
	}
	
	private void liesListeAusDatei(File file)throws IOException{
		if(file.exists()==false) {
			throw new IOException("Datei '"+file+"' existiert nicht!");
		}
		
		BufferedReader br= null;
		try {
			FileReader fr=new FileReader(file);
			br=new BufferedReader(fr);
			
			String strZeile=br.readLine();
			while(strZeile!=null){
				 verarbeiteZeile(strZeile);
				 strZeile=br.readLine();
			}
		}
		catch (Exception ex) { 
			ex.printStackTrace(); 
		} 
		finally{
			try{  if(br!=null){br.close();} } 
			catch(IOException ioex){} //Tue nichts, wenn das eine Exception wirft!
		}
	}


	private void fuelleListeMitEinigenWerten(){
		this.addToListe(new ListenEintrag("Petra","Schaukelpferd",20,5));
		this.addToListe(new ListenEintrag("Otto","Eisenbahn",15,4));
		this.addToListe(new ListenEintrag("Max","Buch \"Ich muss braver werden\"",1,0));
		this.addToListe(new ListenEintrag("Moritz","Buch \"Ich muss braver werden\"",1,1));
		this.addToListe(new ListenEintrag("Nicole","Barbie",4,4));
	}	
	
	public static void erzeugeListeUndSchreibeInDatei(){
		SantasListe sl=new SantasListe();
		sl.fuelleListeMitEinigenWerten();
		sl.schreibeInDatei();
		
	}
	
	public static void leseListeAusDatei(){
		try{
			SantasListe sl=new SantasListe();
			sl.liesListeAusDatei();
			sl.gibListeAus();
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Lesen aus Datei nicht erfolgreich!");
		}
	}
	
	
	
	public static void main(String [] args){
			if (args.length==1){
				if(args[0].equalsIgnoreCase("/l")){
					leseListeAusDatei();
					return;
				}
				else if(args[0].equalsIgnoreCase("/s")){
					erzeugeListeUndSchreibeInDatei();
					return;
				}
			}
			
			System.out.println("Benutzung:\n"
								+"   SantasListe /l   -> Liest die Liste von der Datei und gibt sie aus.\n"
								+"   SantasListe /s   -> Erzeugt Liste und schreibt sie in Datei.\n");
			
	}
	
	
	
}