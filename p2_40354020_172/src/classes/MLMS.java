package classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import classes.Customer;;
/**
 * Second Policy - Under this policy, each service post has its own waiting line (one line per server). 
 * Once a person enters a waiting line, that person cannot transfer to another line, even if one becomes empty. 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class MLMS extends Job{

	private int servers;
	Map<Integer,Customer>serversMap= new TreeMap<>();
	Map<Integer,ArrayDeque<Customer>> filas =  new TreeMap<>();
	List<Customer> orderofattended = new ArrayList<>();

	private int atendidos=0;
/**
 * The following constructor determines how many servers will be available
 * @param servers: servers available 
 */
	public MLMS(int servers ) {
		this.servers = servers;

	}
	
	/**
	 * The following method executes the algorithm described at the beginning of the class
	 * @param customers: the amount of clients awaiting service
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
			filas.put(i, new ArrayDeque<Customer>());		
		}
		
		while(atendidos != customers.size())//nos dice cuando atendimos a todos
		{
			//ARRIVAL EVENT// #4
			if (!input.isEmpty() && input.peek().getMomentofArrival() == Time()) {
				int temp = input.peek().getMomentofArrival();

				filas.get(shortestLineIndex()).add(input.remove());
				
				if (!input.isEmpty()) {
					while (temp == input.peek().getMomentofArrival()) {
						filas.get(shortestLineIndex()).add(input.remove());
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
 * This method checks which of the lines has the fewest customers
 * @return index: line with fewest customers
 */
	private  int shortestLineIndex() {
		
		int index =0;//fila 0 es el que menos personas tienes
		if(filas.size()>1){
			for(int i = 1; i<filas.size();i++) {
			if(filas.get(index).size()>filas.get(i).size())//0 y 1
				index = i;
			}
			return index;
		}
		else{
			return 0;
		}
		
	}
	/**
	 * This method checks whether or not the lines are empty
	 * @return true if all lines do not contain any clients
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
}
