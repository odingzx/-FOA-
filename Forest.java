import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;
public class Forest {
    //初始化一片森林，需要给出树叶的最大以及平均值，树叶的值随机获取
    public ArrayList<Tree> forest = new ArrayList<Tree>();
    public ArrayList<Tree> candidate = new ArrayList<Tree>();
    public ArrayList<Tree> initForest(int size,double max1,double avg1,double max2,double avg2,double avg3,double max3){
        Tree a = new Tree();
        for(int i=0;i<size;++i){
            a = a.initTree(max1,avg1,max2,avg2,max3,avg3);
            forest.add(a);
        }
        return forest;
    }
    public ArrayList<Tree> LocalSeed(int LSC){
        //遍历当前森林，按照每个树的年龄处理
        for(int i =0;i<forest.size();i++){
            int age = forest.get(i).getAge();
            if(age == 0){
                LSCcal(forest.get(i),LSC);
            }
            else{
                forest.get(i).setAge(age+1);
            }
        }
        return forest;
    }
    //对于每个年龄为0的树，产生LSC个新树加入到森林中
    public void LSCcal(Tree a,int LSC){
        for(int i=0;i<LSC;i++){
            //随机选择一个变动的位置
            //获取当前位置的值
            //获取当前位置的平均值
            //随机一个当前位置的变动值
            //改变当前位置的值
            //将新生成的树年龄置为0
            int loc = (int)(Math.random()*2);
            Tree newtree = a;
            newtree.setAge(0);
            double val = 0.0;
            double avg = 0.0;
            double delta = 0.0;
            double val1 = 0.0;
            double avg1 = 0.0;
            double delta1 = 0.0;
            double val2 = 0.0;
            double avg2 = 0.0;
            double delta2 = 0.0;
            switch(loc){
                case 0:
                    val = a.getX1().getVal();
                    avg = a.getX1().getAvg();
                    delta = (double)(Math.random());
                    if(val > avg)
                        newtree.getX1().setVal(val - delta);
                    else
                        newtree.getX1().setVal(val + delta);
                    break;
                case 1:
                    val1 = a.getX2().getVal();
                    avg1 = a.getX2().getAvg();
                    delta1 = (double)(Math.random());
                    if(val1 > avg1)
                        newtree.getX2().setVal(val1 - delta1);
                    else
                        newtree.getX2().setVal(val1 + delta1);
                    break;
                case 2:
                    val2 = a.getX3().getVal();
                    avg2 = a.getX3().getAvg();
                    delta2 = (double)(Math.random());
                    if(val2 > avg2)
                        newtree.getX3().setVal(val2 - delta2);
                    else
                        newtree.getX3().setVal(val2 + delta2);
                    break;
            }
            forest.add(newtree);
        }
        a.setAge(1);
        //老树年龄置为1
    }
    public void areaLimit(int area, int limitage,double target){
        //首先移除年龄大的树到候选队列
        //检查规模是否大于限制
        //如果大于限制，计算适应度，将不适应的移除到候选队列
        for(int i=0;i<forest.size();++i){
            if(forest.get(i).getAge()>=limitage){
                candidate.add(forest.get(i));
                forest.remove(i);
            }
        }
        if(forest.size()>area){
            int num = forest.size()-area;
            ArrayList<Double> evluate = eva(target);
            for(int i=0;i<num;++i){
                int indexOfMax =evluate.indexOf(Collections.max(evluate));
                candidate.add(forest.get(indexOfMax));
                forest.remove(indexOfMax);
            }
        }
    }
    public ArrayList<Double> eva(double target){
        ArrayList<Double> e = new ArrayList<Double>();
        for(int i=0;i<forest.size();++i){
            double x1 = forest.get(i).getX1().getVal();
            double x2 = forest.get(i).getX2().getVal();
            double x3 = forest.get(i).getX3().getVal();
            double result = Math.abs(target - x1 - x2 - x3);
            e.add(result);
        }
        return e;
    }
    public void GlobalSeed(){
        //从候选队列中选择10%的树
        //每一个树选择GSC个变量进行改变（在次随机）,这里GSC选择2
        //每一个树的年龄置为0，放入森林中
        for(int i=0;i<candidate.size()*0.1;i++){
            int index = (int)(Math.random()*candidate.size());
            Tree choose = candidate.get(index);
            choose.setAge(0);
            int nochange = (int)(Math.random()*2);
            switch(nochange){
                case 0:
                    choose.getX2().setVal((double)(Math.random()*choose.getX2().getMax()));
                    choose.getX3().setVal((double)(Math.random()*choose.getX3().getMax()));
                    break;
                case 1:
                    choose.getX3().setVal((double)(Math.random()*choose.getX3().getMax()));
                    choose.getX1().setVal((double)(Math.random()*choose.getX1().getMax()));
                    break;
                case 2:
                    choose.getX2().setVal((double)(Math.random()*choose.getX2().getMax()));
                    choose.getX1().setVal((double)(Math.random()*choose.getX1().getMax()));
            }
            forest.add(choose);
        }
    }
    public void update(double target){
        ArrayList<Double> re = eva(target);
        int indexmin = re.indexOf(Collections.min(re));
        forest.get(indexmin).setAge(0);
    }
}
