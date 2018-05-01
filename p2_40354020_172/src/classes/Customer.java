package classes;

/**
 * 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class Customer implements Comparable{

	private int MomentofArrival;
	private int ServiceTime;
	private int Server;
	private int customerS;  // remaining service time for this job
	public Customer(int MomentofArrival, int ServiceTime) {
		this.MomentofArrival = MomentofArrival;
		this.ServiceTime = ServiceTime;
	}

	/**
	 * Retrieves the moment of arrival for the given Customer instance.
	 * 
	 * @return int representing the moment the customer arrives.
	 * 
	 */
	public int getMomentofArrival() {
		return MomentofArrival;
	}

	/**
	 * Sets the moment of arrival of a given Customer instance.
	 * 
	 * @param MomentofArrival
	 *            int denoting arrival of customer in turns.
	 */
	public void setMomentofArrival(int MomentofArrival) {
		this.MomentofArrival = MomentofArrival;
	}

	/**
	 * Retrieves the ServiceTime of the given Customer instance.
	 * 
	 * @return int representing the ServiceTime of the customer.
	 */
	public int getServiceTime() {
		return ServiceTime;
	}

	public int getCurrentCustomerS() {// valor a comparar con T+S
		return customerS;
	}
	/**
	 * Sets the ServiceTime integer to the one provided.
	 * 
	 * @param ServiceTime
	 *            int denoting the ServiceTime.of the customer
	 */
	public void setServiceTime(int ServiceTime) {
		this.ServiceTime = ServiceTime;
	}
	/**
	 * Retrieves the Server of the given Customer instance.
	 * 
	 * @return int representing the Server of the customer.
	 */
	public int getServer() {
		return Server;
	}

	/**
	 * Sets the Server integer to the one provided.
	 * 
	 * @param Server
	 *            int denoting the Server of the customer
	 */
	public void setSever(int Server) {
		this.Server = Server;
	}
	/**
	 * Organizes a list of customers based on their moment of arrival.
	 * 
	 * @param compareCus
	 *            object denoting the customer
	 */
	@Override
	public int compareTo(Object compareCus) {
        int compareMomentofArrival=((Customer)compareCus).getMomentofArrival();
        return this.MomentofArrival-compareMomentofArrival;

    }
	
	/**
	 * Converts the Customer object to a String representation.
	 * 
	 * @return String representing the Customer and all of his/her fields
	 */
	@Override
	public String toString() {
		return "Customer [MomentofArrival=" + MomentofArrival + ", ServiceTime=" + ServiceTime + "]";
	}


}
