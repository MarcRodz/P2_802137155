package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import classes.LLQueue;

/**
 * First Policy - Under this policy, there is only one waiting line and one or more service posts. 
 * Similar to that of the popular store Walgreens
 * Whenever a post is available, the first person in line, if any, will start to be served by the service person at the post. 
 * In the case in which there are more than one server available at a moment, 
 * then the first person in line will go to the available post having  min index value among those available. 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class SLMS extends Job {

	// A queue to hold the customers before being put into the processing queue.
	LLQueue<Customer> inputQueue = new LLQueue<Customer>();
	// A queue that will receive the customers that arrive.
	LLQueue<Customer> processingQueue = new LLQueue<Customer>();
	// List that holds customers being served customer.
	List<Customer> servingList = new ArrayList<>();
	// List of available servers
	List<Integer> Servers = new ArrayList<>();
/**
 * The following method/constructor executes the algorithm described at the beginning of this class is executed
 * @param customers: the clients that are awaiting service
 * @param servers: quantity of servers that will be available
 */
	public SLMS(List<Customer> customers, int servers) 
	{
		for(int i=0; i < servers; i++){
			Servers.add(i);
		}
		//This is used to organize the contents of the customers list to have a set order for arrivals
		Collections.sort(customers);
		// This for is used to get the customers from the"customers" list into
		// an input Queue
		for (Customer c : customers) {
			inputQueue.enqueue(c);
		}
		
		while (!inputQueue.isEmpty() || !processingQueue.isEmpty()||!servingList.isEmpty())
		{

			if (!processingQueue.isEmpty()&&!Servers.isEmpty())
			{
				while(!Servers.isEmpty()&&!processingQueue.isEmpty()){
					Customer temp = processingQueue.dequeue();
					addTotalWaitTime(Time() -1 - temp.getMomentofArrival());
					temp.setSever(Servers.remove(0));
					Collections.sort(Servers);
					servingList.add(temp);
					List<Customer> Check = new ArrayList<>();
					while(!processingQueue.isEmpty()){
						Check.add(processingQueue.dequeue());
					}
					for(Customer b: Check){
						if(Time() - temp.getMomentofArrival() < Time() - b.getMomentofArrival()){
							addNoCOverpasing();
						}
						processingQueue.enqueue(b);
					}
					Check.clear();
				}
			}

			List<Customer> Remove = new ArrayList<>();
			if(!servingList.isEmpty()){
				for(Customer a: servingList){
					//System.out.println(a.getMomentofArrival() +"," + a.getServiceTime());
					a.setServiceTime(a.getServiceTime() - 1);
					
					if (a.getServiceTime() == 0) 
					{
						Remove.add(a);
					}
				}
				
			}
			if(!Remove.isEmpty()){
				List<Integer> Temporary = new ArrayList<>();
				for(Customer a: Remove){
					for(int i=0; i<servingList.size();i++){
						if(servingList.get(i).equals(a)){
							Servers.add(servingList.get(i).getServer());
							Temporary.add(i);
							Collections.sort(Servers);
						}
					}
				}
				for(int i=Temporary.size()-1;i>=0 ;i--){
					servingList.remove((int) Temporary.get(i));
				}
				Remove.clear();
			}
			// This if is used to place customers from the inputQueue to the
			// processingQueue
			// They're placed in the Queue when their moment of arrival is equal
			// to Time
			if (!inputQueue.isEmpty() && inputQueue.front().getMomentofArrival() == Time()) {
				int temp = inputQueue.front().getMomentofArrival();
				
				processingQueue.enqueue(inputQueue.dequeue());
				
				if (!inputQueue.isEmpty()) {
					while (temp == inputQueue.front().getMomentofArrival()) {
						processingQueue.enqueue(inputQueue.dequeue());
						if(inputQueue.isEmpty()){
							break;
						}
					}
				}
			}
			addTime();
		}
		FixTime();
		AvergaeNoCOverpassing(NoCOverpasing(),customers.size());
		AvergaeWaitTime(TotalWaitTime(), customers.size());

	}
}
