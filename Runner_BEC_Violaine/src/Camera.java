
public class Camera {

    private double x;
    private double y;
    private double ax;
    private double vx=0;

    private double k=1;
    private double m=1;
    private double f=1.2;

    private AnimatedThing animation;



    public Camera(int x, int y, AnimatedThing animation ){
        this.x=x;
        this.y=y;
        this.animation=animation;

    }


    public void update(double time, double xhero){

        if (time>1) time=0;
        // équations en x
        ax = (k/m)*(xhero-x)-f*vx;
        vx=vx+ax*time;
        x=x+vx*time;
            }

    public double getVx(){return vx;}
    public double getX() {return x;}
    public void setX(double x){this.x=x;}
    public double getY(){return y;}


    @Override
    public String toString(){return x +"," + y;}
}