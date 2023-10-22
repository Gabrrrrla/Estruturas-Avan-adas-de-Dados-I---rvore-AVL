/*
Trabalho de Estruturas Avançadas de Dados 2023/2 - Árvore AVL elaborado pelas alunas:
Ana Daudt, Emanuele Thomazzoni, Gabriela Bley, Luisa Becker e Scheila Yamashita
 */
package arvore;

public class OperacoesArvore {
    Nodo raiz;

    public OperacoesArvore() {
        raiz = null;
    }

    //Inserção Nodo
    public void inserirNodo(int valor){
        Nodo nodoNovo = new Nodo(valor);
        posicaoInsercao(raiz, nodoNovo);
    }

    //Posições Nodo
    public void posicaoInsercao(Nodo referencia, Nodo nodoNovo){
        if(referencia == null)
            raiz = nodoNovo;
        else
            if(nodoNovo.getValor() < referencia.getValor())
                if(referencia.getNodoEsquerdo() == null) {
                    System.out.println("Inserido valor " + nodoNovo.getValor() + " a esquerda de " + referencia.getValor());
                    referencia.setNodoEsquerdo(nodoNovo);
                    nodoNovo.setNodoPai(referencia);
                    verificarBalanceamento(referencia);
                }
                else {
                    System.out.println("Inserindo valor " + nodoNovo.getValor() + " a esquerda de " + referencia.getValor());
                    posicaoInsercao(referencia.getNodoEsquerdo(),nodoNovo);
                }
            else if (nodoNovo.getValor() > referencia.getValor())
                if(referencia.getNodoDireito() == null) {
                    System.out.println("Inserido valor " + nodoNovo.getValor() + " a direita de " + referencia.getValor());
                    referencia.setNodoDireito(nodoNovo);
                    nodoNovo.setNodoPai(referencia);
                    verificarBalanceamento(referencia);
                }
                else {
                    System.out.println("Inserindo valor " + nodoNovo.getValor() + " a direita de " + referencia.getValor());
                    posicaoInsercao(referencia.getNodoDireito(),nodoNovo);
                }
    }

    //Fator Balanceamento
    public int calculaAltura(Nodo nodo){
        if(nodo == null)
            return 0;
        else if(nodo.getNodoEsquerdo() == null && nodo.getNodoDireito() == null)
            return 1;
        else if(nodo.getNodoDireito() == null)
            return 1 + calculaAltura((nodo.getNodoEsquerdo()));
        else if(nodo.getNodoEsquerdo() == null)
            return 1 + calculaAltura((nodo.getNodoDireito()));
        else
            return 1 + Math.max(calculaAltura(nodo.getNodoEsquerdo()), calculaAltura(nodo.getNodoDireito()));
    }

    public void calculaFatorBalanceamento(){
        if(raiz != null) {
            calculaFatorBalanceamento(raiz);
            System.out.println();
        }
        else
            System.out.println("A árvore está vazia.");
    }
    public void calculaFatorBalanceamento(Nodo nodo){
        System.out.println("Nodo: " + nodo.getValor() + " FB: " + calculaAltura(nodo.getNodoEsquerdo()) + " - " + calculaAltura(nodo.getNodoDireito()));
        nodo.setFatorBalanceamento((calculaAltura(nodo.getNodoEsquerdo()) - calculaAltura(nodo.getNodoDireito())));
    }

