package com.example.jobhunter.Service;

import com.example.jobhunter.Model.Job;
import com.example.jobhunter.Model.RawJob;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JobNormallizer {

    public Job normalize(RawJob rawJob){

        List<Integer> salaryRange = getRangeSalary(rawJob.getRawSalary());

        String title = rawJob.getRawTitle();
        String location = rawJob.getRawLocation();
        String company = rawJob.getRawCompany();
        Integer salaryMin = salaryRange.get(0);
        Integer salaryMax = salaryRange.get(1);
        String workMode = rawJob.getWorkMode();
        String contractType = rawJob.getContractType();
        List<String> requiredEducation = convertRawEducation(rawJob.getRawDescription());
        Integer requiredYearsExperience = convertRawExperience(rawJob.getRawDescription());
        List<String> requiredSkills = convertRawSkills(rawJob.getRawDescription());
        String source = rawJob.getSource();
        String description = rawJob.getRawDescription();
        String sourceUrl = rawJob.getSourceUrl();

        Job job = new Job(title,  location, company, salaryMin, salaryMax, workMode, contractType, requiredEducation, requiredYearsExperience, requiredSkills, source, description, sourceUrl);
        return job;
    }


    public List<Integer> convertRawSalary(String salary){
        Pattern patron = Pattern.compile("\\b\\d{1,3}(?:\\.\\d{3})+\\b|\\b\\d+(?:[,.]\\d+)?\\b");
        Matcher matcher = patron.matcher(salary);

        List<Integer> salarios = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if (match.contains(".") && match.chars().filter(ch -> ch == '.').count() > 1) {
                String numeroLimpio = match.replace(".", "");
                salarios.add(Integer.parseInt(numeroLimpio));
            }else {
                // Caso abreviado: "4", "4,5", "4.5"
                String numeroLimpio = match.replace(",", ".");
                double valor = Double.parseDouble(numeroLimpio);

                // Si el valor es menor a 100, asumimos que representa millones (ej. 4.5 -> 4,500,000)
                if (valor < 100) {
                    valor = valor * 1_000_000;
                }
                salarios.add((int) Math.round(valor));
            }

        }
           return salarios;
    }

    public List<Integer> getRangeSalary(String description){
        List<Integer> lista = convertRawSalary(description);
        List<Integer> listRange = new ArrayList<>();
        Integer minSalary;
        Integer maxSalary;

        if (lista.size() <= 0) {
            listRange.add(0, null);
            listRange.add(1, null);
            return listRange;
        }else if (lista.size() == 1) {
            listRange.add(lista.get(0));
            listRange.add(1, null);
            return listRange;
        } else  {
            minSalary = lista.get(0);
            maxSalary = lista.get(0);
            for (Integer i = 0; i < lista.size(); i++) {
                if (lista.get(i) > maxSalary) {
                    maxSalary = lista.get(i);
                }

                if (lista.get(i) < minSalary) {
                    minSalary = lista.get(i);
                }
            }
            listRange.add(minSalary);
            listRange.add(maxSalary);

            return listRange;
        }
    }

    public Integer convertRawExperience(String experience){
        Pattern patron = Pattern.compile("\\b[1-5]\\b");
        Matcher matcher = patron.matcher(experience);

        List<Integer> years = new ArrayList<>();
        while (matcher.find()) {
            String numeroLimpio = matcher.group().replace(".", "");
            years.add(Integer.parseInt(numeroLimpio));
        }

        if (years.size() > 0) {
            Integer menor = years.get(0);
            for (Integer i = 0; i < years.size(); i++) {
                if (years.get(i) < menor) {
                    menor = years.get(i);
                }
            }

            return menor;
        }
        return null;
    }

    public List<String> convertRawSkills(String description){
        String[] knownSkills = {"Java", "Spring Boot", "PostgreSQL", "Docker", "React","Node.js",
                "PHP","Git","Oracle","MySQL","Android","Flutter","iOS",".NET","C#","SQL","SQL Server",
                 "vue", "vue.js","PL/SQL"};
        List<String> resultado = new ArrayList<>();
        String convert = description.toLowerCase();
        for (String found : knownSkills) {
            if (convert.contains(found.toLowerCase())) {
                resultado.add(found);
            }
        }
        return resultado;
    }

    public List<String> convertRawEducation (String description){
        String[] education = {"Tecnologo", "Tecnico", "Profesional", "Ingeniero de software", "Ingeniero de sistemas", "Estudiante"};
        String textoNormalizado = Normalizer.normalize(description, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        List<String> resultado = new ArrayList<>();
        String convert = textoNormalizado.toLowerCase();
        for (String found : education) {
            if (convert.contains(found.toLowerCase())) {
                resultado.add(found);
            }
        }
        return resultado;
    }
}
