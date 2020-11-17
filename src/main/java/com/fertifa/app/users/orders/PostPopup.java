package com.fertifa.app.users.orders;

import lombok.SneakyThrows;
import okhttp3.*;

import java.io.IOException;

public class PostPopup {
    public static final MediaType popupType = MediaType.get("text/xml; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String post(String url, String popupWindow) throws IOException {

        RequestBody body = RequestBody.create(popupType, popupWindow);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {

                try (ResponseBody responseBody = response.body()) {
                    System.out.println("Done call transfer "+  response.body());
                }
            }

            @SneakyThrows
            public void onFailure(Call call, IOException e) {
                throw new IOException("the error " + e);
            }
        });
       /* try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;

        }catch(Exception e){
            e.printStackTrace();
        }*/
        return null;
    }
    public String popupXml(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<!DOCTYPE>\n" +
                "\n" +
                "<article lang=\"\">\n" +
                "\n" +
                "\n" +
                "<sect1 id=\"incomingVideoCall\">\n" +
                "\n" +
                "\n" +
                "-<title>\n" +
                "\n" +
                "<anchor id=\"exampleModalCenterTitle\"/>\n" +
                "Modal title\n" +
                "</title>\n" +
                "\n" +
                "<para>× </para>\n" +
                "\n" +
                "<para>... </para>\n" +
                "\n" +
                "<para>Close Save changes </para>\n" +
                "\n" +
                "</sect1>\n" +
                "\n" +
                "\n" +
                "<sect1>\n" +
                "\n" +
                "\n" +
                "<title>\n" +
                "\n" +
                "<anchor id=\"exampleModalCenterTitle\"/>\n" +
                "Modal title\n" +
                "</title>\n" +
                "\n" +
                "<para>× </para>\n" +
                "\n" +
                "<para>... </para>\n" +
                "\n" +
                "<para>Close Save changes </para>\n" +
                "\n" +
                "</sect1>\n" +
                "\n" +
                "</article>";
    }

    public String popupWindow() {
        return "<div class=\"modal fade\" id=\"incomingVideoCall\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\"\n" +
                "     aria-hidden=\"true\">\n" +
                "    <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
                "        <div class=\"modal-content\">\n" +
                "            <div class=\"modal-header\">\n" +
                "                <h5 class=\"modal-title\" id=\"exampleModalCenterTitle\">Modal title</h5>\n" +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "                    <span aria-hidden=\"true\">&times;</span>\n" +
                "                </button>\n" +
                "            </div>\n" +
                "            <div class=\"modal-body\">\n" +
                "                ...\n" +
                "            </div>\n" +
                "            <div class=\"modal-footer\">\n" +
                "                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "                <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
    }

}
