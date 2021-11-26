import javafx.geometry.Rectangle2D;
import java.util.Random;


public class Foe extends AnimatedThing{

// fileName:nom de l'image
// x,y: où l'on place l'image sur la fenêtre
// camX, camY: les coordonnées que l'on veut de l'image
// width, height: la taille de l'image que l'on souhaite

    private double timePosition=0;
    private double offset = 45;
    private double index=0;



    public Foe(String fileName, double x, double y, double camX, double camY,double width,double height,double vx){
        super(fileName,x, y,camX,camY,width,height,vx);
    }


    // Il y a un nombre fini d'ennemi, chaque ennemi à des vitesses et des positions différentes suivant les niveaux
    public void updatefoe1 (double time, double vx,double xcam) {
        if (time > 1) time = 0;

        if (this.x>1500){GameScene.reached_level=2;}
        if (this.x>2900){GameScene.reached_level=3;}
        if (this.x>5000){GameScene.reached_level=4;}
        if (this.x>7000){GameScene.reached_level=5;}
        if (this.x>8000){GameScene.reached_level=6;}
        if (this.x>8500){GameScene.reached_level=7;}
        if (this.x>9400){GameScene.reached_level=8;}
        if (this.x>10000){GameScene.reached_level=9;}
        if (this.x>12000){GameScene.reached_level=9;}

        if (this.x > -100 + xcam) {
            this.x -= this.vx * time;
            getImageView().setX(this.x - xcam);

        } else {
            this.x = 1100 + xcam;
            this.y = genererInt(240, 310);
            getImageView().setY(y);
        }

        // niveau 4
        if(this.x>5000){this.vx=120;}
        //niveau 7
        if (this.x>9000){this.vx=150;}
        // niveau 8
        if (this.x>10000){this.vx=180;}
        // niveau 9
        if (this.x>11000){this.vx=200;}
        // niveau 10
        if (this.x>12000){this.vx=250;}
        // niveau final
        if (this.x>13000){this.vx=350;}



    }
        // pour l'ennemi vert
        public void updatefoe2 (double time, double vx,double xcam) {
            if (time > 1) time = 0;

            if (this.x > -100 + xcam) {
                this.x -= this.vx * time;
                getImageView().setX(this.x - xcam);
            } else {
                this.x = 1100 + xcam;
                this.y = genererInt(240, 310);
                getImageView().setY(y);
            }

            // niveau 4
            if (x>5500){this.vx=120;}

            // niveau 6
            if (this.x > 8000) {
                if (timePosition>8) {
                    timePosition=0;
                    this.y = genererInt(240, 270);
                    imageView.setY(y);// on place l'ennemi sur l'axe Y
                }
                timePosition+=1;
            }

            //niveau 7
            if (this.x>9000){this.vx=150;}
            // niveau 8
            if (this.x>10000){this.vx=180;}
            // niveau 9
            if (this.x>11000){this.vx=200;}
            // niveau 10
            if (this.x>12000){this.vx=250;}
            // niveau final
            if (this.x>13000){this.vx=350;}

        }


    // pour l'ennemi vert et violet
    public void updatefoe3 (double time, double vx,double xcam){
        timePosition+=1;
        if (time > 1) time = 0;

        if (this.x > -100 + xcam) {
            this.x -= this.vx * time;
            getImageView().setX(this.x - xcam);
        }

        else {
            this.x = 1100 + xcam;
            this.y = genererInt(270, 310);
            getImageView().setY(y);
        }

        if (timePosition > 8){ // on garde la même position un certain temps
                    timePosition = 0;
                    index += offset; // on change la position du hero
                    this.y = genererInt(240, 310);
                    imageView.setX(x - xcam);

        }
        // l'ennemi s'anime
        if (this.x>4000){
            imageView.setY(270); // on place l'ennemi sur l'axe Y
            imageView.setViewport(new Rectangle2D(index % 90, 0, offset, 100));
        }

        // niveau 5
        if (this.x>7000){
            imageView.setY(y); // on place l'ennemi sur l'axe Y
            imageView.setViewport(new Rectangle2D(index % 90, 0, offset, 100));
        }
        //niveau 7
        if (this.x>9000){this.vx=150;}
        // niveau 8
        if (this.x>10000){this.vx=180;}
        // niveau 9
        if (this.x>11000){this.vx=200;}
        // niveau 10
        if (this.x>12000){this.vx=250;}
        // niveau final
        if (this.x>13000){this.vx=350;}


    }

    // fonction pour générer un nombre aléatoire entre 2 bornes
    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }


    public double getVx(){return vx;}
    public double getX(){return x;}
    public double getY(){return y;}
    public void setX(double x){this.x=x;}

}
