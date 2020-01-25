package cell;

public class GrassLand extends Land {

	public GrassLand() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String render(){
        if (berumput) return("img/lahan_susu_rumput.png");
        else return("img/lahan_susu_no.png");
    }

}
