package analizers;

import java.util.ArrayList;
import java.util.List;

public class VowelAndconsonantCompare implements IAnalizer {

	private List<Character> vowels;
	
	public VowelAndconsonantCompare() {
		this.vowels = new ArrayList<>();
		this.vowels.add('a');
		this.vowels.add('e');
		this.vowels.add('y');
		this.vowels.add('i');
		this.vowels.add('o');
		this.vowels.add('ą');
		this.vowels.add('ę');
		this.vowels.add('u');
		this.vowels.add('ó');
	}
	
	@Override
	public String apply(String text) {
		int vowels = 0;
		int consonants = 0;
		for(int i = 0; i < text.length(); i++) {
			if(this.vowels.contains(text.charAt(i))) {
				vowels++;
			}
			else {
				consonants++;
			}
		}
		if(vowels == consonants) {
			return "Taka samo liczba samogłosek i głosek";
		}
		else if(vowels > consonants) {
			return "Więcej samogłosek";
		}
		else {
			return "Więcej głosek";
		}
	}

	@Override
	public String getName() {
		return "Porównanie liczby głosek i samogłosek";
	}

}
