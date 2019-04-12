import java.util.Random;

public class Tree {
    public int age;
    public Leaf x1 = new Leaf();
    public Leaf x2 = new Leaf();
    public Leaf x3 = new Leaf();
    public void Tree(int a,Leaf b,Leaf c,Leaf d){
        this.age = a;
        this.x1 = b;
        this.x2 = c;
        this.x3 = d;
    }
    //初始化一棵树，需要给出树叶的最大以及平均值，树叶的值随机获取
    public Tree initTree(double max1,double avg1,double max2,double avg2,double avg3,double max3){
        Tree result = new Tree();
        Leaf b = new Leaf();
        b.setMax(max1);
        b.setAvg(avg1);
        Leaf c = new Leaf();
        c.setMax(max2);
        c.setAvg(avg2);
        Leaf d = new Leaf();
        d.setMax(max3);
        d.setAvg(avg3);
        double rb = (double)(Math.random()*b.max);
        double rc = (double)(Math.random()*c.max);
        double rd = (double)(Math.random()*d.max);
        b.setVal(rb);
        c.setVal(rc);
        d.setVal(rd);
        result.setX1(b);
        result.setX2(c);
        result.setX3(d);
        result.setAge(0);
        return result;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setX1(Leaf x1) {
        this.x1 = x1;
    }

    public void setX2(Leaf x2) {
        this.x2 = x2;
    }

    public void setX3(Leaf x3) {
        this.x3 = x3;
    }

    public int getAge() {
        return age;
    }

    public Leaf getX1() {
        return x1;
    }

    public Leaf getX2() {
        return x2;
    }

    public Leaf getX3() {
        return x3;
    }

}
