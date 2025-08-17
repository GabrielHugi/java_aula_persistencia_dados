// compile command
// javac -d .\bin -cp "lib\mysql-connector-j-9.4.0.jar;src" (Get-ChildItem -Recurse -Filter *.java).FullName (works in powershell)
// in src
// run command
// java -cp "bin;lib/mysql-connector-j-9.4.0.jar" Main
// in main

import java.util.*;
import functions.*;
import models.*;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecione a tabela: 1 - Produtos | 2 - Categorias");
        int tabela = sc.nextInt();
        sc.nextLine(); // Limpa buffer

        switch (tabela) {
            case 1:
                System.out.println("1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar");
                int opcao = sc.nextInt();
                sc.nextLine(); // Limpa buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Preco: ");
                        double preco = sc.nextDouble();
                        Produto p = new Produto(nome, preco);
                        dao.inserir(p);
                        break;

                    case 2:
                        for (Produto prod : dao.listar()) {
                            System.out.println(prod.getNome() + " - R$ " + prod.getPreco());
                        }
                        break;

                    case 3:
                        System.out.print("ID do produto: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        nome = sc.nextLine();
                        System.out.print("Novo preco: ");
                        preco = sc.nextDouble();
                        p = new Produto(nome, preco);
                        p.setId(id);
                        dao.atualizar(p);
                        break;

                    case 4:
                        System.out.print("ID do produto a deletar: ");
                        id = sc.nextInt();
                        dao.deletar(id);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
                break;

            case 2:
                CategoriaDAO cdao = new CategoriaDAO();

                System.out.println("1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar");
                int opcaoCat = sc.nextInt();
                sc.nextLine(); // Limpa buffer

                switch (opcaoCat) {
                    case 1: {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Ativo (true/false): ");
                        boolean ativo = sc.nextBoolean();
                        Categoria catToInsert = new Categoria(nome, ativo);
                        cdao.inserir(catToInsert);
                        break;
                    }

                    case 2: {
                        for (Categoria cat : cdao.listar()) {
                            System.out.println(cat.getNome() + " - Ativo: " + cat.getAtivo());
                        }
                        break;
                    }

                    case 3: {
                        System.out.print("ID da categoria: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Ativo (true/false): ");
                        boolean ativo = sc.nextBoolean();
                        Categoria catToUpdate = new Categoria(nome, ativo);
                        catToUpdate.setId(id);
                        cdao.atualizar(catToUpdate);
                        break;
                    }

                    case 4: {
                        System.out.print("ID da categoria a deletar: ");
                        int id = sc.nextInt();
                        cdao.deletar(id);
                        break;
                    }

                    default:
                        System.out.println("Opção inválida.");
                }
                break;

            default:
                System.out.println("Opção inválida.");
        }

        sc.close();
    }
}