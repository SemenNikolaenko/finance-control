package com.money.demo.controllerTest;

import com.money.demo.model.Category;
import com.money.demo.service.impl.CategoryServiceImpl;
import com.money.demo.service.impl.EntryServiceImpl;
import com.money.demo.service.impl.SubcategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;
    @MockBean
    private EntryServiceImpl entryServiceImpl;
    @MockBean
    private SubcategoryServiceImpl subcategoryServiceImpl;


    @Test
    public void shouldReturnSimpleMessage() throws Exception {
        Category category = new Category("category1", "definition1");
        when(categoryService.getCategoryById(Mockito.any())).thenReturn(category);

        mockMvc.perform(get("/app"))
                .andExpect(status().isOk());


    }

}
