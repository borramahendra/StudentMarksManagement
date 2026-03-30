package com.rolvatech.studentmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rolvatech.studentmanagement.dto.MarksRequestdto;
import com.rolvatech.studentmanagement.model.MarksModel;

@Configuration
public class ModelMapperConfig {
	@Bean
	ModelMapper getModelMapper() {
		 ModelMapper modelMapper = new ModelMapper();
		 
		 modelMapper.getConfiguration().setAmbiguityIgnored(true);
		 
		 modelMapper.typeMap(MarksRequestdto.class, MarksModel.class)
	    .addMappings(mapper -> mapper.skip(MarksModel::setMarks_id));
		return modelMapper;
	}

}
