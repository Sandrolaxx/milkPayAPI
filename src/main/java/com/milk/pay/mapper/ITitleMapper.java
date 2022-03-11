package com.milk.pay.mapper;

import com.milk.pay.entities.Title;
import com.milk.pay.title.dto.TitleDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ITitleMapper {

    public TitleDto titleToTitleDto(Title entity);

    public Title titleDtoToEntity(TitleDto resDto);

}
