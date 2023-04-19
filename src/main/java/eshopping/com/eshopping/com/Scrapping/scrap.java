package eshopping.com.eshopping.com.Scrapping;


import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class scrap {




    public static String getdata(String link) throws IOException {

        HashMap<String,  String > hashdata= new HashMap<>();

        Document doc  = Jsoup.connect(link).get();

        Element main = Objects.requireNonNull(doc.getElementById("body")).getElementById("specs-list");
        assert main != null;
        Elements div= doc.getElementById("body").select("div.review-header");

        String image_link ="https://www.gsmarena.com/"+div.select("div.specs-photo-main").select("a").attr("href");



        Elements data = main.getElementsByTag("table");

        for (Element i : data){

            for (Element  tr : i .select("tbody").select("tr").removeClass("tr-hover"   )){

                for (Element td : tr.select("tr")){

                    if (!tr.select("th").text().equals("")){
                        String KEY =  tr.select("th").text().toUpperCase();



                        String key =   td.select("td.ttl").text();

                        String value  =   td.select("td.nfo").text();


                        hashdata.put(KEY, key+ "  " + value);

                    }




                }



            }

        }





        PhoneScrapModel phone=new   PhoneScrapModel(
                hashdata.get("BODY").toString(),
                hashdata.get("LAUNCH").toString(),
                hashdata.get("PLATFORM").toString(),
                hashdata.get("BATTERY").toString(),
                hashdata.get("NETWORK").toString(),
                hashdata.get("SELFIE CAMERA").toString(),
                hashdata.get("MAIN CAMERA").toString(),
                hashdata.get("MISC").toString(),
                hashdata.get("FEATURES").toString(),
                hashdata.get("DISPLAY").toString(),
                hashdata.get("COMMS").toString(),
                hashdata.get("MEMORY").toString(),
                hashdata.get("SOUND").toString()




        );
        Gson gson = new Gson();
        String json = gson.toJson( phone);
        return json;





    }
    public static   ArrayList<String> getpictures(String link) throws IOException {

        ArrayList<String> img_array=new ArrayList<>();
        Document doc  = Jsoup.connect(link).get();
        for (Element img : Objects.requireNonNull(doc.getElementById("pictures-list")).select("img"))  {


            img_array.add(img.select("img").attr("src"));
        }


        return img_array;
    }






}