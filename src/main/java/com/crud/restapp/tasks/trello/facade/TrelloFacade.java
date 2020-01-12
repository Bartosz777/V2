package com.crud.restapp.tasks.trello.facade;

import com.crud.restapp.tasks.domain.*;
import com.crud.restapp.tasks.mapper.TrelloMapper;
import com.crud.restapp.tasks.service.TrelloService;
import com.crud.restapp.tasks.trello.validator.TrelloValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {
    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoard() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.maptoBoardDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
