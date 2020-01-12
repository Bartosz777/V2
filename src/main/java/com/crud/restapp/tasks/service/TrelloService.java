package com.crud.restapp.tasks.service;

import com.crud.restapp.tasks.config.AdminConfig;
import com.crud.restapp.tasks.domain.CreatedTrelloCardDto;
import com.crud.restapp.tasks.domain.Mail;
import com.crud.restapp.tasks.domain.TrelloBoardDto;
import com.crud.restapp.tasks.domain.TrelloCardDto;
import com.crud.restapp.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: New Trello card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "New Card: " + trelloCardDto.getName() + " has been created on your Trello account")));
        return newCard;
    }
}

