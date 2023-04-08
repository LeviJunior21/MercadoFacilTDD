package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Serviço de alteração do produto")
public class ProdutoAlterarServiceTests {

    @Autowired
    ProdutoAlterarService driver;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;
    Produto produto2;
    Produto produto3;
    Produto produto4;
    Produto produto10;
    Produto produto6;
    Produto produto7;
    Produto produto8;
    Produto produto9;
    @BeforeEach
    void setup() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto = produtoRepository.find(10L);

        Mockito.when(produtoRepository.find(20L))
                .thenReturn(Produto.builder()
                        .id(20L)
                        .codigoBarra("7899137500104")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(-450.00)
                        .build()
                );
        produto2 = produtoRepository.find(20L);

        Mockito.when(produtoRepository.find(100L))
                .thenReturn(Produto.builder()
                        .id(100L)
                        .codigoBarra("")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(-450.00)
                        .build()
                );
        produto3 = produtoRepository.find(100L);

        Mockito.when(produtoRepository.find(40L))
                .thenReturn(Produto.builder()
                        .id(40L)
                        .codigoBarra("")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto4 = produtoRepository.find(40L);

        Mockito.when(produtoRepository.find(60L))
                .thenReturn(Produto.builder()
                        .id(60L)
                        .codigoBarra("7899137500100")
                        .nome(null)
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto6 = produtoRepository.find(60L);

        Mockito.when(produtoRepository.find(70L))
                .thenReturn(Produto.builder()
                        .id(70L)
                        .codigoBarra("7899137500100")
                        .nome("")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto7 = produtoRepository.find(70L);

        Mockito.when(produtoRepository.find(80L))
                .thenReturn(Produto.builder()
                        .id(80L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez")
                        .fabricante(null)
                        .preco(450.00)
                        .build()
                );
        produto8 = produtoRepository.find(80L);

        Mockito.when(produtoRepository.find(90L))
                .thenReturn(Produto.builder()
                        .id(90L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez")
                        .fabricante("")
                        .preco(450.00)
                        .build()
                );
        produto9 = produtoRepository.find(90L);
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoNovoNomeValido() {
        // Arrange
        produto.setNome("Produto Dez Atualizado");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez Atualizado")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);

        // Assert
        assertEquals("Produto Dez Atualizado", resultado.getNome());
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoPrecoEhZero() {
        // Arrange
        produto.setPreco(0.00);

        try {
            Mockito.when(produtoRepository.update(produto))
                    .thenReturn(Produto.builder()
                            .id(10L)
                            .codigoBarra("7899137500100")
                            .nome("Produto Dez Atualizado")
                            .fabricante("Empresa Dez")
                            .preco(0.00)
                            .build()
                    );

            // Act
            Produto resultado = driver.alterar(produto);
        } catch (Exception e) {
            // Assert
            assertEquals("Preco invalido!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoPrecoEhValido() {
        // Arrange
        produto.setPreco(1.00);

        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez Atualizado")
                        .fabricante("Empresa Dez")
                        .preco(1.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);
        // Assert
        assertEquals(1.00, resultado.getPreco());

    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoPrecoEhNegativo() {
        // Arrange
        produto2.setCodigoBarra("7899137500100");

        try {
            Mockito.when(produtoRepository.update(produto2))
                    .thenReturn(Produto.builder()
                            .id(20L)
                            .codigoBarra("7899137500100")
                            .nome("Produto Dez Atualizado")
                            .fabricante("Empresa Dez")
                            .preco(-450.00)
                            .build()
                    );

            // Act
            Produto resultado = driver.alterar(produto2);
            // Assert
            assertEquals("7899137500100", resultado.getCodigoBarra());
        } catch(Exception e) {
            assertEquals("Preco invalido!", e.getMessage());
        }

    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoCodigoDeBarrasEhValido() {
        // Arrange
        produto2.setCodigoBarra("7899137500100");
        produto2.setPreco(1.00);
        Mockito.when(produtoRepository.update(produto2))
                .thenReturn(Produto.builder()
                        .id(20L)
                        .codigoBarra("7899137500100")
                        .nome("Produto Dez Atualizado")
                        .fabricante("Empresa Dez")
                        .preco(1.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto2);
        // Assert
        assertEquals("7899137500100", resultado.getCodigoBarra());

    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoCodigoBarrasEhInvalido() {
        // Arrange
        produto.setCodigoBarra("7899137500104");
        try {
            Mockito.when(produtoRepository.update(produto))
                    .thenReturn(Produto.builder()
                            .id(10L)
                            .codigoBarra("7899137500104")
                            .nome("Produto Dez Atualizado")
                            .fabricante("Empresa Dez")
                            .preco(1.00)
                            .build()
                    );

            // Act
            Produto resultado = driver.alterar(produto);
        } catch (Exception e) {
            // Assert
            assertEquals("Código de barras inválido!", e.getMessage());
        }
    }
    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoCodigoBarrasEhVazio() {
        // Arrange
        produto2.setCodigoBarra("");
        try {
            Mockito.when(produtoRepository.update(produto4))
                    .thenReturn(Produto.builder()
                            .id(40L)
                            .codigoBarra("")
                            .nome("Produto Dez Atualizado")
                            .fabricante("Empresa Dez")
                            .preco(1.00)
                            .build()
                    );
            // Act
            Produto resultado = driver.alterar(produto4);
        } catch (Exception e) {
            // Assert
            assertEquals("Código de barras inválido!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoNomeEhNull() {
        // Arrange
        produto6.setNome(null);
        try {
            Mockito.when(produtoRepository.update(produto6))
                    .thenReturn(Produto.builder()
                            .id(60L)
                            .codigoBarra("7899137500100")
                            .nome(null)
                            .fabricante("Empresa Dez")
                            .preco(1.00)
                            .build()
                    );
            // Act
            Produto resultado = driver.alterar(produto6);
        } catch (Exception e) {
            // Assert
            assertEquals("Nome inválido!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoNomeEhVaio() {
        // Arrange
        produto7.setNome("");
        try {
            Mockito.when(produtoRepository.update(produto7))
                    .thenReturn(Produto.builder()
                            .id(70L)
                            .codigoBarra("7899137500100")
                            .nome("")
                            .fabricante("Empresa Dez")
                            .preco(1.00)
                            .build()
                    );
            // Act
            Produto resultado = driver.alterar(produto7);
        } catch (Exception e) {
            // Assert
            assertEquals("Nome inválido!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoFabricanteEhNull() {
        // Arrange
        produto8.setFabricante(null);
        try {
            Mockito.when(produtoRepository.update(produto8))
                    .thenReturn(Produto.builder()
                            .id(80L)
                            .codigoBarra("7899137500100")
                            .nome("Produto Dez")
                            .fabricante(null)
                            .preco(1.00)
                            .build()
                    );
            // Act
            Produto resultado = driver.alterar(produto8);
        } catch (Exception e) {
            // Assert
            assertEquals("Fabricante inválido!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoFabricanteEhVazio() {
        // Arrange
        produto9.setFabricante("");
        try {
            Mockito.when(produtoRepository.update(produto9))
                    .thenReturn(Produto.builder()
                            .id(90L)
                            .codigoBarra("7899137500100")
                            .nome("Produto Dez")
                            .fabricante("")
                            .preco(1.00)
                            .build()
                    );
            // Act
            Produto resultado = driver.alterar(produto9);
        } catch (Exception e) {
            // Assert
            assertEquals("Fabricante inválido!", e.getMessage());
        }
    }
}
