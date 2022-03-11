package com.milk.pay.title.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.mapper.ITitleMapper;
import com.milk.pay.title.dto.TitleDto;
import com.milk.pay.utils.ListUtil;

@ApplicationScoped
public class TitleService {

    @Inject
    ITitleMapper titleMapper;

    public List<TitleDto> findAll(Integer companyId, Integer userId) {

        if (companyId != null) {
            var companyTitles = Title.listByCompanyId(companyId);

            if (ListUtil.isNotNullOrEmpty(companyTitles)) {
                return companyTitles.stream()
                    .map(p -> titleMapper.titleToTitleDto(p))
                    .collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }

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

}