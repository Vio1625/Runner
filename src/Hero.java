import javafx.geometry.Rectangle2D;
import java.util.ArrayList;

public class Hero extends AnimatedThing {


    public double attitude = 0; //0:run 1:jump up 2:jump down
    private int timePosition = 0; // détermine le temps durant lequel le joueur garde une postion
    private final double ysol = 245;
    private double invicibility = 0;


    private Boolean accelerate = false;
    private int index = 0;
    private final int maximumIndex = 85 * 6; // Chaque position du héro mesure 85 pixels, il y a 6 positions
    private final double offset = 85; // taille en pixel du héro selon x

    private ArrayList<Double> taby = new ArrayList();
    private boolean isInvicible = false;
    private double time_invicible;



    public Hero(String fileName, double x, double y, double camX, double camY, double width, double height, double vx) {
        super(fileName, x, y, camX, camY, width, height, vx);
    }

    // fonction qui check les collisions
    public boolean getHitBox(Foe foe) {
        time_invicible = 2;
        for (int indice = 0; indice < 6; indice++) {
            taby.add(y + 20 * indice);
        }
        for (int indice = 0; indice < 6; indice++) {
            if ((foe.getX() - 2 < x + 40) && (x + 40 < foe.getX() + 40)
                    && (foe.getY() < taby.get(indice)) && (taby.get(indice) < foe.getY() + 32)) // attention trop pour le début -->
            {
                System.out.println("collision");
                return true;
            }
        }
        taby.clear();
        return false;
    }

    // fonction qui garde l'invincibilité pendant un certain temps
    public void isInvicibleFunction(double time) {

        if (time_invicible > 0) {
            time_invicible = time_invicible - time;
        } else {
            isInvicible = false;
        }
    }


    // fonction servant à afficher le héro courant sur place sur le menu
    public void menuhero(double time) {
        if (time > 1) time = 0;
        if (timePosition > 8) // on garde la même position un certain temps
        {
            timePosition = 0;
            index += offset; // on change la position du hero
            imageView.setY(280); // on place le hero sur l'axe Y
            imageView.setViewport(new Rectangle2D(index % maximumIndex, 0, offset, 100)); // on prend la bonne position dans l'image des héros
            imageView.setX(630);
        }
        timePosition += 1;
    }


    public void update(double time, double xcam) {

        if (time > 1) time = 0;

        if (accelerate) {
            ax = 1000 - vx;
        } else if (vx > 400) {
            ax = -0.5 * vx;
        } else {
            ax = 0;
            vx += ax * time;
            x += vx * time;
        }

        timePosition += 1;


        if (timePosition > 8) // on garde la même position un certain temps
        {
            timePosition = 0;
            index += offset; // on change la position du hero
            if (attitude == 0) {
                y = 245;
                imageView.setY(y); // on place le hero sur l'axe Y
                if (index % 2 == 0 & !isInvicible) {
                    imageView.setViewport(new Rectangle2D(index % maximumIndex, 0, offset, 100)); // on prend la bonne position dans l'image des héros
                }
                if (index % 2 == 0 & isInvicible) {
                    imageView.setViewport(new Rectangle2D(-100, 0, offset, 100)); // on prend la bonne position dans l'image des héros
                }
                if (index % 2 == 1) {
                    imageView.setViewport(new Rectangle2D(index % maximumIndex, 0, offset, 100)); // on prend la bonne position dans l'image des héros
                }
            }
        }


        // jump up
        if (attitude == 1) {
            imageView.setViewport(new Rectangle2D(0, 160, offset, 100));
        }

        //jump down
        if (attitude == 2) {
            imageView.setViewport(new Rectangle2D(85, 160, offset, 100));
        }

        imageView.setX(x - xcam);
         if (attitude==1 && vy<0){attitude =2;}

        // si on saute: équations en y
        if (attitude == 1 || attitude ==2){
            ay=ay-9.81*1000*time;
            vy=vy+ay*time;
            y=y-vy*time;
        }

        if (y>ysol){
            y=245;
            vy=0;
            ay=0;
            if (attitude ==2){attitude=0;}
        }
        imageView.setY(y);
    }


    public boolean islevelup(){
        boolean levelup = false;
        if ((1500<this.x && this.x<1750) //niveau 2
        ||(2900<this.x && this.x<3150)  // niveau 3
        ||(5000<this.x && this.x<5250)  // niveau 4
        ||(7000<this.x && this.x<7250)  // niveau 5
        ||(8000<this.x && this.x<8250)  // niveau 6
        ||(9000<this.x && this.x<9250)  // niveau 7
        ||(10000<this.x && this.x<10250)  // niveau 8
        ||(11000<this.x && this.x<11250))  // niveau 9
            {levelup =true;}

        else {
            levelup =false;}
        return (levelup);
    }

    public double getX(){return (x);}
    public void setX(double x){this.x=x;};
    public double getY(){return (y);}
    public void setAy(double ay){this.ay=ay;}
    public boolean getisInvicible(){return isInvicible;}
    public void setisInvicible(boolean isInvicible){this.isInvicible=isInvicible;}

}
