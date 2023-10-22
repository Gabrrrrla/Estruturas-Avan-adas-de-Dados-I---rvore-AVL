/*
Trabalho de Estruturas Avançadas de Dados 2023/2 - Árvore AVL elaborado pelas alunas:
Ana Daudt, Emanuele Thomazzoni, Gabriela Bley, Luisa Becker e Scheila Yamashita
 */
package ui;
import arvore.OperacoesArvore;

import java.io.IOException;

public class Menu {
    //LISTA DE TAREFAS:
    //menu de opções: (1- inserir 2- remover 3- buscar 4- exibir em ordem 5- exibir pos ordem etc
    Teclado teclado = new Teclado();
    private int escolha;
    OperacoesArvore operacoes = new OperacoesArvore();

    public void menu() throws IOException {
        while (escolha != 8) {
            exibeMenu();
            leEscolha();
        }
    }

    public void exibeMenu() {
        System.out.println("\n-----------MENU-----------");
        System.out.println("1. Inserir nodo");
        System.out.println("2. Excluir nodo");
        System.out.println("3. Buscar nodo");
        System.out.println("4. Verificar fatores de balanceamento");
        System.out.println("5. Exibir árvore Pré-Ordem");
        System.out.println("6. Exibir árvore Em-Ordem");
        System.out.println("7. Exibir árvore Pós-Ordem");
        System.out.println("8. Sair da árvore\n");
    }

    public void leEscolha() {
        escolha = teclado.leInt("Escolha a opção desejada: \n");

        switch (escolha) {
            case 1:
                operacoes.inserirNodo(teclado.leInt("Digite o valor a ser inserido: "));
                System.out.println("Exibição da árvore depois da inserção [pré ordem]: ");
                operacoes.ordenacoes(1);
                break;
            case 2:
                operacoes.excluirNodo(teclado.leInt("Digite o valor a ser excluído: "));
                System.out.println("Exibição da árvore depois da exclusão [pré ordem]: ");
                operacoes.ordenacoes(1);
                break;
            case 3:
                operacoes.buscarNodo(teclado.leInt("Digite o valor a ser buscado: "));
                break;
            case 4:
                operacoes.calculaFatorBalanceamento();
                break;
            case 5:
                operacoes.ordenacoes(1); //PRÉ ORDEM
                break;
            case 6:
                operacoes.ordenacoes(2); //EM ORDEM
                break;
            case 7:
                operacoes.ordenacoes(3); //PÓS ORDEM
                break;
            case 8:
                break;
        }
    }
}

