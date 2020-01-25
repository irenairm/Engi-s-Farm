package livingcreature;
import java.awt.Point;
import renderable.Renderable;


public abstract class LivingCreature implements Renderable {
	Point position;
	
	public LivingCreature(int x, int y) {
		// TODO Auto-generated constructor stub
		position = new Point(x,y);
	}
	public Point getPosition(){
        return position;
    }

    public void setX(int x){
        position.setLocation(x, position.getY());
    }

    public void setY(int y){
        position.setLocation(position.getX(), y);
    }

    public void Move(String direction){
        if (direction.equals("up")){
            setX((int)getPosition().getX()-1);
        }
        if (direction.equals("down")){
            setX((int)getPosition().getX()+1);
        }
        if (direction.equals("left")){
            setY((int)getPosition().getY()-1);
        }
        if (direction.equals("right")){
            setY((int)getPosition().getY()+1);
        }
    }

}

