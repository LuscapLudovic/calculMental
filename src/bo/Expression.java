package bo;

import jdk.dynalink.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Expression {

    //region Properties
    private ArrayList<Double> numbers = new ArrayList<>();
    private ArrayList<Operator> operators = new ArrayList<>();
    private String calcul;
    private int result;
    //endregion


    //region Constructors
    private Expression(){};

    public Expression(ArrayList<Double> numbers, ArrayList<Operator> operators, String calcul, int result) {
        this.numbers.addAll(numbers);
        this.operators.addAll(operators);
        this.calcul = calcul;
        this.result = result;
    }
    //endregion

    //region Getter and Setter
    public ArrayList<Double> getNumbers() { return numbers; }

    public void setNumbers(ArrayList<Double> numbers) { this.numbers = numbers; }

    public ArrayList<Operator> getOperators() { return operators; }

    public void setOperators(ArrayList<Operator> operators) { this.operators = operators; }

    public String getCalcul() {
        return calcul;
    }

    public void setCalcul(String calcul) {
        this.calcul = calcul;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    //endregion



    //region Methods

    /**
     * Permet de récupérer un nombre entier aléatoire
     * @param Min
     * @param Max
     * @return un nombre aléatoire
     */
    private static int getRandomNumber(int Min, int Max){
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }


    /**
     * Génère un nombre non définie de calcul aléatoire
     * @param nb_calcule
     * @return  la liste des calculs
     * @throws CloneNotSupportedException
     */
    public static ArrayList<Expression> generateCalcul(int nb_calcule) throws CloneNotSupportedException {
        ArrayList<Expression> expressions = new ArrayList<>();

        Expression exp = new Expression();

        // Génération des nombres et opérateurs aléatoires du calculs
        int nb_Operator = getRandomNumber(1, 3);

        int nb = getRandomNumber(0, 10);

        exp.numbers.add((double)nb);
        StringBuilder expression = new StringBuilder().append(nb).append(" ");

        for (int i = 0; i <= nb_Operator; i++){
            Operator op = Operator.values()[getRandomNumber(0, Operator.values().length -1)];
            exp.operators.add(op);
            expression.append(op.toString()).append(" ");

            nb = getRandomNumber(0, 10);
            exp.numbers.add((double)nb);
            expression.append(nb).append(" ");
        }

        if ( nb_calcule > 1){
            expressions = generateCalcul(nb_calcule - 1);
        }
        // Assignation des valeurs aux propriétés
        exp.calcul = expression.toString();
        exp.result = exp.calcAll();
        expressions.add(exp);

        return expressions;
    }

    /**
     * Retourne le calcul prioritaire dans une expressions comprenants 1 ou plusieurs opérateurs
     * @return la position de l'opérateur du calcul prioritaire
     */
    private int getPriorCalc(){

        int priorCalc = -1;

        for (int i = 0; i < operators.size(); i++){
            if (operators.get(i) == Operator.MULTI || operators.get(i) == Operator.DIVI){
                priorCalc = i;
                break;
            }
        }

        if (priorCalc == -1){
            priorCalc = 0;
        }

        return priorCalc;
    }

    /**
     * Calcule le calcul avec l'opérateur correspondant à la position indiquée et actualise l'expression
     * @param prior position de l'opérateur
     * @throws ArithmeticException
     */
    private void calc(int prior) throws ArithmeticException {

        double result = Operator.calcOpe(operators.get(prior), numbers.get(prior), numbers.get(prior + 1));

        operators.remove(prior);

        numbers.set(prior, result);
        numbers.remove(prior + 1);

    }

    /**
     * Calcule l'ensemble de l'expression
     * @return  le résultat arrondie de l'expression
     * @throws CloneNotSupportedException
     */
    private int calcAll() throws CloneNotSupportedException {

        boolean valid = false;

        Expression cpyExp = this.clone();

            do {
                try{
                    if (cpyExp.operators.size() != 0){
                        int prior = cpyExp.getPriorCalc();
                        cpyExp.calc(prior);
                    }else{
                        valid = true;
                    }

                }catch (ArithmeticException e){
                    Expression tempExp = Expression.generateCalcul(1).get(0);
                    this.numbers = tempExp.numbers;
                    this.result = tempExp.result;
                    this.calcul = tempExp.calcul;
                    this.operators = tempExp.operators;
                    System.out.println("Error calcul");
                    cpyExp = this.clone();
                }

            }while(!valid);

        return cpyExp.numbers.get(0).intValue();
    }


    /**
     * Retourne un clone de l'expression
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Expression(this.numbers, this.operators, this.calcul, this.result);
    }
    //endregion
}
