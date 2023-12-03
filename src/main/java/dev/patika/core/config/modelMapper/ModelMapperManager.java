package dev.patika.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements IModelMapperService{
    private final ModelMapper modelMapper;
@Autowired
    public ModelMapperManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ModelMapper forRequest() {
                this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STANDARD);
                return this.modelMapper;
    }

    public ModelMapper forResponse() {
      this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.LOOSE );
        return this.modelMapper;
    }
}