    private void verificarBalanceamento(Nodo nodo) {
        calculaFatorBalanceamento(nodo);
        int fatorBalanceamento = nodo.getFatorBalanceamento();

        if(fatorBalanceamento == 2)

            if(calculaAltura(nodo.getNodoEsquerdo().getNodoEsquerdo()) >= calculaAltura(nodo.getNodoEsquerdo().getNodoDireito()))
                nodo = rotacaoDireita(nodo);//chamar metodo para rotação direita. Toda vez que uma sub-Árvore fica com um fator positivo e sua sub-Árvore da esquerda também tem um fator positivo
            else
                nodo = rotacaoDireitaDupla(nodo); //Toda vez que uma sub-Árvore fica com um fator positivo e sua sub-Árvore da esquerda tem um fator negativo

        else if(fatorBalanceamento == -2)

            if(calculaAltura(nodo.getNodoDireito().getNodoDireito()) >= calculaAltura(nodo.getNodoDireito().getNodoEsquerdo()))
                nodo = rotacaoEsquerda(nodo);
            else
                nodo = rotacaoEsquerdaDupla(nodo);

        if(nodo.getNodoPai() != null)
            verificarBalanceamento(nodo.getNodoPai());
        else
            raiz = nodo;
    }

    //Excluir Nodo
    public void excluirNodo(int valor) {
        raiz = excluirRecursivamente(raiz, valor);
    }

    private Nodo excluirRecursivamente(Nodo raiz, int valor) {
        if (raiz == null) {
            System.out.println("O nodo " + valor + " não foi encontrado na árvore.");
            return raiz;
        }

        if (valor < raiz.getValor()) {
            raiz.setNodoEsquerdo(excluirRecursivamente(raiz.getNodoEsquerdo(), valor));
        } else if (valor > raiz.getValor()) {
            raiz.setNodoDireito(excluirRecursivamente(raiz.getNodoDireito(), valor));
        } else {
            // Nodo com o valor a ser excluído foi encontrado

            if (raiz.getNodoEsquerdo() == null || raiz.getNodoDireito() == null) {
                Nodo novaRaiz = (raiz.getNodoEsquerdo() != null) ? raiz.getNodoEsquerdo() : raiz.getNodoDireito();

                if (novaRaiz == null) {
                    // Caso sem filhos ou com apenas um filho
                    novaRaiz = raiz;
                    raiz = null;
                } else {
                    // Caso com um filho
                    raiz = novaRaiz;
                }
            } else {
                // Caso com dois filhos
                Nodo sucessor = encontrarSucessor(raiz.getNodoDireito());
                raiz.setValor(sucessor.getValor());
                raiz.setNodoDireito(excluirRecursivamente(raiz.getNodoDireito(), sucessor.getValor()));
            }
        }

        //Balancear a árvore após a exclusão
        if (raiz != null) {
            verificarBalanceamento(raiz);
        }
        return raiz;
    }

    private Nodo encontrarSucessor(Nodo nodo) {
        Nodo atual = nodo;
        while (atual.getNodoEsquerdo() != null) {
            atual = atual.getNodoEsquerdo();
        }
        return atual;
    }

    //Buscar Nodo
    public Nodo buscarNodo(int valor) {
        return buscaRecursiva(raiz, valor);
    }

    private Nodo buscaRecursiva(Nodo nodo, int valor) {
        if (nodo == null) {
            System.out.println("O nodo " + valor + " não foi encontrado na árvore.");
            return null;
        }

        if (valor == nodo.getValor()) {
            System.out.println("O nodo " + valor + " foi encontrado na árvore, na posição " + nodo.getValor());
            return nodo;
        }

        if (valor < nodo.getValor()) {
            return buscaRecursiva(nodo.getNodoEsquerdo(), valor);
        } else {
            return buscaRecursiva(nodo.getNodoDireito(), valor);
        }
    }

