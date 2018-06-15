
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * yanglc 2018/06/08
 * 
 * 1、从1到31中任选三个不重复数的进行组合，求所有组合
 * 2、计算组合的平均值，并求出平均值出现的次数，以及出现概率。 例如， 1,2,3， 平均值为 2， 出现1次， 出现概率 = 1 / 所有组合数目。
 * 3、输出 csv格式数据：平均值，出现次数，所占百分比，例如   2 ， 1， %
 *
 * */
public class Combinition {
	
	public static List<List<Integer>> CombinitionList() {
	int max = 31;
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	//List<List> result = new ArrayList<List>();
	for (int i = 1; i <=max; i++)
		for (int j = i + 1; j <= max; j++) {
			if (i == j) {
				continue;
			}
			for (int k = j + 1; k <= max; k++) {
				if (k == i || k == j) continue;
				List<Integer> combine = new ArrayList<Integer>();
				//List combine = new ArrayList();
				combine.add(i);
				combine.add(j);
				combine.add(k);
				result.add(combine);

			}
		}
    return result;
	}  
    public static Map<Float,Integer> listStatics(List<List<Integer>>result) {
    	//int sum=0;
    	Map<Float,Integer> avgMap = new TreeMap<Float,Integer>();
    	for(int i=0;i<result.size();i++){
    	   int sum = (result.get(i).get(0)+result.get(i).get(1)+result.get(i).get(2)) ;
    	   float avg = (float)sum/3;
    		if(avgMap.get(avg)==null){
    			avgMap.put(avg,1);
    		}else{
    			    int cnt = avgMap.get(avg);
    			    avgMap.put(avg,cnt+1);
    		}
    			
    	     }
		return avgMap;
    	}
    public static void  outputCSV (Map<Float,Integer> avgMap){   
    	     try{
    	         String[] heads= {"平均值", "出现次数", "所占百分比"};
    	         //String fileName = "combineCSV.csv";//文件名称
    	        // String filePath = "d:/test/"; //文件路径
    	         File csvFile = new File("d:\\test\\combineCSV.csv");
    	         BufferedWriter out = new BufferedWriter(new  OutputStreamWriter(new FileOutputStream(csvFile),"gbk"));
				for(String head:heads){
    	        	 out.write(head);
    	        	 out.write(",");
    	         }
    	         out.write("\r\n");
    	         float sumTimes = 0; 
    	         for(float times:avgMap.values()) 
    	        	 sumTimes += times; 
    	         for(Float avg:avgMap.keySet()) { 
    	        	 out.write(avg+""); 
    	        	 out.write(","); 
    	         	 out.write(avgMap.get(avg)+""); 
    	         	 out.write(","); 
    	         	 out.write((avgMap.get(avg)/(float)sumTimes)*100+"%"); 
    	         	 out.write("\r\n"); 
    	         	    } 
    	          	    out.flush(); 
    	          	    out.close(); 
    	          	} catch(Exception e){
    	          		e.printStackTrace();
    	          	}
    	 }
    	
    public static void main(String[] args){

		outputCSV(listStatics(CombinitionList()));
    }
}
 
