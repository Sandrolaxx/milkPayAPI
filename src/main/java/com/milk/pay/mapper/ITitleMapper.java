package com.milk.pay.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.entities.enums.EnumPaymentType;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.EncryptUtil;
import com.milk.pay.utils.EnumUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
            return EncryptUtil.textDecrypt(user.getPixKey(), user.getSecret());
        }

        return null;
    }

}
