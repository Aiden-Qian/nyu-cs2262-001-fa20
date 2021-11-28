import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_n extends Action {

	public Action_n() {}

	@Override
	public void execute(FSM arg0, Event arg1) {
		if ((arg1.getName()).equals("RDATA")){
			System.out.println("Next state is  " + arg0.currentState() + " ! Data received "+arg1.getName() +" successed!");
		}
		else if ((arg1.getName()).equals("SDATA")){
			System.out.println("Next state is  " + arg0.currentState() + " ! Data sent "+arg1.getName() +" successed!");
		}
	}

}
