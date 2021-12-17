public class ProfesorTP implements Teacher {
    private String _Nombre;
    private int _Tipo;
    private int _salarioBaseMensual =2000;
    private int _comision = 500;
    private int _bonus = 100;
    
    ProfesorTP(int type, String nombre) {
        _Tipo = type;
        _Nombre = nombre;
    }

    public String get_Nombre() {
        return _Nombre;
    }

    public int get_Tipo() {
        return _Tipo;
    }

    public int Sueldo() {
        return _salarioBaseMensual;
    }
}
