package bo;

public enum Operator {
    ADDI ("+"),
    SOUS ("-"),
    MULTI ("*"),
    DIVI ("/");

    private String name = "";

    //Constructeur
    Operator(String name){
        this.name = name;
    }

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

    public static int calcOpe(Operator ope, int nb1, int nb2){

        int result = 0;

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
                result = nb1 / nb2;
                break;
        }

        return result;
    }

}
