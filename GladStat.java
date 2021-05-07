package src.githubTest;
public class GladStat {

    private String _name;
    private int _gemachteKaempfe;
    private int _siege;
    private int _niederlagen;
    private double _siegquote;
    boolean _lebendig = true;

    //GladStat one; man brauch hier keine GladStat Objekte drin
   // GladStat two;

    //private Arena _arena;                              // muss ich eine neue Arena Eerzeugen ????    oder woher soll die Klasse die notwendige Daten bekommen
	// nein du musst keine Arena erzeugen,welche daten meinst du? ich habe erst in der KampfstatTabelle eine Arena in der fight Methode erzeugt.


    public GladStat(String Name, int gemachteKaempfe, int siege, int niederlagen, /*double siegquote,*/ boolean lebendig) { // ich würde vielleicht keine Siegquote uebergeben, da man die selber ausrechnen kann und so fehler von der Eingabe vermeidet

        this._name = Name;
        this._gemachteKaempfe = gemachteKaempfe;
        this._siege = siege;
        this._niederlagen = niederlagen;

        this._siegquote = (siege / niederlagen);
        //this._siegquote = siegquote;
        this._lebendig = lebendig;

        //this._arena= new Arena(); brachst du nicht
    }

    	//fals this besser ist als gl wird 1, fals this gleich gl 0 und fals this schlechter als gl -1 zurÃ¼ckgegeben.
	public int compareTo(GladStat gl) {


        //one = new GladStat(_name, _gemachteKaempfe, _siege, _niederlagen, _siegquote, _lebendig);
        //two = new GladStat(_name, _gemachteKaempfe, _siege, _niederlagen, _siegquote, _lebendig);

        //int bessereGlad = one.compareTo(two);
	//Du muss die miteinader vergleichen

        if (this._siege > gl._siege) {                                                        //siege vergleich
            System.out.println(this._name + " is greater GladStat "+ gl._name);		
            return 1;
        }
        else if (this._siege < gl._siege) {
            System.out.println(gl._name + " is greater GladStat "+ this._name);
            return -1;
        }

		else if(this._siegquote>gl._siegquote){
			return 1;
		}
		else if(this._siegquote<gl._siegquote){
			return -1;
		}
		else if(this._lebendig && (gl._lebendig==false)){
			return 1;
		}
		else if((this._lebendig==false) && gl._lebendig){
			return -1;
		}
		else{
			if(this._name.compareTo(gl._name)>0){
				return 1;
			}
			else if(this._name.compareTo(gl._name)<0){
				return -1;
			}
			else{
				return 0;
			}
		}

        /*else {
            System.out.println("GlasStat one is equal to GladStat two");
	
                if (_siegquote > 0) {                                                        //siegquote vergleich
                System.out.println("GladStatone is greater GladStat two");
                return bessereGlad;
                }
                 else if (_siegquote < 0) {
                System.out.println("GladStat one is less GladStat two");
                return bessereGlad;
               }
                 else {
                System.out.println("GlasStat one is equal to GladStat two");
                         //-------- boolean lebendig
                         if (_lebendig == true) {                                                        //lebendig oder Tot
                         System.out.println("GladStatone is greater GladStat two");
                        return bessereGlad;

                        }
                        else {
                        System.out.println("GlasStat one is lower to GladStat two");

                        }
            }

            return bessereGlad;
        }*/


    }

    public static void main(String[]args){               

	//Zuafllwerte eingefügt , wei kann ich (ohne fehler comperTo anwenden???) wenn compare überhaupt stimmt
        GladStat gl1 = new GladStat("Spartacus",7,5,1,true);
        GladStat gl2 =new GladStat("Spartacus",4,2,1,true);
      //fals this besser ist als gl wird 1, fals this gleich gl 0 und fals this schlechter als gl -1 zurÃ¼ckgegeben.
        int i = gl1.compareTo(gl2); //hier sollte eins in 1 sein, da gl1 mehr siege als gl2 hat
        System.out.println(i);



    }

}
