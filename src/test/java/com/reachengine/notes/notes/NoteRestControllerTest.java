package com.reachengine.notes.notes;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.reachengine.notes.common.LocalDateTimeProvider;
import com.reachengine.notes.owners.OwnerRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(NoteRestController.class)
public class NoteRestControllerTest {

    @Inject
    MockMvc mockMvc;

    @MockBean
    NoteRepo noteRepo;

    @MockBean
    LocalDateTimeProvider localDateTimeProvider;

    @MockBean
    OwnerRepo ownerRepo;

    @Test
    public void test_index() throws Exception {

        Mockito.when(

            noteRepo.findAll(Mockito.any(NoteCriteria.class))
        )
        .thenReturn(

            Arrays.asList(
               new Note(new NoteId(1),"Pick up milk!!!"                , LocalDateTime.of(2019,6,22,0,0)),
               new Note(new NoteId(2),"Ask Larry about the TPS reports", LocalDateTime.of(2019,6,22,0,0))
            )
        );


        mockMvc.perform(
            get("/api/notes")
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].body").value("Pick up milk!!!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id"  ).value("1"));
    }


    @Test
    public void test_create_is_ok() throws Exception {

        Mockito.when(

            noteRepo.insert(Mockito.any(Note.class))
        )
        .thenReturn(new NoteId(1));


        mockMvc.perform(
            post("/api/notes")
            .content(asJsonString(new NoteDto(0,"Pick up milk!!!",null)))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Pick up milk!!!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id"  ).value("1"));
    }

    @Test
    public void test_create_is_failure_with_empty_body() throws Exception {

        Mockito.when(

        noteRepo.insert(Mockito.any(Note.class))
        )
        .thenReturn(new NoteId(1));


        mockMvc.perform(
            post("/api/notes")
            .content(asJsonString(new NoteDto(0,"",null))
            )
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }


    @Test
    public void test_create_is_failure_with_non_null_date() throws Exception {

        Mockito.when(

        noteRepo.insert(Mockito.any(Note.class))
        )
        .thenReturn(new NoteId(1));


        mockMvc.perform(
            post("/api/notes")
            .content(asJsonString(new NoteDto(0,"Pick up milk!!!",LocalDateTime.of(2019,6,22,0,0))))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }


    @Test
    public void test_update_is_ok() throws Exception {

        Mockito.when(

            localDateTimeProvider.now()
        )
        .thenReturn(

            LocalDateTime.of(2019,6,22,0,0)
        );


        Mockito.when(

            noteRepo.findById(Mockito.any(NoteId.class))
        )
        .thenReturn(

            Optional.of(new Note(new NoteId(1),"Pick up milk!!!",LocalDateTime.of(2019,6,22,0,0)))
        );

        Mockito
            .doNothing()
            .when(
                noteRepo
            )
            .update(Mockito.any(Note.class));


        mockMvc.perform(
            put("/api/notes")
                .content(asJsonString(new NoteDto(1,"Pick up milk and cookies!!!",null)))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Pick up milk and cookies!!!"));
    }


    @Test
    public void test_update_is_failure_with_empty_body() throws Exception {

        Mockito.when(

            localDateTimeProvider.now()
        )
        .thenReturn(

            LocalDateTime.of(2019,6,22,0,0)
        );


        Mockito.when(

            noteRepo.findById(Mockito.any(NoteId.class))
        )
        .thenReturn(

            Optional.of(new Note(new NoteId(1),"Pick up milk!!!",LocalDateTime.of(2019,6,22,0,0)))
        );

        Mockito
        .doNothing()
        .when(
            noteRepo
        )
        .update(Mockito.any(Note.class));


        mockMvc.perform(
        put("/api/notes")
            .content(asJsonString(new NoteDto(1,"",null)))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    public void test_update_is_failure_with_provided_date() throws Exception {

        Mockito.when(

            localDateTimeProvider.now()
        )
        .thenReturn(

            LocalDateTime.of(2019,6,22,0,0)
        );


        Mockito.when(

            noteRepo.findById(Mockito.any(NoteId.class))
        )
        .thenReturn(

            Optional.of(new Note(new NoteId(1),"Pick up milk!!!",LocalDateTime.of(2019,6,22,0,0)))
        );

        Mockito
        .doNothing()
        .when(
            noteRepo
        )
        .update(Mockito.any(Note.class));


        mockMvc.perform(
            put("/api/notes")
            .content(asJsonString(new NoteDto(1,"",LocalDateTime.of(2018,1,1,0,0))))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }


    @Test
    public void test_delete_is_ok() throws Exception {

        Mockito
        .doNothing()
        .when(
            noteRepo
        )
        .delete(Mockito.any(NoteId.class));


        mockMvc.perform(
            delete("/api/notes/1")
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());

    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}