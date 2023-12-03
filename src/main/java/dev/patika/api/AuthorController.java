package dev.patika.api;

import dev.patika.business.abstracts.IAuthorService;
import dev.patika.core.config.modelMapper.IModelMapperService;
import dev.patika.dto.request.author.AuthorSaveRequest;
import dev.patika.dto.response.author.AuthorResponse;
import dev.patika.entity.Author;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthorResponse save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author author = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        authorService.save(author);
        return this.modelMapper.forResponse().map(author, AuthorResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        return this.modelMapper.forResponse().map(author, AuthorResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse update(@PathVariable("id") int id, @Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author author = this.authorService.get(id);
        this.modelMapper.forRequest().map(authorSaveRequest, author);
        authorService.update(author);
        return this.modelMapper.forResponse().map(author, AuthorResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        boolean isDeleted = authorService.delete(id);
        if (isDeleted) {
            return String.format("Author with ID %d deleted", id);
        } else {
            return String.format("No Author with ID %d found", id);
        }
    }
}
