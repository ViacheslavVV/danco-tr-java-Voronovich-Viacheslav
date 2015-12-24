package classes;

public class Text extends Word {

	private Word[] words;
	
	public Text(String value) {
		super(value);
		words = splitIntoWords(); 
	}
	
	public Word[] getWords() {
		return words;
	}
	
	public void setWords(Word[] words) {
		this.words = words;
	}
	
	private Word[] splitIntoWords() //delimiter is space (no conditions anymore)
	{
		String stringAfterReplaceSpaces = this.value.replaceAll("[ ]{2,}", " ");
		String stringAfterRemoveStartSpace = stringAfterReplaceSpaces.replaceFirst("^ ", "");
		String[] strings = stringAfterRemoveStartSpace.split(" ");
		return createWordsFromStrings(strings);
	}
	
	private Word[] createWordsFromStrings(String[] strings)
	{
		Word[] words = new Word[strings.length];
		for (int i=0; i<strings.length; i++) {
			words[i] = new Word(strings[i]);
			}
		return words;
	}

}
