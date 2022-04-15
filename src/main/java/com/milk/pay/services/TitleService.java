package com.milk.pay.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.ITitleMapper;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.ListUtil;
import com.milk.pay.utils.MilkPayException;

@ApplicationScoped
public class TitleService {

    @Inject
    ITitleMapper titleMapper;

    public List<TitleDto> findAll(String userId) {
        var userTitles = Title.listByUserId(UUID.fromString(userId));

        return userTitles.stream()
                       .map(p -> titleMapper.titleToTitleDto(p))
                       .collect(Collectors.toList());

    }

    @Transactional()
    public void persistTitle(TitleCreateDto newTitleDto) {
        var defaultDailyFine = 0.2D;

        if (newTitleDto.getDailyFine() == null) {
            newTitleDto.setDailyFine(defaultDailyFine);
        }

        var newTitle = titleMapper.titleDtoToEntity(newTitleDto);
        var user = User.findUserByDocument(newTitleDto.getUserDocument());

        if (user == null) {
            throw new MilkPayException(EnumErrorCode.ERRO_SALVAR_TIT_USUARIO_INVALIDO);
        }

        newTitle.setBalance(newTitleDto.getAmount());
        newTitle.setUser(user);

        newTitle.persistAndFlush();
    }

    @Transactional
    public void finishTitle(Payment payment, Integer titleId) {
        var title = Title.findById(titleId);

        if (payment.getAmount() == title.getBalance()) {
            title.setBalance(0.0d);
            title.setLiquidated(true);
        } else {
            title.setBalance(title.getBalance() - payment.getAmount());
        }

        if (ListUtil.isNullOrEmpty(title.getListPayment())) {
            title.setListPayment(List.of(payment));
        } else {
            title.getListPayment().add(payment);
        }

        title.persistAndFlush();
    }

    public Double resolverAmount(Double total, Date dueDate, Double dailyFine) {
        var numberOfDays = DateUtil.numberOfDaysBetweenDates(new Date(), dueDate);

        if (numberOfDays == 0) {
            return total;
        }
        var totalWithFine = total - (((numberOfDays * dailyFine) * 100) / total);

        return totalWithFine;
    }

}