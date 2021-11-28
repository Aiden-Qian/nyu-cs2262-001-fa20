
import Fsm.FSM;
import Fsm.FsmException;
import Fsm.Event;
import Fsm.Transition;
import Fsm.Action;
import java.util.Scanner;

public class FSM_my{
		private static class InputParser {
		Scanner input;
		String[] commands;

		InputParser(){
			input = new Scanner(System.in);
			commands = new String[1];
		}

		public Event getEventFromString(String input) {		
			if(input.equals("ACTIVE")) {
				return new Event_ACTIVE(input);
			}
			else if (input.equals("ACK")) {
				return new Event_ACK(input);
			}
			else if (input.equals("CLOSE")) {
				return new Event_CLOSE(input);
			}
			else if (input.equals("FIN")) {
				return new Event_FIN(input);
			}
			else if (input.equals("PASSIVE")) {
				return new Event_PASSIVE(input);
			}
			else if (input.equals("RDATA")) {
				return new Event_RDATA(input);
			}
			else if (input.equals("SDATA")) {
				return new Event_SDATA(input);
			}
			else if (input.equals("SYN")) {
				return new Event_SYN(input);
			}
			else if (input.equals("SYNACK")) {
				return new Event_SYNACK(input);
			}
			else if (input.equals("TIMEOUT")) {
				return new Event_TIMEOUT(input);
			}
			else if (input.equals("SEND")) {
				return new Event_SEND(input);
			}
			System.out.println("Error");
			return new Event_ERROR("ERROR");
		}
	}

	public static void main(String[] args) throws FsmException {
		int sdata_count = 0;
		int rdata_count = 0;
		Event_ACK event_ack = new Event_ACK("ACK");
		Event_ACTIVE event_active = new Event_ACTIVE("ACTIVE");
		Event_CLOSE event_close = new Event_CLOSE("CLOSE");
		Event_FIN event_fin = new Event_FIN("FIN");
		Event_PASSIVE event_passive = new Event_PASSIVE("PASSIVE");
		Event_RDATA event_rdata = new Event_RDATA("RDATA");
		Event_SDATA event_sdata = new Event_SDATA("SDATA");
		Event_SYN event_syn = new Event_SYN("SYN");
		Event_SYNACK event_synack = new Event_SYNACK("SYNACK");
		Event_TIMEOUT event_timeout = new Event_TIMEOUT("TIMEOUT");
		Event_SEND event_send=new Event_SEND("SNED");

		State_CLOSED closed = new State_CLOSED("CLOSED");
		State_CLOSING closing = new State_CLOSING("CLOSING");
		State_CLOSE_WAIT close_wait = new State_CLOSE_WAIT("CLOSE_WAIT"); 
		State_ESTABLISHED established = new State_ESTABLISHED("ESTABLISHED");
		State_FIN_WAIT_1 fin_wait_1 = new State_FIN_WAIT_1("FIN_WAIT_1");
		State_FIN_WAIT_2 fin_wait_2 = new State_FIN_WAIT_2("FIN_WAIT_2");
		State_LAST_ACK last_ack = new State_LAST_ACK("LAST_ACK");
		State_LISTEN listen = new State_LISTEN("LISTEN");
		State_SYN_SENT syn_sent = new State_SYN_SENT("SYN_SENT");
		State_SYN_RCVD syn_rcvd = new State_SYN_RCVD("SYN_RCVD");
		State_TIME_WAIT time_wait = new State_TIME_WAIT("TIME_WAIT");
		
		FSM fsm = new FSM("ActiveClient", closed);
		
		InputParser parser = new InputParser();
		
		Transition t1 = new Transition(closed, event_passive, listen, new Action_no_action());
		Transition t2 = new Transition(listen, event_close, closed, new Action_no_action());
		Transition t3 = new Transition(closed, event_active, syn_sent, new Action_syn());
		Transition t4 = new Transition(syn_sent, event_close, closed, new Action_no_action());
		Transition t5 = new Transition(listen, event_send , syn_sent, new Action_syn());
		Transition t6 = new Transition(listen, event_syn, syn_rcvd, new Action_syn_ack());
		Transition t7 = new Transition(syn_sent, event_syn, syn_rcvd, new Action_syn_ack());
		Transition t8 = new Transition(syn_rcvd, event_ack, new State_ESTABLISHED("ESTABLISHED"), new Action_no_action());
		Transition t9 = new Transition(syn_sent, event_synack, new State_ESTABLISHED("ESTABLISHED"), new Action_ack());
		Transition t10 = new Transition(syn_rcvd, event_close, fin_wait_1, new Action_fin());
		Transition t11 = new Transition(established, event_close, fin_wait_1, new Action_fin());
		Transition t12 = new Transition(established, event_rdata, established, new Action_n());
		Transition t13 = new Transition(established, event_sdata, established, new Action_n());
		Transition t14 = new Transition(established, event_fin, close_wait, new Action_ack());
		Transition t15 = new Transition(fin_wait_1, event_ack, fin_wait_2, new Action_no_action());
		Transition t16 = new Transition(fin_wait_1, event_fin, closing, new Action_ack());
		Transition t17 = new Transition(close_wait, event_close, last_ack, new Action_fin());
		Transition t18 = new Transition(fin_wait_2, event_fin, time_wait, new Action_ack());
		Transition t19 = new Transition(closing, event_ack, time_wait, new Action_no_action());
		Transition t20 = new Transition(last_ack, event_ack, closed, new Action_no_action());
		Transition t21 = new Transition(time_wait, event_timeout, closed, new Action_no_action());

		fsm.addTransition(t1);
		fsm.addTransition(t2);
		fsm.addTransition(t3);
		fsm.addTransition(t4);
		fsm.addTransition(t5);
		fsm.addTransition(t6);
		fsm.addTransition(t7);
		fsm.addTransition(t8);
		fsm.addTransition(t9);
		fsm.addTransition(t10);
		fsm.addTransition(t11);
		fsm.addTransition(t12);
		fsm.addTransition(t13);
		fsm.addTransition(t14);
		fsm.addTransition(t15);
		fsm.addTransition(t16);
		fsm.addTransition(t17);
		fsm.addTransition(t18);
		fsm.addTransition(t19);
		fsm.addTransition(t20);
		fsm.addTransition(t21);

		System.out.printf("Enter a command:");
		Scanner input = new Scanner(System.in);
		String command = input.next();

		while(command != null) {
			System.out.printf("Current state:  %s\n", fsm.currentState());
			String last_state = fsm.currentState().getName();
			try {			
				fsm.doEvent(parser.getEventFromString(command));
				if (last_state != "ESTABLISHED" && fsm.currentState().getName().equals("ESTABLISHED")) {
					rdata_count = 0;
					sdata_count = 0;
				}else if (last_state.equals("ESTABLISHED") && command.equals("RDATA")) {
					rdata_count += 1;
					System.out.printf("Recieved data: %d\n", rdata_count);
				}else if (last_state.equals("ESTABLISHED") && command.equals("SDATA")) {
					sdata_count += 1;
					System.out.printf("Sent data: %d\n", sdata_count);
				}
			}catch(FsmException e) {
					System.out.printf("%s\n", e.toString());
			}
				System.out.printf("\nNext command:");
				command = input.next();			
		}
			input.close();
		}
	

}
