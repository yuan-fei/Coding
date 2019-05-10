package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given n heaps of stones, players take any positive number up to k of stones
 * from any heap
 */
public class NimWithLimit {

	public static void main(String[] args) {
		SpragueGrundySolution sg = new SpragueGrundySolution(3, Arrays.asList(5, 6, 7));
		System.out.println(sg.getNimber());
		System.out.println(sg.isFirstPlayerWin());
		System.out.println(isFirstPlayerWin(3, Arrays.asList(5, 6, 7)));
	}

	static boolean isFirstPlayerWin(int k, List<Integer> stones) {
		int r = 0;
		for (int i : stones) {
			r ^= getNimber(i, k);
		}
		return r != 0;
	}

	static int getNimber(int n, int k) {
		return n % (k + 1);
	}

	static class SpragueGrundySolution {
		AbstractState s;
		int k;

		public SpragueGrundySolution(int k, List<Integer> stones) {
			this.k = k;
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
				for (int i = 1; i <= Math.min(stones, k); i++) {
					r.add(new State(stones - i));
				}
				return r;
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

			private boolean isZero() {
				// TODO Auto-generated method stub
				return stones == 0;
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
			public CombinedState(List<AbstractState> states) {
				subStates = states;
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
