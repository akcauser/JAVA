package remotecircle;

import edu.princeton.cs.introcs.StdDraw;

public class Circle {

    double x;   //Çemberin merkez X koordinatı
    double y;   //Çemberin merkez Y koordinatı   
    double r;   //Çemberin yarıçapı

    public Circle(double x, double y, double r) {

        this.x = x;
        this.y = y;
        this.r = r;

    }

    // draw fonksiyonun overload ettim
    //eğer graphic nesnesi ile parametre gelirse tek boyutlu çizimler yapacak
    //eğer graphic2D nesnesi ile parametre gelirse 2 boyutlu çizimler yapacak
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, r);

    }

    //seçtiğim çemberin verdiğim parametre ile arasındaki uzaklığı veren fonksiyon
    public double distance(Circle inputCircle) {
        double distance;

        //vektörel iki nokta arası uzaklığı bulmak için;
        //kullandığım formül |AB| = kök[ (x1 - x2)^2 + (y1 - y2)^2 ]
        distance = Math.sqrt(Math.pow(inputCircle.x - this.x, 2) + Math.pow(inputCircle.y - this.y, 2));

        //uzaklık değerini sonuç olarak dönderiyorum.
        return distance;
    }

}
