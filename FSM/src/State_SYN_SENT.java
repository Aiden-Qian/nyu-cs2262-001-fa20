import Fsm.State;

public class State_SYN_SENT extends State {
	
	private String state_name = "SYN_SENT";
			
	public State_SYN_SENT(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
