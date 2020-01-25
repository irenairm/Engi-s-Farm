package cell;

public class Coop extends Land {

	public Coop() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String render(){
        if (berumput) return("img/lahan_egg_rumput.png");
        else return("img/lahan_egg_no.png");
    }

}
