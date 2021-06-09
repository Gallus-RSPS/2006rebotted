package com.rebotted.integrations.website;

import com.rebotted.game.players.Player;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class bubble {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void loadPlayerData(Player player, String key) throws IOException {
        URL urlForGetRequest = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/characters");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty(player.playerName, "Character"); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            //Read JSON response and print
            JSONObject userResponse = new JSONObject(response.toString());
            JSONObject returnedUser = new JSONObject(userResponse.getJSONObject("response").toString());
            JSONArray results = returnedUser.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                String delim = " ";
                String username = results.getJSONObject(i).getString("Username").toLowerCase();
                if (player.playerName.equalsIgnoreCase(username)) {

                    System.out.println("Loading player " + key);

                    switch(key) {
                        case "rights":
                            player.playerRights = results.getJSONObject(i).getInt(key);
                            break;
                        case "donationPoints":
                            player.playerRights = results.getJSONObject(i).getInt(key);
                            break;
                        case "last-ip":
                            player.lastConnectedFrom = results.getJSONObject(i).getString(key);
                            break;
                        case "Magic Points":
                            player.magePoints = results.getJSONObject(i).getInt(key);
                            break;
                        case "pc-points":
                            player.pcPoints = results.getJSONObject(i).getInt(key);
                            break;
                        case "Slayer Points":
                            player.slayerPoints = results.getJSONObject(i).getInt(key);
                            break;
                        case "Vote Points":
                            player.votePoints = results.getJSONObject(i).getInt(key);
                            break;
                        case "Can Talk":
                            player.canSpeak = results.getJSONObject(i).getBoolean(key);
                            break;
                        case "Black Marks":
                            player.blackMarks = results.getJSONObject(i).getInt(key);
                            break;
                        case "flagged":
                            player.accountFlagged = results.getJSONObject(i).getBoolean(key);
                            break;
                    }
                }
            }
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("FAILED TO LOAD PLAYER");
        }
    }

    public static boolean validateLogin(String user, String pass) throws IOException {
        Boolean validInfo = false;

        URL urlForGetRequest = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/characters");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty(user, pass); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            //Read JSON response and print
            JSONObject userResponse = new JSONObject(response.toString());
            JSONObject returnedUser = new JSONObject(userResponse.getJSONObject("response").toString());
            JSONArray results = returnedUser.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                String username = results.getJSONObject(i).getString("Username").toLowerCase();
                String password = results.getJSONObject(i).getString("Password").toLowerCase();
                if (user.equalsIgnoreCase(username)) {
                    System.out.println(username);
                    if (pass.equalsIgnoreCase(password)) {
                        System.out.println("MATCHES");
                        validInfo = true;
                    } else {
                        System.out.println("DOESN'T MATCH");
                        validInfo = false;
                    }
                }
            }

            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

        return validInfo;
    }

    public static double getXPRate() throws IOException {
        int xpRate = 1;

        URL urlForGetRequest = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/siteinfo");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("xprate", "XP Rate"); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            //Read JSON response and print
            JSONObject userResponse = new JSONObject(response.toString());
            JSONObject returnedUser = new JSONObject(userResponse.getJSONObject("response").toString());
            JSONArray results = returnedUser.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                String title = results.getJSONObject(i).getString("Title").toLowerCase();
                if (title.equalsIgnoreCase("XP Rate")) {
                    xpRate = results.getJSONObject(i).getInt("xprate");
                }
            }
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
        System.out.println("XP Rate: " + xpRate);
        return xpRate;
    }

    public static String getUID(String user) throws IOException {
        String UID = null;

        URL urlForGetRequest = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/characters");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty(user, "Character"); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            //Read JSON response and print
            JSONObject userResponse = new JSONObject(response.toString());
            JSONObject returnedUser = new JSONObject(userResponse.getJSONObject("response").toString());
            JSONArray results = returnedUser.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                String username = results.getJSONObject(i).getString("Username").toLowerCase();
                if (user.equalsIgnoreCase(username)) {
                    UID = results.getJSONObject(i).getString("_id");
                }
            }
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

        return UID;
    }

    public static int getMessageCount(Player player) throws IOException{
        int messageCount = 0;
        URL urlForGetRequest = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/characters");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty(player.playerName, "Character"); // set userId its a sample here
        int responseCode = conection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            //Read JSON response and print
            JSONObject userResponse = new JSONObject(response.toString());
            JSONObject returnedUser = new JSONObject(userResponse.getJSONObject("response").toString());
            JSONArray results = returnedUser.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                String username = results.getJSONObject(i).getString("Username").toLowerCase();
                if (player.playerName.equalsIgnoreCase(username)) {
                    Iterator<String> keys = results.getJSONObject(i).keys();

                    while(keys.hasNext()) {
                        String key = keys.next();
                        if (key.equalsIgnoreCase("messageunread")) {
                            messageCount = results.getJSONObject(i).getJSONArray("messageunread").length();
                        }
                    }
                }
            }
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

        return messageCount;
    }

    public static int calcTotalLevel(Player player) {
        int totalLevel = 0;

        for (int i = 0; i < 21; i++) {
            totalLevel += player.playerLevel[i];
        }
        System.out.println("Total Level: " + totalLevel);
        return totalLevel;
    }

    public static int calcTotalExp(Player player) {
        int totalExp = 0;

        for (int i = 0; i < 21; i++) {
            totalExp += player.playerXP[i];
        }
        System.out.println("Total Exp: " + totalExp);
        return totalExp;
    }

    public static void reportAbuse(Player reporter, String reported, String message, String rule) throws IOException {
        reporter.getPacketSender().sendMessage("SENDING REPORT ABUSE");
        URL url = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/Reports");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"Message\": \""+ message +"\",\n \"Reported\": \""+reported+"\",\n \"Reporter\": \""+ reporter.playerName +"\",\n \"Rule\": \""+ rule +"\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    public static void sendBugReport(Player player, String message) throws IOException {
        URL url = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/bugreports/" + player.uniqueId);
        OkHttpClient client = new OkHttpClient();

        String data = "{\n\"message\": " + message + ",\n" +
                "\"reporter\": " + getUID(player.playerName) + "\n}";

        RequestBody requestBody = RequestBody.create(JSON, data);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
        }
    }

    public static void updateUser(Player player) throws IOException {

        URL url = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/characters");
        OkHttpClient client = new OkHttpClient();

        String data = "{\n\"woodcutting level\": " + player.playerLevel[8] + ",\n" +
                "\"rights\": " + player.playerRights + ",\n" +
                //"\"donationPoints\": " + player.playerXP[7] + ",\n" +
                "\"last-ip\": " + player.lastConnectedFrom + ",\n" +
                "\"Magic Points\": " + player.magePoints + ",\n" +
                "\"pc-points\": " + player.pcPoints + ",\n" +
                "\"Slayer Points\": " + player.slayerPoints + ",\n" +
                "\"Vote Points\": " + player.votePoints + ",\n" +
                "\"Can Talk\": " + player.canSpeak + ",\n" +
                "\"flagged\": " + player.accountFlagged + ",\n" +
                "\"total level\": " + calcTotalLevel(player) + ",\n" +
                "\"total exp\": " + calcTotalExp(player) + ",\n" +
                "\"strength exp\": " + player.playerXP[2] + ",\n" +
                "\"smithing exp\": " + player.playerXP[13] + ",\n" +
                "\"runecrafting level\": " + player.playerLevel[20] + ",\n" +
                "\"range exp\": " + player.playerXP[4] + ",\n" +
                "\"prayer exp\": " + player.playerXP[5] + ",\n" +
                "\"mining level\": " + player.playerLevel[14] + ",\n" +
                "\"magic level\": " + player.playerLevel[6] + ",\n" +
                "\"hitpoints level\": " + player.playerLevel[3] + ",\n" +
                "\"fletching level\": " + player.playerLevel[9] + ",\n" +
                "\"fishing exp\": " + player.playerXP[10] + ",\n" +
                "\"farming level\": " + player.playerLevel[19] + ",\n" +
                "\"defence level\": " + player.playerLevel[1] + ",\n" +
                "\"cooking level\": " + player.playerLevel[7] + ",\n" +
                "\"attack exp\": " + player.playerXP[0] + ",\n" +
                "\"agility exp\": " + player.playerXP[16] + ",\n" +
                "\"agility level\": " + player.playerLevel[16] + ",\n" +
                "\"combat level\": " + player.combatLevel + ",\n" +
                "\"crafting exp\": " + player.playerXP[12] + ",\n" +
                "\"defence exp\": " + player.playerXP[1] + ",\n" +
                "\"firemaking exp\": " + player.playerXP[11] + ",\n" +
                "\"fishing level\": " + player.playerLevel[10] + ",\n" +
                "\"herblore exp\": " + player.playerXP[15] + ",\n" +
                "\"hitpoints exp\": " + player.playerXP[3] + ",\n" +
                "\"prayer level\": " + player.playerLevel[5] + ",\n" +
                "\"range level\": " + player.playerLevel[4] + ",\n" +
                "\"slayer exp\": " + player.playerXP[18] + ",\n" +
                "\"smithing level\": " + player.playerLevel[13] + ",\n" +
                "\"thieving exp\": " + player.playerXP[17] + ",\n" +
                "\"woodcutting exp\": " + player.playerXP[8] + ",\n" +
                "\"thieving level\": " + player.playerLevel[17] + ",\n" +
                "\"strength level\": " + player.playerLevel[2] + ",\n" +
                "\"slayer level\": " + player.playerLevel[18] + ",\n" +
                "\"runecrafting exp\": " + player.playerXP[20] + ",\n" +
                "\"quest points\": " + player.questPoints + ",\n" +
                "\"mining exp\": " + player.playerXP[14] + ",\n" +
                "\"magic exp\": " + player.playerXP[6] + ",\n" +
                "\"herblore level\": " + player.playerLevel[15] + ",\n" +
                "\"fletching exp\": " + player.playerXP[9] + ",\n" +
                "\"firemaking level\": " + player.playerLevel[11] + ",\n" +
                "\"farming exp\": " + player.playerXP[19] + ",\n" +
                "\"crafting level\": " + player.playerLevel[12] + ",\n" +
                "\"cooking exp\": " + player.playerXP[7] + ",\n" +
                "\"attack level\": " + player.playerLevel[0] + "\n}";

        RequestBody requestBody = RequestBody.create(JSON, data);

        Request request = new Request.Builder()
                .url(url)
                .patch(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
        }
    }

    public static void addAdventureLog(Player player, String message) throws IOException {

        URL url = new URL("https://06server.bubbleapps.io/version-test/api/1.1/obj/adventurelogs");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"Message\": \""+ message +"\",\n \"Character\": \""+player.uniqueId+"\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

}
