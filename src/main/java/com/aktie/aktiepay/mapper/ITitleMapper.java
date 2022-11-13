package com.aktie.aktiepay.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.aktie.aktiepay.dto.title.TitleCreateDto;
import com.aktie.aktiepay.dto.title.TitleDto;
import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.User;
import com.aktie.aktiepay.entities.enums.EnumPaymentType;
import com.aktie.aktiepay.utils.DateUtil;
import com.aktie.aktiepay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface ITitleMapper {

    @Mappings({
        @Mapping(target = "txId", source = "payment.id"),
        @Mapping(target = "pixKey", expression = "java(getUserPixKey(entity.getUser(), entity.getPaymentType()))")
    })
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

    default String getUserPixKey(User user, EnumPaymentType payType) {
        if (EnumUtil.isEquals(payType, EnumPaymentType.PIX)) {
            return user.getPixKey();
        }

        return null;
    }

}
