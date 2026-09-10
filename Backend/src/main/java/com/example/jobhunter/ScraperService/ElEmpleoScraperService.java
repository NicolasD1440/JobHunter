package com.example.jobhunter.ScraperService;

import com.example.jobhunter.DTO.JobSearchRequest;
import com.example.jobhunter.ExceptionError.ScraperException;
import com.example.jobhunter.Model.RawJob;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.jobhunter.ExceptionError.ScraperException;
@Service
public class ElEmpleoScraperService {

    public List<RawJob> search(JobSearchRequest request) {

        String url = generateUrl(request);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestHttp = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(requestHttp, HttpResponse.BodyHandlers.ofString());
            Document document = Jsoup.parse(response.body());

            Elements elementos = document.select("a[data-offer-id]");
            List<RawJob> rawJobs = new ArrayList<>();

            for (Element el : elementos) {
                String offerId = el.attr("data-offer-id");
                String rawTitle = el.attr("data-offer-title");
                String rawCompany = el.attr("data-offer-companyname");
                String sourceUrl = el.attr("data-offer-url");
                String rawSalary = el.attr("data-offer-salary");
                String source = "El empleo";
                String workMode = "presencial";
                String contractType = "";
                String rawDescription = el.attr("data-offer-description");

                String rawLocation = "No especificada";

                Element cardContainer = el.closest(".result-item");
                if (cardContainer == null) {
                    cardContainer = el.closest(".area-bind");
                }
                if (cardContainer == null) {
                    cardContainer = el.parent().parent();
                }

                if (cardContainer != null) {
                    Element cityElement = cardContainer.selectFirst(".info-city, .js-offer-city");
                    if (cityElement != null) {
                        rawLocation = cityElement.text().trim();
                    }

                    // --- Modalidad ---
                    Element modalityElement = cardContainer.selectFirst(".js-work-modality");
                    if (modalityElement != null && !modalityElement.text().trim().isEmpty()) {
                        // Remueve el guion inicial ("- Hibrido" -> "Hibrido")
                        workMode = modalityElement.text().replace("-", "").trim();
                    }

                    // --- Tipo de contrato ---
                    // Buscamos la etiqueta "Tipo de contrato" y obtenemos el div anterior donde está el valor
                    Element contractLabel = cardContainer.selectFirst("div.small-text:contains(Tipo de contrato)");
                    if (contractLabel != null && contractLabel.previousElementSibling() != null) {
                        contractType = contractLabel.previousElementSibling().text().trim();
                    }

                }


                if (!offerId.isEmpty() && !rawTitle.isEmpty()) {
                   RawJob rawJob = new RawJob( rawTitle, rawCompany, rawLocation, workMode, rawSalary, contractType, rawDescription, source, sourceUrl);
                   rawJobs.add(rawJob);
                }
            }

            return rawJobs;

        } catch (Exception e) {
            throw new ScraperException("Prueba de error del scraper", e);
        }


    }

    public String generateUrl(JobSearchRequest request) {
        StringBuilder path = new StringBuilder("https://www.elempleo.com/co/ofertas-empleo");
        List<String> queryParams = new ArrayList<>();

        // 1. Procesar Ubicación (Va en la ruta)
        if (request.getLocation() != null && !request.getLocation().trim().isEmpty()) {
            path.append("/").append(request.getLocation().trim().toLowerCase());
        }

        // 2. Procesar Palabra Clave (Va en la ruta)
        if (request.getKeywords() != null && !request.getKeywords().isEmpty()) {
            // Une los elementos con "-" y reemplaza cualquier espacio interno restante por "-"
            String keyword = String.join("-", request.getKeywords())
                    .trim()
                    .replaceAll("\\s+", "-");

            if (!keyword.isEmpty()) {
                path.append("/trabajo-").append(keyword);
            }
        }
        

        // 3. Procesar Salario (Parámetro Query)
        if (request.getSalaryMin() != null && request.getSalaryMin() > 0) {
            List<String> salaryList = convertSalary(request.getSalaryMin());
            String min = salaryList.get(0);
            String max = salaryList.get(1);
            queryParams.add("Salaries=" + min + "-" + max);
        }

        // 4. Procesar Modalidad de Trabajo (Parámetro Query)
        if (request.getWorkMode() != null && !request.getWorkMode().trim().isEmpty()) {
            queryParams.add("modalidad=" + request.getWorkMode().trim().toLowerCase());
        }

        // 5. Ensamblar Query Parameters si existen
        if (!queryParams.isEmpty()) {
            path.append("?").append(String.join("&", queryParams));
        }

        return path.toString();
    }

    public static List<String> convertSalary(Integer salary){
        List<String> salaryList = new ArrayList<>();
        Integer max;
        if(salary >= 10000000){
            salary = Math.round((float) salary / 1_000_000F);
            salary = Math.round((float) salary / 5) * 5;
            String min = String.valueOf(salary);
            max = salary + 5;
            String maxC = String.valueOf(max);
            salaryList.add(0, min);
            salaryList.add(1, maxC);
            return salaryList;

        }
        salary = salary/100000;
        salary = Math.round((float) salary / 5) * 5;

        String min = String.valueOf(salary);
        min = min.replaceAll("0","");
        max = salary + 5;
        String maxC = String.valueOf(max);
        salaryList.add(0, min);
        salaryList.add(1, maxC);
        return salaryList;
    }
}
