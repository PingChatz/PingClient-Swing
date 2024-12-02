package data_access;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Message;
import use_case.chat_refresh.ChatRefreshThreadDataAccessInterface;
import use_case.send_message.SendMessageMessageDataAccessInterface;

/**
 * The DAO for message data.
 */
public class MessageDataAccessObject implements
        SendMessageMessageDataAccessInterface,
        ChatRefreshThreadDataAccessInterface
{
    private final PingBackend backend;

    public MessageDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    @Override
    public List<Message> getMessages(Long threadID) throws Exception
    {
        JSONObject response = backend.getMessages(threadID.intValue());
        JSONArray messagesArray = response.getJSONArray("messages");

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < messagesArray.length(); i++)
        {
            JSONObject messageJson = messagesArray.getJSONObject(i);
            String sender = messageJson.getString("sender");
            String content = messageJson.getString("content");
            String timestamp = formatTimestamp(messageJson.getString("timestamp"));
            Message message = new Message(content, sender, timestamp);
            messages.add(message);
        }
        return messages;
    }


    @Override
    public Message save(Message message, Long threadID) throws Exception
    {
        // Send a request to the server to save the new message
        JSONObject serverOutput = backend.sendMessage(
                Math.toIntExact(threadID), "text", message.getContent());

        // If it's the error JSON object:
        if (serverOutput.has("error"))
        {
            // Throw an exception with the server error message
            String errorMessage = serverOutput.getString("message");
            throw new IOException("Server Error: " + errorMessage);
        }

        // Parse the successful response and create a new Message object
        if (serverOutput.has("sender") && serverOutput.has("messageId")
                && serverOutput.has("content") && serverOutput.has("timestamp"))
        {
            String sender = serverOutput.getString("sender");
            String content = serverOutput.getString("content");
            String timestamp = formatTimestamp(serverOutput.getString("timestamp"));

            return new Message(content, sender, timestamp);
        }

        // If the response doesn't match either expected format, throw an exception
        throw new IOException("Unexpected server response: " + serverOutput);
    }

    private static String formatTimestamp(String isoTimestamp)
    {
        ZoneId torontoTimeZone = ZoneId.of("America/Toronto");
        Instant instant = Instant.parse(isoTimestamp);
        ZonedDateTime torontoTime = instant.atZone(torontoTimeZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | h:mm a");
        return torontoTime.format(formatter);
    }
}
