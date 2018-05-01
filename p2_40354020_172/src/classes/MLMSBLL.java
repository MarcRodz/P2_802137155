package classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import classes.Customer;;
/**
 * Third policy -This case is similar to the previous policy but a customer 
 * is not stuck to the line he/she initially chooses as in that one. 
 * This scheme includes a monitor which will balance the line lengths 
 * Always making sure that that all lines have equivalent amount of customers
 * 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class MLMSBLL extends Job {
	
		private int servers;
		Map<Integer,Customer>serversMap= new TreeMap<>();
		Map<Integer,ArrayDeque<Customer>> filas =  new TreeMap<>();
		List<Customer> orderofattended = new ArrayList<>();
		Map<Integer, Integer> Monitor = new TreeMap<>();

		private int atendidos=0;
		/**
		 * This constructor determines how many servers are currently working
		 * @param servers
		 */
		public MLMSBLL(int servers) {
			this.servers = servers;
		}
		/**
		 * This method executes the algorithm described at the beginning of the class
		 * @param customers this element of type list contains the customers that are in line awaiting for service
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
				//ARRIVAL EVENT// #4
				if (!input.isEmpty() && input.peek().getMomentofArrival() == Time()) {
					int temp = input.peek().getMomentofArrival();

					filas.get(shortestLineIndex()).add(input.remove());
					
					monitorFilas();
					if (!input.isEmpty()) {
						while (temp == input.peek().getMomentofArrival()) {
							
							filas.get(shortestLineIndex()).add(input.remove());
							monitorFilas();
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
							addTotalWaitTime(Time() - serversMap.get(s).getMomentofArrival());
							monitorFilas();
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
		 * This method analyzes which of the lines is the one with fewest customers
		 * @return index: this is the index of the line with fewest customers
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
		 * This method analyzes which of the lines contains the most customers
		 * @return index: line with the most customers
		 */
		private  int longestLineIndex() {

			int index =0;//fila 0 es el que menos personas tienes
			for(int i = 1; i<filas.size();i++) {
				if(filas.get(index).size()<filas.get(i).size())//0 y 1
					index = i;
			}
			return index;
		}
		/**
		 * This method checks if all the lines are empty
		 * @return true if all lines are empty
		 */
		private boolean allLinesEmpty() {
		
			for(int i=0; i<filas.size(); i++) {
				if(!filas.get(i).isEmpty())
					return false;
			}
			return true;
			
		}
		/**
		 * The following method that cleans the given server (the one who finished a client)
		 * @param server: server to be cleaned
		 */
		private void cleanServer(int server) {
			serversMap.put(server,null);
		}
		/**
		 * The following method was designed to monitor the lines 
		 * with the purpose of balancing the line lengths  
		 */
		private void monitorFilas() {
			int filaCorta = shortestLineIndex();
			int filaLarga= longestLineIndex();
			if(filas.get(filaLarga).size()>filas.get(filaCorta).size()) {
				filas.get(filaCorta).push(filas.get(filaLarga).remove());
			}

		}
	}

