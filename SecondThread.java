
public class SecondThread implements Runnable{
	int waitTime;
	int counter = 0;
	
	SecondThread(int time)
	{
		waitTime = time;
	}
	public void run()
	{
		try
		{
			for(int i = 0; i < 8; i++)
			{
				double x = Math.random();
				System.out.println("Second Current Thread " + Thread.currentThread().getId() + " is Running. Times run " + counter);
				counter++;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("whoops");
		}
	}
}
