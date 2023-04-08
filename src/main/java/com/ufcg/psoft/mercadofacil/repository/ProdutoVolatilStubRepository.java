package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoVolatilStubRepository implements ProdutoRepository<Produto, Long> {
    @Override
    public Produto save(Produto produto) {
        return null;
    }

    @Override
    public Produto find(Long id) {
        if(id == 10L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500100")
                    .nome("Produto Dez")
                    .fabricante("Empresa Dez")
                    .preco(200.00)
                    .build();
        } else
        if(id == 20L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500100")
                    .nome("Produto Vinte")
                    .fabricante("Empresa Vinte")
                    .preco(450.00)
                    .build();
        } else
        if(id == 30L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Trinta")
                    .fabricante("Empresa Trinta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 40L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500100")
                    .nome("Produto Quarenta")
                    .fabricante("Empresa Quarenta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 50L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Cinquenta")
                    .fabricante("Empresa Cinquenta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 60L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Sessenta")
                    .fabricante("Empresa Sessenta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 70L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Setenta")
                    .fabricante("Empresa Setenta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 80L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Oitenta")
                    .fabricante("Empresa Oitenta")
                    .preco(450.00)
                    .build();
        } else
        if(id == 90L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Noventa")
                    .fabricante("Empresa Noventa")
                    .preco(450.00)
                    .build();
        } else
        if(id == 100L) {
            return Produto.builder()
                    .id(id)
                    .codigoBarra("7899137500104")
                    .nome("Produto Cem")
                    .fabricante("Empresa Cem")
                    .preco(450.00)
                    .build();
        }
        return null;
    }

    @Override
    public List<Produto> findAll() {
        return null;
    }

    @Override
    public Produto update(Produto produto) {
        if(produto.getId() == 10L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Dez Alterado")
                    .fabricante("Empresa Dez")
                    .preco(450.00)
                    .build();
        } else
        if(produto.getId() == 20L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Vinte")
                    .fabricante("Empresa Vinte")
                    .preco(0.00)
                    .build();
        } else
        if(produto.getId() == 30L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Trinta")
                    .fabricante("Empresa Trinta")
                    .preco(-10.00)
                    .build();
        } else
        if(produto.getId() == 40L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500104")
                    .nome("Produto Quarenta")
                    .fabricante("Empresa Quarenta")
                    .preco(450.00)
                    .build();
        }
        else
        if(produto.getId() == 50L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Cinquenta")
                    .fabricante("Empresa Cinquenta")
                    .preco(450.00)
                    .build();
        }
        else
        if(produto.getId() == 60L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome(null)
                    .fabricante("Empresa Sessenta")
                    .preco(450.00)
                    .build();
        }
        else
        if(produto.getId() == 70L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("")
                    .fabricante("Empresa Setenta")
                    .preco(450.00)
                    .build();
        } else
            if(produto.getId() == 80L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Oitenta")
                    .fabricante(null)
                    .preco(450.00)
                    .build();
        }
        else
        if(produto.getId() == 90L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("7899137500100")
                    .nome("Produto Noventa")
                    .fabricante("")
                    .preco(450.00)
                    .build();
        } else
        if(produto.getId() == 100L) {
            return Produto.builder()
                    .id(produto.getId())
                    .codigoBarra("")
                    .nome("Produto Cem")
                    .fabricante("Empresa Cem")
                    .preco(450.00)
                    .build();
        }
        return null;
    }

    @Override
    public void delete(Produto produto) {

    }

    @Override
    public void deleteAll() {

    }
}
