package languageFeatures;

import java.util.Optional;

public class LanguageFeature {

	public static void main(String[] args) {
		optional();
	}

	static void optional() {
		Optional<String> s1 = Optional.of("optional");
		Optional<String> s2 = Optional.ofNullable(null);
		s1.ifPresent(System.out::println);
		s2.ifPresent(System.out::println);
		System.out.println(s1.orElse("default1"));
		System.out.println(s2.orElse("default2"));

	}

}
