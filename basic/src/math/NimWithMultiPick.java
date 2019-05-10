package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given n heaps of stones, each time a player takes any positive number of
 * stones from 1 heap, or all heaps with same amount of stones
 */
public class NimWithMultiPick {

	public static void main(String[] args) {
		SpragueGrundySolution sg = new SpragueGrundySolution(Arrays.asList(6, 6));
		System.out.println(sg.getNimber());
		System.out.println(sg.isFirstPlayerWin());
		System.out.println(sg.combinedStateCache);
	}

	static class SpragueGrundySolution {
		AbstractState s;

		public SpragueGrundySolution(List<Integer> stones) {
			List<State> states = new ArrayList<>();
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
		final Map<CombinedState, Integer> combinedStateCache = new HashMap<>();

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

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "" + stones;
			}
		}

		class CombinedState extends AbstractState {
			public CombinedState(List<State> stones) {
				subStates = stones;
				Collections.sort(stones, Comparator.comparingInt(s -> ((State) s).stones));
			}

			List<State> subStates;

			List<AbstractState> getNextStates() {
				List<AbstractState> r = new ArrayList<>();
				int max = getMaxPick();
				for (int i = 1; i <= max; i++) {
					final int pick = i;
					r.add(new CombinedState(subStates.stream().map(s -> new State(((State) s).stones - pick))
							.collect(Collectors.toList())));
				}
				for (State state : subStates)
					if (state.stones > 0) {
						for (int i = 1; i <= state.stones; i++) {
							final State cur = state;
							final int pick = i;
							CombinedState cs = new CombinedState(
									subStates.stream().map(s -> (s == cur) ? new State(cur.stones - pick) : s)
											.collect(Collectors.toList()));
							r.add(cs);
						}
					}
				return r;
			}

			private int getMaxPick() {
				int min = Integer.MAX_VALUE;
				for (AbstractState s : subStates) {
					min = Math.min(min, ((State) s).stones);
				}
				return min;
			}

			private int getEmptyHeapCnt() {
				return (int) subStates.stream().filter(s -> s.stones > 0).count();
			}

			int getNimber() {
				if (combinedStateCache.containsKey(this)) {
					return combinedStateCache.get(this);
				}
				int nimber = getCombinedNimber();
				if (getEmptyHeapCnt() == 1) {
					return subStates.stream().filter(s -> s.stones > 0).findFirst().get().getNimber();
				}
				if (getMaxPick() > 0) {
					Set<Integer> nimbers = new HashSet<Integer>();
					nimbers.add(nimber);

					List<AbstractState> nextStates = getNextStates();
					for (AbstractState ns : nextStates) {
						nimbers.add(ns.getNimber());
					}
					nimber = mex(nimbers);
				}
				combinedStateCache.put(this, nimber);
				return combinedStateCache.get(this);
			}

			private int getCombinedNimber() {
				int nimber = 0;
				for (AbstractState ns : subStates) {
					nimber ^= ns.getNimber();
				}
				return nimber;
			}

			@Override
			public boolean equals(Object obj) {
				CombinedState that = (CombinedState) obj;
				if (that.subStates.size() != subStates.size()) {
					return false;
				}
				for (int i = 0; i < that.subStates.size(); i++) {
					if (!subStates.get(i).equals(that.subStates.get(i))) {
						return false;
					}
				}
				return true;
			}

			@Override
			public int hashCode() {
				int hashcode = 0;
				for (State state : subStates) {
					hashcode += state.hashCode();
					hashcode *= 37;
				}
				return hashcode;
			}

			@Override
			public String toString() {
				return subStates.toString();
			}
		}
	}

}
