package com.milk.pay.mapper;

import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;

import org.mapstruct.Mapper;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface ITitleMapper {

    public TitleDto titleToTitleDto(Title entity);

    public Title titleDtoToEntity(TitleDto resDto);

}
