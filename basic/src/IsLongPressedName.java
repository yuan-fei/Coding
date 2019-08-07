/**Leetcode 925 */
public class IsLongPressedName {

	public static void main(String[] args) {
		System.out.println(isLongPressedName("alex", "aaleex"));
		System.out.println(isLongPressedName("saeed", "ssaaedd"));
	}

	public static boolean isLongPressedName(String name, String typed) {
		int i = 0;
		int j = 0;
		int n = name.length();
		int m = typed.length();
		while (i < n && j < m) {
			if (name.charAt(i) == typed.charAt(j)) {
				int cnt1 = 1;
				while (i + 1 < n && name.charAt(i) == name.charAt(i + 1)) {
					i++;
					cnt1++;
				}
				int cnt2 = 1;
				while (j + 1 < m && typed.charAt(j) == typed.charAt(j + 1)) {
					j++;
					cnt2++;
				}
				if (cnt2 < cnt1) {
					return false;
				}
				i++;
				j++;
			} else {
				return false;
			}
		}
		if (i < n || j < m) {
			return false;
		}
		return true;
	}

}
