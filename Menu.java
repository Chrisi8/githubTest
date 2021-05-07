package src.githubTest;
public class Menu {
	int nummer;
	int counter;
	
	public Menu(int nummer) {
		this.nummer=nummer;
		this.counter=0;
	}
	
	public void add() {
		this.counter=this.counter+1;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int i) {
		this.counter=i;
	}
	
	@Override
	public String toString() {
		return "Menü " + nummer + " : " + counter;
	}
	
}
