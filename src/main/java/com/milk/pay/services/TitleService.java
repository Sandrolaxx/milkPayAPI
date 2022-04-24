package com.milk.pay.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.dto.title.TotalizersDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.ITitleMapper;
import com.milk.pay.repository.TitleRepository;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.ListUtil;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.StringUtil;
import com.milk.pay.utils.Utils;

@ApplicationScoped
public class TitleService {

    @Inject
    ITitleMapper titleMapper;

    @Inject
    TitleRepository repository;

    public List<TitleDto> findAll(String userId, boolean liquidated, String offset, String limit) {

        var params = new HashMap<String, Object>();
        
        params.put("userId", UUID.fromString(userId));
        params.put("liquidated", liquidated);

        if (!StringUtil.isNullOrEmpty(offset)
                && !StringUtil.isNullOrEmpty(limit)) {
            params.put("offset", DateUtil.DDMMYYYYToLocalDate(offset));
            params.put("limit", DateUtil.DDMMYYYYToLocalDate(limit));
        }

        var userTitles = repository.findByUserIdBetwenDates(params);

        return userTitles.stream()
                       .map(p -> titleMapper.titleToTitleDto(p))
                       .collect(Collectors.toList());

    }

    public TotalizersDto fetchTotalizers(String userId) {

        var listAllTitles = repository.findAllByUserId(UUID.fromString(userId));
        var totalizers = new TotalizersDto();

        if (ListUtil.isNullOrEmpty(listAllTitles)) {
            return totalizers;
        }
        
        totalizers.setAmountReceived(Utils.getTotal(listAllTitles, true).toString());
        totalizers.setAmountToReceive(Utils.getTotalNextDays(listAllTitles, 30).toString());
        totalizers.setTitlesReceived(Utils.countTotal(listAllTitles, true).intValue());
        totalizers.setTitlesToReceive(Utils.countTotalNextDays(listAllTitles, 30).intValue());

        return totalizers;

    }

    @Transactional()
    public void persistTitle(TitleCreateDto newTitleDto) {
        var defaultDailyInterest = 0.2D;

        if (newTitleDto.getDailyInterest() == null) {
            newTitleDto.setDailyInterest(defaultDailyInterest);
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
    public void finishTitle(ReceiptInfo receipt, Integer titleId) {
        var title = Title.findById(titleId);
        var payment = Payment.findById(receipt.getTxId());

        if (payment.getAmount().equals(title.getBalance())) {
            title.setBalance(0.0d);
            title.setLiquidated(true);
            title.setPaidAt(LocalDateTime.now());
        } else {
            title.setBalance(title.getBalance() - payment.getAmount());
        }

        payment.setReceipt(receipt);
        payment.setReceiptImage(receipt.getReceiptResume());

        if (ListUtil.isNullOrEmpty(title.getListPayment())) {
            title.setListPayment(List.of(payment));
        } else {
            title.getListPayment().add(payment);
        }

        title.persistAndFlush();
    }

}