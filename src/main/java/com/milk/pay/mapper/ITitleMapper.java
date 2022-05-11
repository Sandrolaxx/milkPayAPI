package com.milk.pay.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        @Mapping(target = "dueDate", expression = "java(parseLocalDate(dto.getDueDate()))"),
        @Mapping(target = "inclusionDate", expression = "java(parseDate(dto.getInclusionDate()))")
    })
    public Title titleDtoToEntity(TitleCreateDto dto);

    default LocalDateTime parseDate(String strDate) {
        return DateUtil.DDMMYYYYHHMMSSToLocalDateTime(strDate);
    }

    default LocalDate parseLocalDate(String strDate) {
        return DateUtil.DDMMYYYYToLocalDate(strDate);
    }

}
