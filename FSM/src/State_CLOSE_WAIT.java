import Fsm.State;

public class State_CLOSE_WAIT extends State {

	private String state_name = "CLOSE_WAIT";
	
	public State_CLOSE_WAIT(String name) {
		super(name);
	}

	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
