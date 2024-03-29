package bo;

public enum Operator {
    ADDI ("+"),
    SOUS ("-"),
    MULTI ("*"),
    DIVI ("/");

    private String name = "";


    Operator(String name){
        this.name = name;
    }

    //region Methods
    public String toString(){
        return name;
    }

    public static Operator toOpe( String _ope ){

        Operator ope = null;

        for (Operator tmpOpe: Operator.values()) {
            if (_ope.contains(tmpOpe.name)){
                ope = tmpOpe;
                break;
            }
        }

        return ope;

    }

    /**
     * Effectue le calcul en fonction de l'opérateur
     * @param ope
     * @param nb1
     * @param nb2
     * @return
     */
    public static double calcOpe(Operator ope, double nb1, double nb2){

        double result = 0;

        switch (ope){
            case ADDI:
                result = nb1 + nb2;
                break;
            case SOUS:
                result = nb1 - nb2;
                break;
            case MULTI:
                result = nb1 * nb2;
                break;
            case DIVI:
                if (nb2 == 0)
                    throw new ArithmeticException();
                result = nb1 / nb2;
                break;
        }

        return result;
    }
    //endregion

}
