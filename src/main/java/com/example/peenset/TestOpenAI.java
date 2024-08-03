package com.example.peenset;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

import java.util.List;

public class TestOpenAI {
    public static void main(String[] args) {
        // pour pouvoir le faire il faut avoir un compte sur OpenAi
        //https://platform.openai.com/playground/chat?models=gpt-3.5-turbo
        OpenAiApi openAiApi = new OpenAiApi("sk-proj-f3VyaiplsFL7WeHMIWgXT3BlbkFJpldTKLdbca3BoAlSnrSR");
        OpenAiChatModel openAiChatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder()
                .withMaxTokens(3000)
                .withTemperature(0F)
                .withModel("gpt-3.5-turbo-16k")
                .build());

        SystemMessage systemMessage = new SystemMessage("""
                Donne moi l'équipe qui a gagné la coupe du monde de football de l'année qui sera fournie." +
                Je veux le résultat  au format json sous la forme suivante : 
                - Nom de l'équipe
                - la liste des joueurs
                - le pays organisateur
                """);

        UserMessage userMessage = new UserMessage("Je veux le résultat pour l'année 2022");

        Prompt zeriShPrompt = new Prompt(List.of(systemMessage, userMessage));
        ChatResponse chatResponse = openAiChatModel.call(zeriShPrompt);

        System.out.println(chatResponse.getResult().getOutput().getContent());
    }
}
