import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class AnimatedThing {

    protected double x;
    public double y;
    private double camX;
    private double camY;
    protected double vx=1;
    protected double vy=0;
    protected double ax=0;
    protected double ay=0;
    protected ImageView imageView;
    protected Image image;

    public String fileName;


    public AnimatedThing(String fileName, double x, double y, double camX, double camY, double width, double height,double vx){
        this.fileName=fileName;
        this.x=x;
        this.y=y;
        this.camX=x;
        this.camY=y;
        this.vx=vx;

        image=new Image(fileName);

        imageView= new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setViewport(new Rectangle2D(camX,camY ,width ,height));

    }


    public ImageView getImageView(){
        return imageView;
    }
    public double getX(){return (x);}



}
