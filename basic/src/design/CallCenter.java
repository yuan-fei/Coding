package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Employee: Operator, Supervisor, Director
 * 
 * An Employee can:
 * 
 * 1. answer call
 * 
 * 2. process call
 * 
 * 3. escalate call to next level when he can't handle it
 * 
 * 4. complete call
 * 
 * A call center can
 * 
 * 1. receive call
 * 
 * 2. process call: pick available employee or queue the call when employees are
 * all fully occupied
 * 
 * 3. process queued call when available
 * 
 * 
 * Follow up: use observer pattern to decouple CallCenter and complete call
 * event of Employee
 */
public class CallCenter {
	public static CallCenter Instance = new CallCenter();
	Map<EmployeeLevel, List<Employee>> employees = new HashMap<>();
	Queue<Call> queue = new LinkedList<Call>();

	private CallCenter() {
	}

	public void setEmployees(List<Employee> operators, List<Employee> supervisors, List<Employee> directors) {
		employees.put(EmployeeLevel.OPERATOR, operators);
		employees.put(EmployeeLevel.SUPERVISOR, supervisors);
		employees.put(EmployeeLevel.DIRECTOR, directors);
	}

	public void answerCall(Call call) {
		queue.offer(call);
		processQueuedCall();
	}

	public void processQueuedCall() {
		if (!queue.isEmpty()) {
			Call call = queue.poll();
			for (Employee e : employees.get(call.level)) {
				if (e.call == null) {
					e.answerCall(call);
					return;
				}
			}
			queue.offer(call);
		}
	}
}

class Call {
	EmployeeLevel level;
}

enum EmployeeLevel {
	OPERATOR, SUPERVISOR, DIRECTOR
}

abstract class Employee {
	static EmployeeLevel level;
	Call call;

	public void answerCall(Call call) {
		this.call = call;
		doAnswer(call);
	}

	protected void doAnswer(Call call) {

	}

	abstract EmployeeLevel escalateLevel(EmployeeLevel level) throws Exception;

	public void escalateCall() throws Exception {
		call.level = escalateLevel(call.level);
		Call escalatedCall = call;
		call = null;
		CallCenter.Instance.answerCall(escalatedCall);
	}

	public void completeAnswer() {
		call = null;
		CallCenter.Instance.processQueuedCall();
	}
}

class Operator extends Employee {

	@Override
	public EmployeeLevel escalateLevel(EmployeeLevel level) {
		return EmployeeLevel.SUPERVISOR;
	}
}

class Supervisor extends Employee {

	@Override
	public EmployeeLevel escalateLevel(EmployeeLevel level) {
		return EmployeeLevel.DIRECTOR;
	}
}

class Director extends Employee {

	@Override
	public EmployeeLevel escalateLevel(EmployeeLevel level) throws Exception {
		throw new Exception("Not supported");
	}
}
