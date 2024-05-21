package pl.leverx.ms.user.crud.configuration;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.leverx.ms.user.crud.converter.PersonDTOToPersonConverter;
import pl.leverx.ms.user.crud.converter.PersonToPersonDTOConverter;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfiguration {

    private final PersonDTOToPersonConverter personDTOToPersonConverter;
    private final PersonToPersonDTOConverter personToPersonDTOConverter;

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(personDTOToPersonConverter);
        modelMapper.addConverter(personToPersonDTOConverter);
        return modelMapper;
    }
}
