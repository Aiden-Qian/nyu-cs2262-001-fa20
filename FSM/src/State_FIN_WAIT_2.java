import Fsm.State;

public class State_FIN_WAIT_2 extends State {
	
	private String state_name = "FIN_WAIT_2";
	
	public State_FIN_WAIT_2(String name) {
		super(name);
	}

	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
