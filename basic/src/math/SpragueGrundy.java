package math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Check if first player in an impartial game wins.
 * 
 * See Nim for usage
 */
public class SpragueGrundy {
	final Map<State, Integer> cache = new HashMap<>();

	AbstractState s;

	public boolean isFirstPlayerWin() {
		return s.getNimber() != 0;
	}

	public int getNimber() {
		return s.getNimber();
	}

	static abstract class AbstractState {
		abstract int getNimber();

		int mex(Set<Integer> nimbers) {
			for (int i = 0; i < nimbers.size(); i++) {
				if (!nimbers.contains(i)) {
					return i;
				}
			}
			return nimbers.size();
		}

		public boolean isFirstPlayerWin() {
			return getNimber() != 0;
		}
	}

	abstract class State extends AbstractState {

		abstract List<AbstractState> getNextStates();

		abstract boolean isZero();

		int getNimber() {
			if (isZero()) {
				return 0;
			}
			if (cache.containsKey(this)) {
				return cache.get(this);
			}
			Set<Integer> nimbers = new HashSet<Integer>();
			for (AbstractState ns : getNextStates()) {
				nimbers.add(ns.getNimber());
			}
			cache.put(this, mex(nimbers));
			return cache.get(this);
		}

		@Override
		public abstract boolean equals(Object obj);

		@Override
		public abstract int hashCode();
	}

	class CombinedState extends AbstractState {
		public CombinedState(List<State> states) {
			subStates = states;
		}

		List<State> subStates;

		int getNimber() {
			int nimber = 0;
			for (State ns : subStates) {
				nimber ^= ns.getNimber();
			}
			return nimber;
		}

		int getCombinedNimber() {
			int nimber = 0;
			for (State ns : subStates) {
				nimber ^= ns.getNimber();
			}
			return nimber;
		}
	}
}
