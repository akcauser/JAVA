package remotecircle;

import edu.princeton.cs.introcs.StdDraw;
import java.util.Random;

public class RemoteCircle {

    public static void main(String[] args) {
        //çember sayısı
        int n = 100;

        StdDraw.setCanvasSize(500, 500);

        // Çemberleri tutan bir array tanımladım. Kapasitesi n yani çember sayısı
        Circle[] circleList = new Circle[100];

        //Random sınıfından bir nesne türetiyorum
        Random rand = new Random();

        //çember sayısı kadar çember üreteceğim ve diziye ekleyeceğim.
        for (int i = 0; i < n; i++) {
            //yarıçap değişkeni üretiyorum.
            double radius;

            radius = rand.nextDouble();
            while (radius > 0.1 || radius < 0.05) {
                radius = rand.nextDouble();
            }

            double xCoordinate = rand.nextDouble();
            while (xCoordinate > (1.0 - radius) || xCoordinate < radius) {
                xCoordinate = rand.nextDouble();
            }

            double yCoordinate = rand.nextDouble();
            while (yCoordinate > (1.0 - radius) || yCoordinate < radius) {
                yCoordinate = rand.nextDouble();
            }

            //circle isimli Çember değişkeni üretip x ve y kordinatlarını ve yarıçapını constructor fonksiyonuna veriyorum
            Circle circle = new Circle(xCoordinate, yCoordinate, radius);

            // oluşturduğum bu çemberi çember dizisine ekliyorum
            circleList[i] = circle;

            //çemberi çizmesi için de draw fonksiyonunu çağırıyorum
            circle.draw();
        }

        //bu iki değişkene en uzakta olan 2 çemberi bulup atama yapacağım
        Circle max1 = circleList[0]; //1. çember 
        Circle max2 = circleList[1]; //2. çember  

        double maxDistance = 0;     //maksimum uzunluk
        double tempDistance = 0;    // geçici olarak kullanmamız gereken maksimum uzunluk

        //her çemberin diğer bütün çemberler ile arasındaki mesafeyi bulup en uzakta olan 2 çemberi seçiyorum
        //bu çemberleri de max1 ve max2 değişkenlerine atıyorum.
        for (int i = 0; i < n; i++) {
            for (int j = (i + 1); j < n; j++) {

                //geçici uzaklık değişkenine sıradaki 2 çember arasındaki uzaklığı atıyorum
                tempDistance = circleList[i].distance(circleList[j]);

                //eğer geçici değişken en son bulunan maksimum uzaklıktan büyük ise o 2 çemberi max1 ve max2 ye atama yapıyorum
                if (tempDistance > maxDistance) {
                    max1 = circleList[i];
                    max2 = circleList[j];

                    maxDistance = tempDistance; //artık maksimum uzaklık değeri geçici değer olan değer  
                }
            }
        }

        //en uzak 2 çemberi daha kalın olarak tekrar çiziyorum
        StdDraw.setPenRadius(0.005);
        max1.draw();
        max2.draw();

        StdDraw.setPenColor(StdDraw.RED); // rengi kırmızıya ayarlıyorum
        StdDraw.line(max1.x, max1.y, max2.x, max2.y); // en uzak iki çemberin merkezleri arasına kırmızı çizgi çiziyorum

    }

}
