package com.kaminsky.walletproject;

import com.kaminsky.walletproject.entity.Wallet;
import com.kaminsky.walletproject.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WalletControllerTest {
    private static final String GET_URL = "/api/v1/wallets/87374f77-7e87-46fa-9ae8-a67cfc9f3ab9";
    private static final String WRONG_GET_URL = "/api/v1/wallets/87374f77-7e87-46fa-9ae8-a67cfc9f3ab8";
    private static final String POST_URL = "/api/v1/wallet";
    private static final String JSON = "{" +
            "\"walletId\": \"87374f77-7e87-46fa-9ae8-a67cfc9f3ab9\",\n" +
            "    \"amount\": 1500,\n" +
            "    \"operationType\": \"DEPOSIT\"" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletRepository walletRepository;

    @BeforeEach
    void setup() {
        Wallet wallet = new Wallet();
        wallet.setWalletId(UUID.fromString("87374f77-7e87-46fa-9ae8-a67cfc9f3ab9"));
        wallet.setAmount(BigDecimal.valueOf(5000));
        walletRepository.save(wallet);
    }

    @Test
    void getAmountSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.walletId").value("87374f77-7e87-46fa-9ae8-a67cfc9f3ab9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(5000));
    }

    @Test
    void getAmountNoWallet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(WRONG_GET_URL))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string("Кошелек не найден"));
    }

    @Test
    void postSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(POST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(BigDecimal.valueOf(6500), walletRepository.getReferenceById(UUID.fromString("87374f77-7e87-46fa-9ae8-a67cfc9f3ab9")).getAmount());
    }

    @Test
    void postInvalidJSON() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(POST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" "))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string("Невалидный JSON"));
    }

    }



