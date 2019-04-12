import java.util.ArrayList;
import java.util.Collections;

public class TestMain {
    final public int maxIterations = 50;
    final public int lifetime = 6;
    public int lsc = 1;
    public int areaLimit = 100;
    public double transferrate = 0.1;
    public int GSC = 2;
    public static void main(String[] args) {
        int i = 0;
        Forest f = new Forest();
        f.initForest(10,10.0,4.5,12.0,5.5,10.0,1.5);
        while(i<=100){
            f.LocalSeed(1);
            f.areaLimit(200,10,12.0 );
            f.GlobalSeed();
            f.update(12.0);
            i++;
        }
        ArrayList<Double> result = f.eva(12.0);
        int indexmin = result.indexOf(Collections.min(result));
        System.out.println(f.forest.get(indexmin).getX1().getVal());
        System.out.println(f.forest.get(indexmin).getX2().getVal());
        System.out.println(f.forest.get(indexmin).getX3().getVal());
    }
}
