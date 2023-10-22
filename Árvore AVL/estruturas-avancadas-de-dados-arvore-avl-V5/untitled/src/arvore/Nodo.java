/*
Trabalho de Estruturas Avançadas de Dados 2023/2 - Árvore AVL elaborado pelas alunas:
Ana Daudt, Emanuele Thomazzoni, Gabriela Bley, Luisa Becker e Scheila Yamashita
 */
package arvore;

public class Nodo {
    private int valor;
    private Nodo nodoPai;
    private int fatorBalanceamento;
    private Nodo nodoEsquerdo;
    private Nodo nodoDireito;

    public Nodo(int valor) {
        this.valor = valor;
        fatorBalanceamento = 0;
        nodoEsquerdo = null;
        nodoDireito = null;
        nodoPai = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getNodoPai() {
        return nodoPai;
    }

    public void setNodoPai(Nodo nodoPai) {
        this.nodoPai = nodoPai;
    }

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(int fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public Nodo getNodoEsquerdo() {
        return nodoEsquerdo;
    }

    public void setNodoEsquerdo(Nodo nodoEsquerdo) {
        this.nodoEsquerdo = nodoEsquerdo;
    }

    public Nodo getNodoDireito() {
        return nodoDireito;
    }

    public void setNodoDireito(Nodo nodoDireito) {
        this.nodoDireito = nodoDireito;
    }
}
