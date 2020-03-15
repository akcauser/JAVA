
package remotecircles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

//JPanel sınıfından kalıtım alıyoruz
public class RemoteCircles extends JPanel{
    
    //çember sayısı
    int n;
    
    //constructor methodunda kaç tane çember olacağını bildiriyorum. 
    //Bu uygulama için 10 ve 100 değerlerini kullanıcaz.
    public RemoteCircles(int n) {
        this.n = n;  
    }
    
    // Çemberleri tutan bir arraylist tanımladım. Kapasitesi n yani çember sayısı
    ArrayList<Circle> circleList = new ArrayList<>(n);
    
    //paint fonksiyonunu override ediyorum. Kendi ihtiyaçlarıma göre düzenleyeceğim.
    @Override
    public void paint(Graphics g)
    {   
        //Random sınıfından bir nesne türetiyorum
        Random rand = new Random();
        
        //çember sayısı kadar çember üreteceğim ve diziye ekleyeceğim.
        for(int i=0; i<n; i++)
        {   
            //rand nesnesi ile 0-400 arasında random sayıları üretiyorum
            //Ürettiğim bu sayıları çemberin x ve y kordinatlarında kullanıyorum
            int xCoordinate = rand.nextInt(400);
            int yCoordinate = rand.nextInt(400);
            
            //yarıçap değişkeni üretiyorum.
            int radius; 
            
            //Yarıçap değişkenine 25-50 arasında random değerler üretip radius değişkenine atama yapıyorum.
            //25-50 arasını seçmemin sebebi çok farklı yarıçaplar çıkarsa görüntü çok da güzel olmaz.
            do{
                radius = rand.nextInt(50);
            } while(radius < 25);
            
            //circle isimli Çember değişkeni üretip x ve y kordinatlarını ve yarıçapını constructor fonksiyonuna veriyorum
            Circle circle = new Circle(xCoordinate, yCoordinate, radius);
            
            // oluşturduğum bu çemberi çember dizisine ekliyorum
            circleList.add(circle);
            
            //çemberi çizmesi için de draw fonksiyonunu çağırıyorum
            circle.draw(g);
        }
        //en uzaktaki 2 çember için;
        int x1 = 0;    //1. çemberin x kordinatı
        int x2 = 0;    //2. çemberin x kordinatı   
        int y1 = 0;    //1. çemberin y kordinatı
        int y2 = 0;    //2. çemberin y kordinatı
        
        //bu iki değişkene en uzakta olan 2 çemberi bulup atama yapacağım
        Circle max1 = circleList.get(0); //1. çember 
        Circle max2 = circleList.get(1); //2. çember  
        
        double maxDistance = 0; //maksimum uzunluk
        double tempDistance = 0;    // geçici olarak kullanmamız gereken maksimum uzunluk
        
       //her çemberin diğer bütün çemberler ile arasındaki mesafeyi bulup en uzakta olan 2 çemberi seçiyorum
       //bu çemberleri de max1 ve max2 değişkenlerine atıyorum.
        for(int i = 0; i< n; i++){
            for(int j = (i+1) ; j< n; j++){
                
                //geçici uzaklık değişkenine sıradaki 2 çember arasındaki uzaklığı atıyorum
                tempDistance = circleList.get(i).distance(circleList.get(j));
                
                //eğer geçici değişken en son bulunan maksimum uzaklıktan büyük ise o 2 çemberi max1 ve max2 ye atama yapıyorum
                if(tempDistance > maxDistance){
                    max1 = circleList.get(i);
                    max2 = circleList.get(j);
                    x1 = circleList.get(i).x + (circleList.get(i).r / 2);   //seçtiğim 1.çemberin merkezinin x koordinatı
                    y1 = circleList.get(i).y + (circleList.get(i).r / 2);   //seçtiğim 1.çemberin merkezinin y koordinatı
                    x2 = circleList.get(j).x + (circleList.get(j).r / 2);   //seçtiğim 2.çemberin merkezinin x koordinatı
                    y2 = circleList.get(j).y + (circleList.get(j).r / 2);   //seçtiğim 2.çemberin merkezinin y koordinatı
                    maxDistance = tempDistance; //artık maksimum uzaklık değeri geçici değer olan değer  
                }
            }
        }
        
        //2D graphic kullanmamızın sebebi daha kalın çemberler çizme ihtiyacımızın olması.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3)); // 3 kalınlığına ayarlıyorum
        g2.setColor(Color.BLACK); //rengi siyah ayarlıyorum
        
        //en uzak 2 çemberi daha kalın olarak tekrar çiziyorum
        max1.draw(g2);
        max2.draw(g2);
       
        g.setColor(Color.red); // rengi kırmızıya ayarlıyorum
        g.drawLine(x1, y1, x2, y2); // en uzak iki çemberin merkezleri arasına kırmızı çizgi çiziyorum
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        // n=100 için bir ekran oluşturuyorum
        JFrame frame= new JFrame("N=100"); // title => N=100	
	frame.getContentPane().add(new RemoteCircles(100)); 
	frame.setSize(500, 500); //500px e 500px lik bir ekran olacak
	frame.setVisible(true); //Görünürlük true
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kapatma tuşuna bastığımızda yapılması gerekn işlem 
	frame.setResizable(false);  //Tekrar boyutlanamaz olarak ayarladım
        frame.setLocation(0, 0); //pencereyi ekranın 0'a 0 kordinatına yerleştir
        
        // n=10 için ayrı bir ekran oluşturuyorum
        JFrame frame10= new JFrame("N=10"); // title => N=10	 
	frame10.getContentPane().add(new RemoteCircles(10)); //
	frame10.setSize(500, 500);  //500px e 500px lik bir ekran olacak
	frame10.setVisible(true);   //Görünürlük true
	frame10.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kapatma tuşuna bastığımızda yapılması gereken işlem
	frame10.setResizable(false); //TEkrar boyutlanamaz oalrak ayarladım
        frame10.setLocation(500, 0); // pencereyi ekranın 500'e 0 kordinatına yerleştir
              
                
    }
    
}
