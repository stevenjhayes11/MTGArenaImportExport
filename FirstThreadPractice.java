

public class FirstThreadPractice {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		
		for(int i = 0; i < 8; i++)
		{
			System.out.println("\n\nObjects started time: " + i);
			Thread testObj = new Thread(new ThreadingObject(0));
			testObj.start();		
			Thread secondTest = new Thread(new SecondThread(0));
			secondTest.start();
			
			//more elegant way to make sure threads finish before loop
			//does its loop thang
			//testObj.join();
			//secondTest.join();
			
			//other way to ensure loop doesn't execute til both threads
			//are done
			while(testObj.isAlive() || secondTest.isAlive())
			{
				Thread.sleep(1);
				if(testObj.isAlive() || secondTest.isAlive())
					System.out.println("waiting");
			}
			
		}
	}

}
