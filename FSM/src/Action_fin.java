import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_fin extends Action {

	public Action_fin() {}

	@Override
	public void execute(FSM arg0, Event arg1) {
	  	System.out.println("Next state is  " + arg0.currentState() + " ! Event "+arg1.getName() +" successed!");
	}
}
