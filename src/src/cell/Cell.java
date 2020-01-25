package cell;
import renderable.Renderable;

public abstract class Cell implements Renderable {
	boolean content;

	public Cell() {
		// TODO Auto-generated constructor stub
		content = false;
	}
	
	public boolean getContent(){
        return content;
    }

    public void setContent(boolean _content){
        content = _content;
    }

    public void setBerumput(boolean _berumput){}
    public boolean getBerumput() {return false;}

}
