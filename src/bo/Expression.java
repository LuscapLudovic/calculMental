package bo;

import jdk.dynalink.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Expression {

    private static final  Pattern PATTERN = Pattern.compile("((\\-[0-9]|[0-9])+) ([/+*\\-]) ([0-9]+)\\w+");

    private ArrayList<Integer> numbers;
    private ArrayList<Operator> operators;
    private String calcul;
    private String result;

    private Expression(){};

    public ArrayList<Integer> getNumbers() { return numbers; }

    public void setNumbers(ArrayList<Integer> numbers) { this.numbers = numbers; }

    public ArrayList<Operator> getOperators() { return operators; }

    public void setOperators(ArrayList<Operator> operators) { this.operators = operators; }

    public String getCalcul() {
        return calcul;
    }

    public void setCalcul(String calcul) {
        this.calcul = calcul;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private static int getRandomNumber(int Min, int Max){
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }

    public static ArrayList<Expression> generateCalcul(int nb_calcule) {
        ArrayList<Expression> expressions = new ArrayList<>();

        Expression exp = new Expression();

        int nb_Operator = getRandomNumber(1, 3);

        exp.numbers.add(nb_Operator);
        StringBuilder expression = new StringBuilder(getRandomNumber(0, 99) + " ");

        for (int i = 0; i <= nb_Operator; i++){
            Operator op = Operator.values()[getRandomNumber(0, Operator.values().length -1)];
            exp.operators.add(op);
            expression.append(op.toString()).append(" ");

            int nb = getRandomNumber(0, 99);
            exp.numbers.add(nb);
            expression.append(nb).append(" ");
        }

        if ( nb_calcule > 1){
            expressions = generateCalcul(nb_calcule - 1);
        }
        exp.calcul = expression.toString();
        exp.result = Expression.calcAll(expression.toString());
        expressions.add(exp);

        return expressions;
    }

    public static ArrayList<String> getAllCalc(String exp){

        ArrayList<String> calcs = new ArrayList<>();

        Matcher m = PATTERN.matcher(exp);

        boolean b = m.find();

        System.out.println("///////////////////////////////////////////////////////////////////////////");
        System.out.println(exp);
        System.out.println(m.toString());

        if(b) {
            calcs.add(m.group(0));
        }

        return calcs;
    }

    public static String getPriorCalc(ArrayList<String> listCalc){

        String priorCalc = "";

        for (String tmpCalc: listCalc) {
            if (tmpCalc.contains(Operator.MULTI.toString()) || tmpCalc.contains(Operator.DIVI.toString())){
                priorCalc = tmpCalc;
            }
        }

        if (priorCalc.equals("")){
            priorCalc = listCalc.get(0);
        }

        System.out.println(priorCalc);
        return priorCalc;
    }

    public static String calc(String fullCalc, String calc){

        Operator op = Operator.toOpe(calc);

        String ope = String.valueOf(Operator.calcOpe(op, 1, 2));

        fullCalc = fullCalc.replace(calc, ope);

        return fullCalc;
    }

    public static String calcAll(String fullCalc){

        boolean valid = false;

        do {

            if (PATTERN.matcher(fullCalc).find()){
                ArrayList<String> calcs = Expression.getAllCalc(fullCalc);
                String priorCalc = Expression.getPriorCalc(calcs);
                fullCalc = Expression.calc(fullCalc, priorCalc);
            }else{
                valid = true;
            }

        }while(!valid);

        return fullCalc.trim();
    }
}
