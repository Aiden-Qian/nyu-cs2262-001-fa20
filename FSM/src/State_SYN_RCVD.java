import Fsm.State;

public class State_SYN_RCVD extends State {
	
	private String state_name = "SYN_RCVD";
	
	public State_SYN_RCVD(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
