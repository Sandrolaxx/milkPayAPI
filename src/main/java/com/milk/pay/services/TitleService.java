package com.milk.pay.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.mapper.ITitleMapper;

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

}