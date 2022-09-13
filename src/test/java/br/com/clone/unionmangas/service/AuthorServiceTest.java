package br.com.clone.unionmangas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.clone.unionmangas.mock.MockAuthor;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.repository.AuthorRepository;

@Tags({ @Tag("unit"), @Tag("author") })
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    private final static Long id = 1L;
    private MockAuthor input;

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        input = new MockAuthor();
    }

    @Test
    void testFindById() {
        Author entity = input.mockAuthor(id);
        when(authorRepository.findById(id)).thenReturn(Optional.of(entity));
        var result = authorService.findById(id);

        assertNotNull(result);
        assertNotNull(result.getIdAuthor());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</author/1>;rel=\"self\"]"));

        assertEquals(id, result.getIdAuthor());
        assertEquals("name" + id, result.getName());
        assertEquals("pseudonym" + id, result.getPseudonym());

    }


}
