package com.antonyni.GreatWallChinese.domain.food.controllers;

import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodCreationException;
import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.food.services.FoodService;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.core.Is.is;

import static com.antonyni.GreatWallChinese.domain.BaseControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoodController.class)
@ExtendWith(SpringExtension.class)
public class FoodControllerTests {
    @MockBean
    private FoodService foodService;

    @Autowired
    MockMvc mockMvc;

    private Food mockFoodInput;

    private Food mockFoodResponse;

    private List<FoodOptions> foodOptionsList;

    private UUID randomId;

    @BeforeEach
    public void setUp() {
        foodOptionsList = new ArrayList<>();

        UUID id1 = UUID.randomUUID();

        randomId = UUID.randomUUID();
        mockFoodInput = new Food("name","SR",10.2f,11.2f,true,"apps",foodOptionsList);
        mockFoodInput.setId(randomId);
        mockFoodResponse = new Food("name","SR",10.2f,11.2f,true,"apps",foodOptionsList);
        mockFoodResponse.setId(randomId);
    }

    @Test
    @DisplayName("Food Post: /api/v1/FoodRequests - success")
    public void createFoodRequestSuccess() throws Exception {
        BDDMockito.doReturn(mockFoodResponse).when(foodService).create(mockFoodInput);

        mockMvc.perform(post("/api/v1/FoodRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockFoodInput)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));

    }

    @Test
    @DisplayName("Food Post: /api/v1/FoodRequests - food exists")
    public void createFoodRequestFail() throws Exception {
        BDDMockito.doThrow(new FoodCreationException("Food already exists")).when(foodService).create(mockFoodInput);

        mockMvc.perform(post("/api/v1/FoodRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockFoodInput)))

                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Food Get: /api/v1/FoodRequests - foods exist")
    public void getAllFoods() throws Exception {
        List<Food> foods = new ArrayList<>();
        foods.add(mockFoodInput);
        BDDMockito.doReturn(foods).when(foodService).getAll();

        mockMvc.perform(get("/api/v1/FoodRequests"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(randomId.toString())))
                .andExpect(status().isOk());
    }

}
