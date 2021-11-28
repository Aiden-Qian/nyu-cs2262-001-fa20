import Fsm.State;

public class State_LAST_ACK extends State {

	private String state_name = "LAST_ACK";
	
	public State_LAST_ACK(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}

}
