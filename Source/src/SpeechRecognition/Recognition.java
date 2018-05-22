package SpeechRecognition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class Recognition {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.setAcousticModelPath("Files/acousticmodel");
		config.setDictionaryPath("Files/dictionary/Vietnamese.dic");
		config.setLanguageModelPath("Files/languageModel/Vietnamese.lm.dmp");
		//config.setSampleRate(8000);
		//System.out.println(config.getLanguageModelPath());
		RecognitionByVoice(config);
		//RecognitionByFile(config);
	}
	public static void RecognitionByVoice(Configuration config){
		try {
			LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);
			
			recognizer.startRecognition(true);
			System.out.println("--------------------------------");
			System.out.println("Hãy nói!");
			SpeechResult result;
			while((result = recognizer.getResult()) != null) {
				System.out.println("Nhận diện: " + result.getHypothesis());
				System.out.println("Vui lòng nói!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void RecognitionByFile(Configuration config){
		try {
			config.setSampleRate(8000);
			StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(config);
			String soundPath = "Files/test/";
			String fileName;
			//fileName = "Toianbanhmi108";
			//fileName = "Toiuongcafe114";  
			//fileName = "Toidikaraoke124";
			//fileName = "Toigapbaoke108";
			//fileName = "Oithatlaphe105";
			//fileName = "Tenhuconde106";
			//fileName = "Hoangtubatconngua121";
			//fileName = "Thaygiaothaytoi121";
			//fileName = "Thaygiaophatem125";
			fileName = "Connguadaconde123";
			String fileExtension = ".wav";
			String filePath = soundPath + fileName + fileExtension;
			InputStream input = new FileInputStream(filePath);
			SpeechResult result;
			
			recognizer.startRecognition(input);
			while((result = recognizer.getResult()) != null) {
				System.out.println("Máy nhận dạng: " + result.getHypothesis());
			}
			recognizer.stopRecognition();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
