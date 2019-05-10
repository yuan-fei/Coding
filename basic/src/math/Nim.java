package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given n heaps of stones, each time a player takes any positive number of
 * stones from 1 heap
 */
public class Nim {

	public static void main(String[] args) {
		SpragueGrundySolution sg = new SpragueGrundySolution(Arrays.asList(3, 4, 5));
		System.out.println(sg.getNimber());
		System.out.println(sg.isFirstPlayerWin());
		System.out.println(isFirstPlayerWin(Arrays.asList(3, 4, 5)));
	}

	static boolean isFirstPlayerWin(List<Integer> stones) {
		int r = 0;
		for (int i : stones) {
			r ^= getNimber(i);
		}
		return getNimber(r) != 0;
	}

	private static int getNimber(int i) {
		return i;
	}

	static class SpragueGrundySolution {
		AbstractState s;

		public SpragueGrundySolution(List<Integer> stones) {
			List<AbstractState> states = new ArrayList<>();
			for (int i : stones) {
				states.add(new State(i));
			}
			s = new CombinedState(states);
		}

		public boolean isFirstPlayerWin() {
			return s.getNimber() != 0;
		}

		public int getNimber() {
			return s.getNimber();
		}

		abstract class AbstractState {
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

		final Map<State, Integer> cache = new HashMap<>();

		class State extends AbstractState {

			int stones;

			public State(int stones) {
				this.stones = stones;
			}

			List<AbstractState> getNextStates() {
				List<AbstractState> r = new ArrayList<>();
				for (int i = 0; i < stones; i++) {
					r.add(new State(i));
				}
				return r;
			}

			boolean isZero() {
				return stones == 0;
			}

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

			int mex(Set<Integer> nimbers) {
				for (int i = 0; i < nimbers.size(); i++) {
					if (!nimbers.contains(i)) {
						return i;
					}
				}
				return nimbers.size();
			}

			@Override
			public boolean equals(Object obj) {
				State that = (State) obj;
				return stones == that.stones;
			}

			@Override
			public int hashCode() {
				return stones;
			}
		}

		class CombinedState extends AbstractState {
			public CombinedState(List<AbstractState> stones) {
				subStates = stones;
			}

			List<AbstractState> subStates;

			int getNimber() {
				int nimber = 0;
				for (AbstractState ns : subStates) {
					nimber ^= ns.getNimber();
				}
				return nimber;
			}
		}
	}

}
