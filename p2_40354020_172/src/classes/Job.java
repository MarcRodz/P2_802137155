package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class Job {

	private int Time;
	private int NoCOverpasing = 0;	
	private int TotalWaitTime = 0;
	private double AvergaeNoCOverpassing = 0;
	private double AvergaeWaitTime = 0;
	public Job() {
		this.Time = 0;
		this.NoCOverpasing = 0;
		this.TotalWaitTime = 0;
		this.AvergaeWaitTime = 0;
		this.AvergaeNoCOverpassing = 0;
	}

	/*
	 * @return Integer representing the current time
	 */
	public int Time() {
		return this.Time;
	}

	/**
	 * Adds one unit of time to Time counter
	 */
	public void addTime() {
		this.Time++;
	}
	public void FixTime() {
		this.Time--;
	}
	public void FixTime3() {
		this.Time = this.Time - 2;
	}
	/*
	 * @return Integer representing the number of customers overpassing per customer of all the customers.
	 */
	public int NoCOverpasing() {
		return this.NoCOverpasing;
	}
	/**
	 * Adds one customers overpassing per customer to the counter
	 */
	public void addNoCOverpasing() {
		this.NoCOverpasing++;
	}
	
	public void getNoCOverpasing(List<Customer> a) {
		for(int i = 0; i<a.size();i++){
			for(int j = i+1; j<a.size();j++){
				if(a.get(i).getMomentofArrival() > a.get(j).getMomentofArrival()){
					this.NoCOverpasing++;
				}
			}
		}
	}
	
	/*
	 * @return Double representing the Average number of customers overpassing per customer 
	 */
	public double AvergaeNoCOverpassing(){
		return this.AvergaeNoCOverpassing;
	}
	/*
	 * Gets the averageNoCOverpasing by dividing NoCOverpasing by NumOfCus.
	 */
	public void AvergaeNoCOverpassing(int NoCOverpasing, int NumOfCus){
		this.AvergaeNoCOverpassing = ((double) NoCOverpasing)/((double)NumOfCus);
	}
	

	/**
	 * 
	 * @return Double representing the total income for a particular job
	 *         approach
	 */
	public double TotalWaitTime() {
		return this.TotalWaitTime;
	}

	/**
	 * 
	 * @paramTotalWaitTime
	 *            double representing the total wait time of all the customers
	 *            Total wait time
	 */
	public void addTotalWaitTime(double TotalWaitTime) {
		this.TotalWaitTime += TotalWaitTime;
	}
	/*
	 * Gets the AvergaeWaitTime by dividing AvergaeWaitTime by NumOfCus.
	 */
	public void AvergaeWaitTime(double TotalWaitTime, int NumOfCus){
		this.AvergaeWaitTime = ((double)TotalWaitTime)/((double)NumOfCus);
	}
	/*
	 * @return Double representing the Average wait time of customers.
	 */
	public double AvergaeWaitTime(){
		return this.AvergaeWaitTime;
	}

}
