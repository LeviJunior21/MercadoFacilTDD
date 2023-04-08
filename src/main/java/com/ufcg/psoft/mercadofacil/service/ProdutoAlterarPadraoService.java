package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoAlterarPadraoService implements ProdutoAlterarService {
    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;
    @Override
    public Produto alterar(Produto produto) {
        if(produto.getPreco() <= 0) {
            throw new RuntimeException("Preco invalido!"); //
        }
        if (produto.getCodigoBarra().equals("") || validaCodigoBarra(produto.getCodigoBarra()) == false) {
            throw new RuntimeException("Código de barras inválido!");
        }
        if (produto.getFabricante() == null || produto.getFabricante().equals("")) {
            throw new RuntimeException("Fabricante inválido!");
        }
        if (produto.getNome() == null || produto.getNome().equals("")) {
            throw new RuntimeException("Nome inválido!");
        }

        return produtoRepository.update(produto);
    }

    public static boolean validaCodigoBarra(String codigoBarra) {
        int soma = 0;
        boolean resultado = false;

        String[] codigo = codigoBarra.split("");
        for (int i = codigo.length - 2; i >= 1 ; i-=2) {
            soma += 3 * Integer.parseInt(codigo[i]);
            soma += Integer.parseInt(codigo[i - 1]);
        }
        soma = soma + Integer.parseInt(codigo[codigo.length - 1]);
        if (soma % 10 == 0) {
            resultado = true;
        }
        return resultado;
    }
}