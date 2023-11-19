package modelo;

public class Usuario implements Observer{

    private String nombre;
    private String cedula;
    private String contrasena;
    private String numero;
    private int genero; //1 mujer, 2 hombre y 3 no binario

    public Usuario(){
        
    }

    public Usuario(String nombre, String cedula, String contrasena, String numero, int genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.numero = numero;
        this.genero = genero;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", cedula=" + cedula + ", contrasena=" + contrasena + ", numero=" + numero + ", genero=" + genero + '}';
    }

    public Usuario(Builder builder){
        this.nombre = builder.nombre;
        this.cedula = builder.cedula;
        this.contrasena = builder.contrasena;
        this.numero = builder.numero;
        this.genero = builder.genero;
    }

    @Override
    public String update(Cita cita, boolean opc) {
        if(opc){
        return nombre + " se agendó su cita: " + cita.getFecha() + " con hora: " + cita.getHora();
        }
        return nombre + " se canceló su cita: " + cita.getFecha() + " con hora: " + cita.getHora();
    }
    
    public static class Builder {

        private String nombre;
        private String cedula;
        private String contrasena;
        private String numero;
        private int genero;

        public Usuario.Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Usuario.Builder cedula(String cedula) {
            this.cedula = cedula;
            return this;
        }

        public Usuario.Builder contrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Usuario.Builder numero(String numero) {
            this.numero = numero;
            return this;
        }

        public Usuario.Builder genero(int genero) {
            this.genero = genero;
            return this;
        }
        
        public Usuario build(){
            
            return new Usuario(this);
        }
    }
}
