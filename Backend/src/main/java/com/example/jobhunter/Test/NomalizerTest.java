package com.example.jobhunter.Test;

import com.example.jobhunter.DTO.JobSearchRequest;
import com.example.jobhunter.Matcher.JobMatcher;
import com.example.jobhunter.Model.Job;
import com.example.jobhunter.Model.Profile;
import com.example.jobhunter.Model.RawJob;
import com.example.jobhunter.ScraperService.ElEmpleoScraperService;
import com.example.jobhunter.Service.JobNormallizer;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;

import java.util.List;


public class NomalizerTest {

        public static void main(String[] args) {

           /* String rawTitle = "Desarrollador(a) Móvil Junior";
            String rawCompany = "Tecn Sistemas";
            String rawLocation = "Bogotá";
            String rawWorkMode = "hibrido";
            String rawSalary = "Entre $3.500.000 y $4.000.000 COP";
            String rawContractType = "contrato fijo";
            String rawDescription = "¿Te apasiona el desarrollo de aplicaciones móviles...? \n" +
                    "Técnico, tecnólogo o estudiante de últimos semestres...\n" +
                    "1 año de experiencia desarrollando aplicaciones móviles.\n" +
                    "Conocimientos en desarrollo móvil para Android y/o iOS.\n" +
                    "Muy deseable experiencia trabajando con Flutter.";
            String source = "El empleo";
            String sourceUrl = "alguna-url";
            RawJob rawJob = new RawJob(rawTitle, rawCompany, rawLocation, rawWorkMode, rawSalary, rawContractType, rawDescription, source, sourceUrl);*/

           /* Job job = new Job();
            job.setId(1);
            job.setTitle("Ingeniero de software junior");
            job.setCompany("Tech Solutions");
            job.setLocation("Bogotá");
            job.setSalaryMin(3000000);
            job.setSalaryMax(4500000);
            job.setWorkMode("Remote");
            job.setContractType("Indefinido");
            job.setRequiredEducation(List.of("Ingeniería de Sistemas", "Ingeniería de Software"));
            job.setRequiredYearsExperience(2);
            job.setRequiredSkills(List.of("Java", "Docker", "React"));

            String name = "Nicolas";
            String phone = "3001234567";
            String email = "correo@example.com";
            String description = "Desarrollador Java";
            List<String> education = List.of("Ingeniería de Sistemas", "Curso de Spring Boot");
            Integer yearsExperience = 2;
            List<String> skills = List.of("Java",
                    "Spring Boot",
                    "PostgreSQL",
                    "Docker");
            Profile profile = new Profile(name,  phone,  email,  description,
                     education,  yearsExperience,  skills);

            JobMatcher matcher = new JobMatcher();

            System.out.println(matcher.calculateMatch(profile,job));*/

            ElEmpleoScraperService scraper = new ElEmpleoScraperService();
            List<String> keywords = List.of("Java");
            String location = "Bogota";
            String workMode = "Presencial";
            Integer salaryMin = 20000000;
            JobSearchRequest request = new JobSearchRequest(keywords, location, workMode, salaryMin);
            System.out.println(scraper.generateUrl(request));

        }


}
