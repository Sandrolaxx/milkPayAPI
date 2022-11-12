package com.aktie.aktiepay.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.aktie.aktiepay.dto.title.ListTitleDto;
import com.aktie.aktiepay.dto.title.TitleCreateDto;
import com.aktie.aktiepay.dto.title.TitleDto;
import com.aktie.aktiepay.dto.title.TotalizersDto;
import com.aktie.aktiepay.entities.User;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.entities.enums.EnumPaymentType;
import com.aktie.aktiepay.mapper.ITitleMapper;
import com.aktie.aktiepay.repository.TitleRepository;
import com.aktie.aktiepay.utils.DateUtil;
import com.aktie.aktiepay.utils.EnumUtil;
import com.aktie.aktiepay.utils.ListUtil;
import com.aktie.aktiepay.utils.AktiePayException;
import com.aktie.aktiepay.utils.StringUtil;
import com.aktie.aktiepay.utils.Utils;

@ApplicationScoped
public class TitleService {

    @Inject
    ITitleMapper titleMapper;

    @Inject
    TitleRepository repository;

    public ListTitleDto findAll(String userId, Boolean liquidated, Integer pageIndex, Integer pageSize,
            String offset, String limit) {

        var params = new HashMap<String, Object>();
        var listTitleDto = new ListTitleDto();

        params.put("userId", UUID.fromString(userId));

        if (liquidated != null) {
            params.put("liquidated", liquidated);
        }

        if (!StringUtil.isNullOrEmpty(offset)
                && !StringUtil.isNullOrEmpty(limit)) {
            try {
                params.put("offset", DateUtil.DDMMYYYYToLocalDate(offset));
                params.put("limit", DateUtil.DDMMYYYYToLocalDate(limit));
            } catch (Exception e) {
                throw new AktiePayException(EnumErrorCode.DATA_CONSULTA_INVALIDA);
            }
        }

        var userTitles = repository.findByUserIdBetwenDates(params, pageIndex, pageSize);
        var results = userTitles.stream()
                .map(t -> titleMapper.titleToTitleDto(t))
                .map(tDto -> getFinalValue(tDto))
                .collect(Collectors.toList());

        listTitleDto.setPage(pageIndex);
        listTitleDto.setResults(results);
        listTitleDto.setAllResultsSize(repository.getQueryResultsLenght(params));

        return listTitleDto;
    }

    public TotalizersDto fetchTotalizers(String userId) {

        var listAllTitles = repository.findAllByUserId(UUID.fromString(userId));
        var totalizers = new TotalizersDto();

        if (ListUtil.isNullOrEmpty(listAllTitles)) {
            return totalizers;
        }

        totalizers.setAmountReceived(Utils.getTotal(listAllTitles, true));
        totalizers.setAmountToReceive(Utils.getTotalNextDays(listAllTitles, 30));
        totalizers.setTitlesReceived(Utils.countTotal(listAllTitles, true).intValue());
        totalizers.setTitlesToReceive(Utils.countTotalNextDays(listAllTitles, 30).intValue());

        return totalizers;

    }

    @Transactional()
    public void persistTitle(TitleCreateDto newTitleDto) {
        var defaultDailyInterest = BigDecimal.valueOf(0.2D);

        if (newTitleDto.getDailyInterest() == null) {
            newTitleDto.setDailyInterest(defaultDailyInterest);
        }

        if (EnumUtil.isEquals(newTitleDto.getPaymentType(), EnumPaymentType.PIX)) {
            newTitleDto.setBarcode(null);
            newTitleDto.setDigitable(null);
        }

        var newTitle = titleMapper.titleDtoToEntity(newTitleDto);
        var user = User.findUserByDocument(newTitleDto.getUserDocument());

        if (user == null) {
            throw new AktiePayException(EnumErrorCode.ERRO_SALVAR_TIT_USUARIO_INVALIDO);
        }

        newTitle.setBalance(newTitleDto.getAmount());
        newTitle.setUser(user);

        newTitle.persistAndFlush();
    }

    private TitleDto getFinalValue(TitleDto titleDto) {
        var amount = titleDto.getAmount();
        var dueDate = LocalDate.parse(titleDto.getDueDate()).atStartOfDay();
        var diffDays = DateUtil.numberOfDaysBetweenDates(LocalDateTime.now(DateUtil.ZONE_ID), dueDate);
        var totalPercentInterest =  titleDto.getDailyInterest().multiply(BigDecimal.valueOf(diffDays));
        var interestValue = amount.multiply(totalPercentInterest).divide(BigDecimal.valueOf(100));
        
        titleDto.setFinalAmount(amount.subtract(interestValue).setScale(2, RoundingMode.HALF_UP));

        return titleDto;
    }

}