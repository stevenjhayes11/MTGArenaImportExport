import java.util.Random;
public class ThreadingObject implements Runnable{

	int waitTime;
	int counter = 0;
	ThreadingObject(int time)
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
				System.out.println("Current Thread " + Thread.currentThread().getId() + " is Running. Times run " + counter);
				counter++;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("whoops");
		}
	}
	
}
