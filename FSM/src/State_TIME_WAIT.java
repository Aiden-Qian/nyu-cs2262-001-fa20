import Fsm.State;

public class State_TIME_WAIT extends State {
	
	private String state_name = "TIME_WAIT";
	
	public State_TIME_WAIT(String name) {
		super(name);
	}

	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
