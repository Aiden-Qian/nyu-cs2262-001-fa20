import Fsm.State;

public class State_ESTABLISHED extends State {

	private String state_name = "ESTABLISHED";
	
	public State_ESTABLISHED(String name) {
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}

}
