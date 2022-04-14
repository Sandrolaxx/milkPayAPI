package com.milk.pay.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.mapper.ITitleMapper;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.ListUtil;

@ApplicationScoped
public class TitleService {

    @Inject
    ITitleMapper titleMapper;

    public List<TitleDto> findAll(Integer companyId, Integer userId) {

        if (companyId != null) {
        }

        var userTitles = Title.listByUserId(userId);

        return userTitles.stream()
                       .map(p -> titleMapper.titleToTitleDto(p))
                       .collect(Collectors.toList());

    }

    @Transactional()
    public void persistTitle(TitleDto dto) {
        var defaultDailyFine = 0.2D;

        if (dto.getDailyFine() == null) {
            dto.setDailyFine(defaultDailyFine);
        }

        dto.setBalance(dto.getAmount());

        var newTitle = titleMapper.titleDtoToEntity(dto);
        var user = User.findUserById(dto.getUserId());

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