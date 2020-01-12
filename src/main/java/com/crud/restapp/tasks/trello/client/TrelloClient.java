package com.crud.restapp.tasks.trello.client;

import com.crud.restapp.tasks.domain.CreatedTrelloCardDto;
import com.crud.restapp.tasks.domain.TrelloBoardDto;
import com.crud.restapp.tasks.domain.TrelloCardDto;
import com.crud.restapp.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class TrelloClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
    @Autowired
    TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(urlBuilder(), TrelloBoardDto[].class);
        Optional<TrelloBoardDto[]> optional = Optional.ofNullable(boardsResponse);

        try {
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(urlBuilder(), TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new TrelloBoardDto[0]));
        } catch(RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI urlBuilder() {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndPoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndPoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);
    }
}

