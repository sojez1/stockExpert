package com.jpstechno.stock_back.controlleurTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.jpstechno.stock_back.controlleurs.ProductControl;
import com.jpstechno.stock_back.dto.CategorieDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.serviceImplementation.CategorieImplementation;
import com.jpstechno.stock_back.serviceImplementation.TranslatorServices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ProductControl.class)
public class ProduitControlleurTest {

        @MockitoBean
        private CategorieImplementation categorieImpl;

        @MockitoBean
        private TranslatorServices translator;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objMapper;

        @Test
        @DisplayName("Enregistrer une categorie - normal data")
        public void saveContegorieControlleurtest() throws Exception {

                // arrange
                CategorieDto cateDto = new CategorieDto("Materiel Informatique",
                                "Materiel et equipement elctronique utilisee en informatique");

                Categories categorie = Categories.builder()
                                .name(cateDto.name()).description(cateDto.description())
                                .build();

                when(categorieImpl.saveCategorie(any(CategorieDto.class))).thenReturn(categorie);

                // act + assert
                mockMvc.perform(post("/produits/save/categorie")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objMapper.writeValueAsString(cateDto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.name").value(cateDto.name()))
                                .andExpect(jsonPath("$.description").value(categorie.getDescription()));
        }

        @Test
        @DisplayName("enregistrer une categorie - categorie name should fail")
        public void saveContegorieControlleurtest_validationFail() throws Exception {

                // arrange
                CategorieDto cateDto = new CategorieDto("",
                                "Materiel et equipement elctronique utilisee en informatique");

                Categories categorie = Categories.builder()
                                .name(cateDto.name()).description(cateDto.description())
                                .build();

                when(categorieImpl.saveCategorie(any(CategorieDto.class))).thenReturn(categorie);

                // act + assert
                mockMvc.perform(post("/produits/save/categorie")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objMapper.writeValueAsString(cateDto)))
                                .andExpect(status().isBadRequest());

        }

}
