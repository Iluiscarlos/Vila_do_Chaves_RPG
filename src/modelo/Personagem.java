package modelo;

/**
 *
 * @author luisk
 */
public class Personagem {
    private String casa;
    private String nome;
    private int vida;
    private int ataque;
    
    
    public Personagem(String casa, String nome, int vida, int ataque) {
        this.casa = casa;
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    public Personagem() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }
    
    
}
