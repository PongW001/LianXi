import java.util.*;

public class LianXi{
	public static void main(String[] args){
		Resource r = new Resource();
		
		Producer p = new Producer(r);
		Producer p1 = new Producer(r);
		Consumer c = new Consumer(r);
		Consumer c1 = new Consumer(r);
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(p1);
		Thread t3 = new Thread(c);
		Thread t4 = new Thread(c1);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}
 
class Resource{
	private String name;
	private int id = 1;
	private boolean flag = false;
	
	public synchronized void set(String name){
		while(flag){
			try{
				this.wait();
			}catch(Exception e){}
		}
		this.name = name+"..."+id++;
		System.out.println(Thread.currentThread().getName()+"  Producer..."+this.name);
		flag = true;
		this.notifyAll();
	}
	
	public synchronized void out(){
		while(!flag){
			try{
				this.wait();
			}catch(Exception e){}
		}
		System.out.println(Thread.currentThread().getName()+"  Consumer......"+this.name);
		flag = false;
		this.notifyAll();
	}
}

class Producer implements Runnable{
	private Resource res;
	Producer(Resource res){
		this.res = res;
	}
	public void run(){
		while(true){
			res.set("GOODS");
		}
		
	}
}

class Consumer implements Runnable{
	private Resource res;
	Consumer(Resource res){
		this.res = res;
	}
	public void run(){
		while(true){
			res.out();
		}
		
	}
}
