package classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import classes.Customer;;
/**
 * Fourth policy - This policy is very similar to previous two 
 * This time around the  monitor decides which line the new arriving customer has  to go 
 *  based on expected completion time of the offer of service 
 *  The customer moved  will be assigned to the first line having minimum total waiting time at that moment.
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class MLMSBWT extends Job{

	private int servers;
	Map<Integer,Customer>serversMap= new TreeMap<>();
	Map<Integer,ArrayDeque<Customer>> filas =  new TreeMap<>();
	List<Customer> orderofattended = new ArrayList<>();
	Map<Integer, Integer> Monitor = new TreeMap<>();

	private int atendidos=0;
/**
 * The following constructor sets the amount of servers available at the moment
 * @param servers: amount of servers available
 */
	public MLMSBWT(int servers) {
		this.servers = servers;
	}
	/**
	 * The following method executes the algorithm described at the beginning of the class
	 * @param customers: the amount of clients awaiting for service
	 */
	public  void  evaluate(List<Customer> customers){		

 		ArrayDeque<Customer> input = new ArrayDeque<Customer>();
 		
 		Collections.sort(customers);
 		
		for(Customer a: customers){
			input.add(a);
		}
		//create empty servers
		for(int i=0;i<servers;i++){
			serversMap.put(i, null);
			
			Monitor.put(i,0);
			filas.put(i, new ArrayDeque<Customer>());		
		}
		
		while(atendidos != customers.size())//nos dice cuando atendimos a todos
		{
			MonitorView();
			//ARRIVAL EVENT// #4
			if (!input.isEmpty() && input.peek().getMomentofArrival() == Time()) {
				int temp = input.peek().getMomentofArrival();
				int temp1 = 0;
				
				for(int s=1;s<servers;s++){
					if(Monitor.get(temp1)>Monitor.get(s)){
						temp1 = s;
					}
				}
				
				filas.get(temp1).add(input.remove());
				MonitorView();
				if (!input.isEmpty()) {
					while (temp == input.peek().getMomentofArrival()) {
						temp1 = 0;
						MonitorView();
						for(int s=1;s<servers;s++){
							if(Monitor.get(temp1)>Monitor.get(s)){
								temp1 = s;
							}
						}
						filas.get(temp1).add(input.remove());
						MonitorView();
						if(input.isEmpty()){
							break;
						}
					}
				}
			}
			//el if chequea si faltan eventos por hacer
			if(!allLinesEmpty() ||atendidos!=customers.size())//puede darse el error que pase el tiempo y la fila se vacie
			{//or el map contenga un customer	
				//chequear si hay algun server vacio

				//*SERVICE COMPLETED EVENT// #1
				for(int i=0;i<servers;i++){

					if(serversMap.get(i)!=null){
						serversMap.get(i).setServiceTime(serversMap.get(i).getServiceTime() - 1);
						if(serversMap.get(i).getServiceTime() ==0) {
							
							atendidos++;
							cleanServer(i);
						}
					}
				}
			
				
				for(int s=0;s<servers;s++){
					if(serversMap.get(s) == null && !filas.get(s).isEmpty()){		
						Customer a = filas.get(s).remove();
						serversMap.put(s, a);
						orderofattended.add(a);
						addTotalWaitTime(Time() - a.getMomentofArrival());
					}
				}
				
				
				
			}

			addTime();
		}
		FixTime();
		getNoCOverpasing(orderofattended);
		AvergaeNoCOverpassing(NoCOverpasing(),customers.size());
		AvergaeWaitTime(TotalWaitTime(), customers.size());

	}
	/**
	 * the following method checks whether or not the lines are empty (contain no clients)
	 * @return true if all lines have no clients waiting
	 */
	private boolean allLinesEmpty() {
	
		for(int i=0; i<filas.size(); i++) {
			if(!filas.get(i).isEmpty())
				return false;
		}
		return true;
		
	}
	/**
	 * This method cleans server given, which means it has finished to offer service to client
	 * @param server: server to be cleaned
	 */
	private void cleanServer(int server) {
		serversMap.put(server,null);
	}
	/**
	 * The following method contains the monitor that balances the expected waiting time of the lines
	 * and that also move the clients to the corresponding lines
	 */
	private void MonitorView(){
		for(int j=0;j<servers;j++){
			int temp = 0;
			if(filas.get(j)!=null){
				for(Customer a: filas.get(j)){
					temp = temp + a.getServiceTime();
				}
			}
			if(serversMap.get(j)!=null){
				Monitor.replace(j, serversMap.get(j).getServiceTime() + temp);
			}
			else{
				Monitor.replace(j, temp);
			}

		}
	}

}
