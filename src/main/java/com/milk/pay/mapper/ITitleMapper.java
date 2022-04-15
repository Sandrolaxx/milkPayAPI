package com.milk.pay.mapper;

import java.util.Date;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import com.milk.pay.utils.DateUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface ITitleMapper {

    public TitleDto titleToTitleDto(Title entity);

    @Mappings({
        @Mapping(target = "dueDate", expression = "java(parseDate(dto.getDueDate()))"),
        @Mapping(target = "inclusionDate", expression = "java(parseDate(dto.getInclusionDate()))")
    })
    public Title titleDtoToEntity(TitleCreateDto dto);

    default Date parseDate(String strDate) {
        return DateUtil.DDMMYYYYHHMMSSToDate(strDate);
    }

}
