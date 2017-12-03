package sousajunior.devfest.firestore.Model;

/**
 * Created by SJ on 11/22/2017.
 */

public class Filme {
    private String titulo;
    private String comentario;

    public Filme() {
    }

    public Filme(String titulo, String comentario) {
        this.titulo = titulo;
        this.comentario = comentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
