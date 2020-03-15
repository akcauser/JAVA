
package remotecircles;

import java.awt.*;

public class Circle {
    int x;   //Çemberin merkez X koordinatı
    int y;   //Çemberin merkez Y koordinatı   
    int r;   //Çemberin yarıçapı

    public Circle(int x, int y, int r) {
        
        this.x = x;
        this.y = y;
        this.r = r;
            
    }
    // draw fonksiyonun overload ettim
    //eğer graphic nesnesi ile parametre gelirse tek boyutlu çizimler yapacak
    //eğer graphic2D nesnesi ile parametre gelirse 2 boyutlu çizimler yapacak
    public void draw(Graphics g){
        g.drawOval(x, y, r, r);
    }
    
    //graphic2D yi kalın kenarlar çizmek için kullanıcam
    public void draw(Graphics2D g){
        g.drawOval(x, y, r, r);
    }
    
    //seçtiğim çemberin verdiğim parametre ile arasındaki uzaklığı veren fonksiyon
    public double distance(Circle inputCircle){
        double distance = 0;
        
        //vektörel iki nokta arası uzaklığı bulmak için;
        //kullandığım formül |AB| = kök[ (x1 - x2)^2 + (y1 - y2)^2 ]
        distance = Math.sqrt(Math.pow(inputCircle.x - this.x, 2) + Math.pow(inputCircle.y - this.y, 2));
        
        //uzaklık değerini sonuç olarak dönderiyorum.
        return distance;
    }
    
    
    
}