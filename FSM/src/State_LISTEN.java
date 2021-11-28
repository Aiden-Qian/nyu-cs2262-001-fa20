import Fsm.State;

public class State_LISTEN extends State {
	
	private String state_name = "LISTEN";
	
	public State_LISTEN(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}

}
