import Fsm.State;

public class State_CLOSING extends State {

	private String state_name = "CLOSING";
	
	public State_CLOSING(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}

}
