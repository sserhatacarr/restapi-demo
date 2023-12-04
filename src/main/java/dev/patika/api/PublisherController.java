package dev.patika.api;

import dev.patika.business.abstracts.IPublisherService;
import dev.patika.core.config.modelMapper.IModelMapperService;
import dev.patika.dto.request.publisher.PublisherSaveRequest;
import dev.patika.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.dto.response.publisher.PublisherResponse;
import dev.patika.entity.Publisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public PublisherResponse save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher publisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        publisherService.save(publisher);
        return this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PublisherResponse get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        return this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PublisherResponse update(@PathVariable("id") int id, @Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher publisher = this.publisherService.get(id);
        this.modelMapper.forRequest().map(publisherUpdateRequest, publisher);
        publisherService.update(publisher);
        return this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        boolean isDeleted = publisherService.delete(id);
        if (isDeleted) {
            return String.format("Publisher with ID %d deleted", id);
        } else {
            return String.format("No Publisher with ID %d found", id);
        }
    }
}
