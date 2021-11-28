import Fsm.State;

public class State_CLOSED extends State {

	private String state_name = "CLOSED"; 

	public State_CLOSED(String name) {		
		super(name);
	}
	
	public String getName() {
		return this.state_name;
	}
	
	public String toString() {
		return new String("State (" + this.state_name + ")");
	}
}