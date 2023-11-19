
package modelo;


public class Agenda {
    private Usuario usuario;
    private Cita cita;

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @Override
    public String toString() {
        return "Agenda{" + "usuario=" + usuario + ", cita=" + cita + '}';
    }
    
    public Agenda(Builder builder){
        this.usuario = builder.usuario;
        this.cita = builder.cita;
    }
    
    public static class Builder{
        private Usuario usuario;
        private Cita cita;

        public Agenda.Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Agenda.Builder cita(Cita cita) {
            this.cita = cita;
            return this;
        }
        
        public Agenda build(){
            return new Agenda(this);
        }
    }
}
