package search.api;



class ex{
	
	
	public void test() {
		
		try {
			
			throw new Exception();
		}
		catch(Exception ex) {
			
		}
	}
}

public class th {
	private static int[] buffer;
	private static int count;
	private static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		buffer = new int[10];
		count = 0;
		Runnable r1 = () -> {
			for (int i = 0 ; i < 50 ; i++) {
				Producer.produce();
			}
			System.out.println("Done producing");
		};
		Runnable r2 = () -> {
			for (int i = 0 ; i < 45 ; i++) {
				Consumer.consume();
			}
			System.out.println("Done consuming");
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Data in the buffer: " + count);
	}
	static boolean isEmpty(int[] buffer) {
		return count == 0;
	}

	static boolean isFull(int[] buffer) {
		return count == buffer.length;
	}

	static class Producer {

		static void produce() {
			synchronized (lock) {
				if (isFull(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				lock.notify();
			}
		}
	}


	static class Consumer {
		static void consume() {
			synchronized (lock) {
				if (isEmpty(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[--count] = 0;
				lock.notify();
			}
		}
	}


}

class MyThread extends Thread {
	
	
}



// Singleton
class Singleton {

	private static Singleton instance;
	private static final Object obj = new Object();

	private Singleton() {
		System.out.println("This is a constructor");
	}

	public static Singleton getObject() {
		synchronized (obj) {
			if (instance == null) {
				instance = new Singleton();
			}
		}
		return instance;
	}

}

// Strategy
interface Payment{
	public boolean pay();
}

class CreditCartPayment implements Payment{
	public boolean pay() {
		return true;
	}
}

class DebitCardPayment implements Payment{
	public boolean pay() {
		return false;
	}
}

//Chain of Repository
interface Approver{
//	Approver successor;
//	public boolean handleRequest() ;
	
}

class Director implements Approver{
//	private 
	
	
}



