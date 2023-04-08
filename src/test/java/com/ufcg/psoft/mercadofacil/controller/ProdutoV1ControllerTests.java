package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Produtos")
public class ProdutoV1ControllerTests {
    @Autowired
    MockMvc driver;

    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Produto produto;
    Produto produto2;
    Produto produto3;
    Produto produto4;
    Produto produto5;
    Produto produto6;
    Produto produto7;
    Produto produto8;
    Produto produto9;
    Produto produto10;

    @BeforeEach
    void setup() {
        produto = produtoRepository.find(10L);
        produto2 = produtoRepository.find(20L);
        produto3 = produtoRepository.find(30L);
        produto4 = produtoRepository.find(40L);
        produto5 = produtoRepository.find(50L);
        produto6 = produtoRepository.find(60L);
        produto7 = produtoRepository.find(70L);
        produto8 = produtoRepository.find(80L);
        produto9 = produtoRepository.find(90L);
        produto10 = produtoRepository.find(100L);
    }

    @AfterEach
    void tearDown() {
        produto = null;
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de campos obrigatórios")
    class ProdutoValidacaoCamposObrigatorios {

        @Test
        @DisplayName("Quando alteramos o nome do produto com dados válidos")
        void quandoAlteramosNomeDoProdutoValido() throws Exception {
            // Arrangeee
            produto.setNome("Produto Dez Alterado");

            // Act
            String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            assertEquals("Produto Dez Alterado", resultado.getNome());
            assertEquals(200.00, produto.getPreco());
            assertEquals("Empresa Dez", produto.getFabricante());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da regra sobre o preço")
    class ProdutoValidacaoRegrasDoPreco {

        @Test
        @DisplayName("Quando o preço é válido.")
        void quandoPrecoEhValido() throws Exception {
            produto.setPreco(450.00);
            // Act
            String responseJsonString = driver.perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            assertEquals(450.00, resultado.getPreco());
        }

        @Test
        @DisplayName("Quando o preço é igual a zero.")
        void quandoPrecoEhIgualAZero() {
            produto2.setPreco(0.0);
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto2)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);

        }

        @Test
        @DisplayName("Quando o preço é menor que zero.")
        void quandoPrecoEhMenorQueZero() {
            produto3.setPreco(-10.0);
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto3.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto3)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);

        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da validação do código de barras")
    class ProdutoValidacaoCodigoDeBarras {

        @Test
        @DisplayName("Quando o código de barras é inválido.")
        void quandoCodigoBarrasEhInvalido() {
            produto4.setCodigoBarra("7899137500104");
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto4.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto4)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);

        }

        @Test
        @DisplayName("Quando o código de barras é válido.")
        void quandoCodigoBarrasEhValido() throws Exception {
            produto5.setCodigoBarra("7899137500100");
            String responseJsonString = driver.perform(put("/v1/produtos/" + produto5.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto5)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

            assertEquals("7899137500100", resultado.getCodigoBarra());

        }

        @Test
        @DisplayName("Quando o código de barras é vazio.")
        void quandoCodigoBarrasEhVazio() throws Exception {
            produto10.setCodigoBarra("");
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto10.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto10)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);

        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da regra sobre o nome do produto")
    class ProdutoValidacaoRegrasDoNome {
        @Test
        @DisplayName("Quando o nome do produto é null")
        void quandoNomeEhNull() throws Exception {
            produto6.setNome(null);
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto6.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto6)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);

        }

        @Test
        @DisplayName("Quando o nome do produto pe vazio")
        void quandoNomeEhVazio() throws Exception {
            produto7.setNome("");
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto7.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto7)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);
        }

    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da regra sobre o nome do fabricante")
    class ProdutoValidacaoRegrasDoFabricante {
        @Test
        @DisplayName("Quando o fabricante é null")
        void quandoFabricanteEhNull() throws Exception {
            produto8.setFabricante(null);
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto8.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto8)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);
        }

        @Test
        @DisplayName("Quando o fabricante é vazio")
        void quandoFabricanteEhVazio() throws Exception {
            produto9.setFabricante("");
            boolean pegouProduto = false;
            try {
                String responseJsonString = driver.perform(put("/v1/produtos/" + produto9.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto9)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
                pegouProduto = true;
            }
            catch (Exception e) {
            }

            assertEquals(false, pegouProduto);
        }
    }
}
