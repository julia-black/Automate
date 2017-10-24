import sun.font.FontRunIterator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> signs = new ArrayList<>();
    public static List<String> states = new ArrayList<>();
    public static List<Tetro> transactions = new ArrayList<>();
    public static List<String > endStates = new ArrayList<>();

    //Возвращает true/false и n - максимальную длину найденной подстроки
    private static Pair f(Automate automate, List<Character> chars, int index){
        System.out.println();
        boolean result = false;
        int count = 0;
        List<String> states;
        List<String> newStates = null;
        for (int i = index; i < chars.size(); i++) {
            if(automate.signs.contains(chars.get(i).toString())){ //если содержит такой вх. сигнал
                System.out.println(chars.get(i) + " State = " + automate.getCurrentState());
                automate.execute(chars.get(i));

                newStates = automate.getCurrentState();
                System.out.println("New state = " + newStates);
            }
            if(newStates != null){
                states = newStates;
                newStates = null;

                if(automate.containsElem(automate.getEndState(), states)){
                    count = i - index + 1;
                    result = true;
                }
            }
            else
                break;
        }
        Pair pair = new Pair(result, count);
        return pair;
    }

    public static List<Character> readInputString(){
        List<Character> chars = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("input.txt")));
            int c;
            while ((c = reader.read()) != -1) {
                chars.add((char) c);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Input string: ");
        for (Character c : chars) {
            System.out.print(c);
        }
        System.out.println();
        return chars;
    }

    public static void readInputAutomate() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("input_automate_task2.txt"), StandardCharsets.UTF_8);
        for(int i = 0; i < lines.size(); i++){
            //Сигналы
            if(i == 0){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    signs.add(ch);
                }
            }
            //Состояния
            else if(i == 1){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    states.add(ch);
                }
            }
            //Вых. состояния
            else if(i==2){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    endStates.add(ch);
                }
            }
            //Табл. переходов
            else
            {
                String[] string = lines.get(i).split("/");
                List<String> input = new ArrayList<>();
                String inputState = "";
                String result = "";
                List<String> results = new ArrayList<>();
                for(int k = 0; k < string.length; k++){
                    //System.out.println(string[i]);
                    String[] elems = string[k].split("'");
                    if(k == 0){
                        for(String ch : elems){
                            input.add(ch);

                        }
                    }
                    if(k == 1){ //если это вх. сост
                        inputState = string[k];
                    }
                    else if(k == 2){ //если это вых. сост
                        String[] elems1 = string[k].split("'");
                        if(elems1.length > 1){
                            for(String ch: elems1){
                                results.add(ch);
                                transactions.add(new Tetro(input, inputState, results));
                            }
                        }
                        else {
                            result = string[k];
                            transactions.add(new Tetro(input, inputState, result));
                        }
                    }
                }
            }
        }

    }

    public static void readInputAutomateID() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input_automate_ID.txt"), StandardCharsets.UTF_8);
        for(int i = 0; i < lines.size(); i++){
            //Сигналы
            if(i == 0){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    if(ch.equals("letter")){
                        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        for (int j = 0; j < letters.length(); j++) {
                            signs.add(Character.toString(letters.charAt(j)));
                        }

                    }
                    else if(ch.equals("number")){
                        for (int j = 0; j < 10 ; j++) {
                            signs.add(j + "");
                        }
                    }
                    else if(ch.equals("slash")){
                        signs.add("/");
                    }
                    else {
                        signs.add(ch);
                    }
                }
            }
            //Состояния
            else if(i == 1){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    states.add(ch);
                }
            }
            //Вых. состояния
            else if(i==2){
                String[] string = lines.get(i).split(" ");
                for(String ch : string){
                    endStates.add(ch);
                }
            }
            //Табл. переходов
            else
            {
                String[] string = lines.get(i).split("/");
                List<String> input = new ArrayList<>();
                String inputState = "";
                String result = "";
                List<String> results = new ArrayList<>();
                for(int k = 0; k < string.length; k++){
                    //System.out.println(string[i]);
                    String[] elems = string[k].split("'");
                    if(k == 0){
                        for(String ch : elems){
                            if(ch.equals("letter")){
                                String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                for (int j = 0; j < letters.length(); j++) {
                                    input.add(Character.toString(letters.charAt(j)));
                                }

                            }
                            else if(ch.equals("number")){
                                for (int j = 0; j < 10 ; j++) {
                                    input.add(j + "");
                                }
                            }
                            else if(ch.equals("slash")){
                                input.add("/");
                            }
                            else {
                                input.add(ch);
                            }

                        }
                    }
                    if(k == 1){ //если это вх. сост
                        inputState = string[k];
                    }
                    else if(k == 2){ //если это вых. сост
                        String[] elems1 = string[k].split("'");
                        if(elems1.length > 1){
                            for(String ch: elems1){
                                results.add(ch);
                                transactions.add(new Tetro(input, inputState, results));
                            }
                        }
                        else {
                            result = string[k];
                            transactions.add(new Tetro(input, inputState, result));
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        List<Character> chars = readInputString();
        //readInputAutomate();
        readInputAutomateID();

        List<String> beginState = new ArrayList<>();
        beginState.add("1");

        String results = "";
        Automate automate = new NotDeterminatedAutomate(states, signs, endStates, transactions, beginState);


        System.out.println("\nOutput:");
        for (int index = 0; index < chars.size(); ) {
            automate.setCurrentState(beginState);
            Pair pair = f(automate, chars, index);
            System.out.println(pair.getN() + " " + pair.isRes());
            if(pair.isRes()){
                for (int i = index; i < index + pair.getN(); i++) {
                    results += chars.get(i).toString();
                }
                results += "\n";
                index += pair.getN();
            }
            else {
                index++;
            }
        }

        if(results != ""){
           System.out.println(results);
        }
        else {
            System.out.println("No numbers");
        }
     }
}