    //Rotação direita (rota e balanceia)
    private Nodo rotacaoDireita(Nodo nodo) {
        Nodo novoNodo = nodo.getNodoEsquerdo();
        novoNodo.setNodoPai(nodo.getNodoPai());

        nodo.setNodoEsquerdo(novoNodo.getNodoDireito());

        if (nodo.getNodoEsquerdo() != null) {
            nodo.getNodoEsquerdo().setNodoPai(nodo);
        }

        novoNodo.setNodoDireito(nodo);
        nodo.setNodoPai(novoNodo);

        if (novoNodo.getNodoPai() != null) {
            if (novoNodo.getNodoPai().getNodoDireito() == nodo) {
                novoNodo.getNodoPai().setNodoDireito(novoNodo);
            } else if (novoNodo.getNodoPai().getNodoEsquerdo() == nodo) {
                novoNodo.getNodoPai().setNodoEsquerdo(novoNodo);
            }
        }
        calculaFatorBalanceamento(nodo);
        calculaFatorBalanceamento(novoNodo);
        return novoNodo;
    }

    //Rotação direita dupla
    private Nodo rotacaoDireitaDupla(Nodo nodo) {
        nodo.setNodoEsquerdo(rotacaoEsquerda(nodo.getNodoEsquerdo()));
        return rotacaoDireita(nodo);
    }

    //Rotação esquerda
    private Nodo rotacaoEsquerda(Nodo nodo) {
        Nodo novoNodo = nodo.getNodoDireito();
        novoNodo.setNodoPai(nodo.getNodoPai());

        nodo.setNodoDireito(novoNodo.getNodoEsquerdo());

        if (nodo.getNodoDireito() != null) {
            nodo.getNodoDireito().setNodoPai(nodo);
        }

        novoNodo.setNodoEsquerdo(nodo);
        nodo.setNodoPai(novoNodo);

        if (novoNodo.getNodoPai() != null) {
            if (novoNodo.getNodoPai().getNodoDireito() == nodo) {
                novoNodo.getNodoPai().setNodoDireito(novoNodo);
            } else if (novoNodo.getNodoPai().getNodoEsquerdo() == nodo) {
                novoNodo.getNodoPai().setNodoEsquerdo(novoNodo);
            }
        }
        calculaFatorBalanceamento(nodo);
        calculaFatorBalanceamento(novoNodo);
        return novoNodo;
    }

    //Rotação esquerda dupla
    private Nodo rotacaoEsquerdaDupla(Nodo nodo) {
        nodo.setNodoDireito(rotacaoDireita(nodo.getNodoDireito()));
        return rotacaoEsquerda(nodo);
    }

    public void ordenacoes(int tipoOrdenacao){
        if(raiz != null){
            switch(tipoOrdenacao)
            {
                case 1:
                    if(raiz != null)
                        preOrdem(raiz);
                    else
                        System.out.println("A árvore está vazia.");
                    break;
                case 2:
                    if(raiz != null)
                        emOrdem(raiz);
                    else
                        System.out.println("A árvore está vazia.");
                    break;
                case 3:
                    if(raiz != null)
                        posOrdem(raiz);
                    else
                        System.out.println("A árvore está vazia.");
                    break;
            }
        }
    }

    //Árvore em Pré-Ordem
    public void preOrdem(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            preOrdem(nodo.getNodoEsquerdo());
            preOrdem(nodo.getNodoDireito());
        }
    }

    //Árvore em Em-Ordem
    public void emOrdem(Nodo nodo){
        if (nodo != null) {
            emOrdem(nodo.getNodoEsquerdo());
            System.out.print(nodo.getValor() + " ");
            emOrdem(nodo.getNodoDireito());
        }
    }

    //Árvore em Pós-Ordem
    public void posOrdem(Nodo nodo){
        if (nodo != null) {
            posOrdem(nodo.getNodoEsquerdo());
            posOrdem(nodo.getNodoDireito());
            System.out.print(nodo.getValor() + " ");
        }
    }
//LISTA DE TAREFAS:

    //inserir OK, verificar balanceamento OK, exibir fator balanceamento ok, setar balanceamento OK, retornar altura, rotaÃ§Ã£o (direita e esquerda) (tentei fazer espero que rode), excluir ok.
    //buscar elemento, verificar se os metodos (pre ordem, pos ordem, em ordem) estão ok.
    //ajeitar a formatação das letras
}
