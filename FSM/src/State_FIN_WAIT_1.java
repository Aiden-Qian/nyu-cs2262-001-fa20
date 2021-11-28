import Fsm.State;

public class State_FIN_WAIT_1 extends State {

	private String state_name = "FIN_WAIT_1";
	
	public State_FIN_WAIT_1(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}
