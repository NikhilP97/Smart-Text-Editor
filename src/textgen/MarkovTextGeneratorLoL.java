package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(sourceText == "") {
			return;
		}
		String[] wordsInText = sourceText.split(" +");
		String prevWord;
//		System.out.println("Split words are");
//		for(String iterator : wordsInText) {
//			System.out.print(iterator+" ");
//		}
		System.out.println();
		if(starter == "") {
			starter = wordsInText[0];
		}
		starter = wordsInText[0];
		prevWord = starter;
		boolean hasNode = false;
		for(int i = 1; i < wordsInText.length; i++) {
			hasNode = false;
			for(ListNode itr : wordList) {
				if(itr.getWord().equals(prevWord)) {
					itr.addNextWord(wordsInText[i]);
					hasNode = true;
					break;
				}
			}
			if(!hasNode) {
				ListNode temp = new ListNode(prevWord);
				wordList.add(temp);
				temp.addNextWord(wordsInText[i]);
			}
			prevWord = wordsInText[i];
		}
		
		ListNode addLastWord = new ListNode(prevWord);
		wordList.add(addLastWord);
		addLastWord.addNextWord(starter);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(wordList.size() == 0) {
			return "";
		}
		if(numWords == 0) {
			return "";
		}
		String currword = starter;
		String output = "";
		output+=currword;
		while(numWords > 1) {
			ListNode getNode = getRequiredNode(currword);
			String randomWord = getNode.getRandomNextWord(rnGenerator);
			output = output+" "+randomWord;
			currword = randomWord;
			numWords--;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		String[] wordsInText = sourceText.split(" +");
		String prevWord;
//		System.out.println("Split words are");
//		for(String iterator : wordsInText) {
//			System.out.print(iterator+" ");
//		}
		System.out.println();
		starter = wordsInText[0];
		prevWord = starter;
		boolean hasNode = false;
		wordList.removeAll(wordList);
//		System.out.println("World list after remove all looks like : "+wordList);
		for(int i = 1; i < wordsInText.length; i++) {
			hasNode = false;
			for(ListNode itr : wordList) {
				if(itr.getWord().equals(prevWord)) {
					itr.addNextWord(wordsInText[i]);
					hasNode = true;
					break;
				}
			}
			if(!hasNode) {
				ListNode temp = new ListNode(prevWord);
				wordList.add(temp);
				temp.addNextWord(wordsInText[i]);
			}
			prevWord = wordsInText[i];
		}
		
		ListNode addLastWord = new ListNode(prevWord);
		wordList.add(addLastWord);
		addLastWord.addNextWord(starter);
	}
	
	// TODO: Add any private helper methods you need here.
	private ListNode getRequiredNode(String s) {
		for(ListNode itr : wordList) {
			if(itr.getWord().equals(s)) {
				return itr;
			}
		}
		return null;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println("Original text");
		System.out.println(textString);
		gen.train(textString);
//		System.out.println(gen);
		System.out.println("New generated text is");
		System.out.println(gen.generateText(20));
		System.out.println();
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println("Original text");
		System.out.println(textString2);
		gen.retrain(textString2);
//		System.out.println(gen);
		System.out.println("New generated next is");
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		String returnWord;
		returnWord = nextWords.get(generator.nextInt(nextWords.size()));
	    return returnWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


