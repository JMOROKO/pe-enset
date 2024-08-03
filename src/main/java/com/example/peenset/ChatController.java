package com.example.peenset;

import com.example.peenset.model.Team;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {
    //permet de communiquer avec n'importe qu'elle LLM
    private ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE) // Pour obtenir le retour au format HTML produces = MediaType.TEXT_PLAIN_VALUE
    public String chat(String question){
        String systemMessage = """
                Donne moi l'équipe qui a gagné la coupe du monde de football de l'année qui sera fournie.
                Je veux le résultat au format json sous la forme suivante : 
                - Nom de l'équipe
                - la liste des joueurs
                - le pays organisateur
                """;
        return chatClient.prompt()
                .system(systemMessage)
                .user(question)
                .call().content();
    }
    @GetMapping(value = "/ballon-or", produces = MediaType.TEXT_PLAIN_VALUE) // Pour obtenir le retour au format HTML produces = MediaType.TEXT_PLAIN_VALUE
    public String ballonOr(String question){
        String systemMessage = """
                Donne moi le nombre de ballon d'or obtenu par un joueur à partir du nom du joueur qui qui sera fournie.
                Je veux le résultat au format json sous la forme suivante : 
                - Nom du joueur
                - Nombre de ballon d'or
                - Année d'obtention
                """;
        return chatClient.prompt()
                .system(systemMessage)
                .user(question)
                .call().content();
    }

    // pour que le retour soit fait au format json en utilisant la class Team et on attend une année en entrée
    @GetMapping(value = "/chat2", produces = MediaType.TEXT_PLAIN_VALUE) // Pour obtenir le retour au format HTML produces = MediaType.TEXT_PLAIN_VALUE
    public Team chat2(String question){
        String systemMessage = """
                Donne moi l'équipe qui a gagné la coupe du monde de football de l'année qui sera fournie.
                """;
        return chatClient.prompt()
                .system(systemMessage)
                .user(question)
                .call()
                .entity(Team.class);
    }
    // pour retourner une liste de réponses obtenue à partir du system Message
    @GetMapping(value = "/chat3")
    public List<Team> chat3(){
        String systemMessage = """
                Donne moi les noms des équipes qui ont gagné les quatres dernières coupe du monde de football de l'année qui sera fournie.
                """;
        return chatClient.prompt()
                .system(systemMessage)
                .call()
                .entity(new ParameterizedTypeReference<List<Team>>() {});
    }
    @GetMapping(value = "/test")
    public String test(){
        return "je suis le test";
    }
}
